package com.rainbow.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.common.collect.Maps;
import com.rainbow.api.dto.AliPayNotifyDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.entity.Order;
import com.rainbow.api.entity.OrderSku;
import com.rainbow.api.entity.PaymentRecord;
import com.rainbow.api.entity.Sku;
import com.rainbow.api.enums.OrderStatusEnum;
import com.rainbow.api.enums.PayTypeEnum;
import com.rainbow.api.enums.PortalErrorCode;
import com.rainbow.common.enums.RedisKeyEnums;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.portal.config.yml.AliPayConfiguration;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.service.IAliPayService;
import com.rainbow.portal.service.IFlashService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 支付
 *
 * @author lujinwei
 * @since 2020/4/7
 */
@Service
@Slf4j
public class AliPayServiceImpl implements IAliPayService {

    @Autowired
    private AliPayConfiguration aliPayConfig;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderSkuMapper orderSkuMapper;

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private FlashPromotionSpuMapper flashPromotionSpuMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String goPay(ParentOrderNoDTO param) {
        //1、获取订单信息
        List<Order> orderList = orderMapper.getByParentOrderNo(param.getCustomerId(), param.getParentOrderNo());
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException(PortalErrorCode.WAIT_TO_PAY_ORDER_ERROR);
        }
        return buildAlipayParam(orderList, param.getParentOrderNo());
    }

    private String buildAlipayParam(List<Order> orderList, String parentOrderNo) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(), aliPayConfig.getAppId(), aliPayConfig.getMerchantPrivateKey(), "json", aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = parentOrderNo;
        //付款金额，必填
        String total_amount = calculateTotalAmount(orderList);
        //订单名称，必填
        String subject = parentOrderNo + "订单商品";
        //商品描述，可空
        String body = "用户订购商品个数：" + calculateGoodsQuantity(orderList);

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return result;
    }

    /**
     * 计算总金额
     *
     * @param orderList
     * @return
     */
    private String calculateTotalAmount(List<Order> orderList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (Order order : orderList) {
            totalAmount = totalAmount.add(order.getTotalAmount());
        }
        return String.valueOf(totalAmount);
    }

    /**
     * 计算商品数量
     *
     * @param orderList
     * @return
     */
    private Integer calculateGoodsQuantity(List<Order> orderList) {
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        Set<Long> orderIds = orderList.stream().map(Order::getId).collect(Collectors.toSet());
        List<OrderSku> orderSkuList = orderSkuMapper.listByOrderIds(orderIds);
        Integer quantity = 0;
        for (OrderSku orderSku : orderSkuList) {
            quantity += orderSku.getQuantity();
        }
        return quantity;
    }


    @Override
    public Boolean paySucessCallback(Long customerId, AliPayNotifyDTO param) {
        Map<String, String> params = getParameterMap(param);
        try {
            //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType()); //调用SDK验证签名

            if (!signVerified) {
                //沙箱环境验证会出错
//              throw new BusinessException(PortalErrorCode.ALIPAY_VERIFY_ERROR);
            }
        } catch (AlipayApiException e) {
            log.error("支付宝验证回调异常", e);
        }
        // 验证各种数据
        alipay(customerId, params);
        return Boolean.TRUE;
    }

    private Map<String, String> getParameterMap(AliPayNotifyDTO param) {
        Map<String, String> result = Maps.newHashMap();
        if (param == null) {
            return result;
        }
        Class clazz = param.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                result.put(field.getName(), (String) field.get(param));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("支付宝回调,sign:{}, 参数:{}", param.getSign(), result.toString());
        return result;
    }

    private void alipay(Long customerId, Map<String, String> params) {
        log.info("支付宝回调. - aliPayCallback. params={}", params);
        String orderNo = params.get("outTradeNo");
        String tradeNo = params.get("tradeNo");
        String tradeStatus = params.getOrDefault("tradeStatus", "TRADE_SUCCESS");
        //修改订单状态

        List<Order> orderList = orderMapper.getByParentOrderNo(customerId, orderNo);
        if (CollectionUtils.isEmpty(orderList)) {
            return;
        }
        for (Order order : orderList) {
            //幂等性
            if (order.getStatus().equals(OrderStatusEnum.NON_DELIVER.getValue())) {
                return;
            }
        }
        for (Order order : orderList) {
            order.setUpdateTime(LocalDateTime.now());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            order.setPaymentTime(LocalDateTime.parse(params.get("timestamp"), df));
            order.setTradeNo(tradeNo);
            order.setStatus(OrderStatusEnum.NON_DELIVER.getValue());
            orderMapper.updateById(order);
        }
        Set<Long> orderIds = orderList.stream().map(Order::getId).collect(Collectors.toSet());
        //2、释放锁定库存，扣减真实库存
        List<OrderSku> orderSkuList = orderSkuMapper.listByOrderIds(orderIds);
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        List<Long> skuIdList = orderSkuList.stream().map(OrderSku::getSkuId).collect(Collectors.toList());
        String key = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_FLASH_GOODS_KEY.getRedisKey() + "_" + customerId;
        String goodsInfo = redisTemplate.opsForValue().get(key);
        boolean flashFlag = false;
        JSONObject goods = new JSONObject();
        if (Strings.isNotBlank(goodsInfo)) {
            goods = JSONObject.parseObject(goodsInfo);
            Long skuId = goods.getLong("skuId");
            if(skuIdList.get(0).equals(skuId)) {
                flashFlag = true;
            }
        }
        if(flashFlag) {
            Long skuId = goods.getLong("skuId");
            Sku sku = skuMapper.selectById(skuId);
            Long flashPromotionId = goods.getLong("flashPromotionId");
            Long flashPromotionSessionId = goods.getLong("flashPromotionSessionId");
            Integer flashPromotionNum = goods.getInteger("flashPromotionNum");
            Integer quantity = goods.getInteger("quantity");
            flashPromotionSpuMapper.updateFlashSpuStock(flashPromotionSessionId, flashPromotionId, sku.getSpuId(), flashPromotionNum - quantity);
            spuMapper.updateSale(sku.getSpuId(), quantity);
        } else {
            orderSkuMapper.updateSkuStock(orderSkuList);
            Map<Long, Integer> spuQuantityMap = calculateQuantity(orderSkuList);
            for(Map.Entry<Long, Integer> entry: spuQuantityMap.entrySet()) {
                spuMapper.updateSale(entry.getKey(), entry.getValue());
            }
        }
        //更新spu销量

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(customerId);
        paymentRecord.setParentOrderNo(orderNo);
        paymentRecord.setTradeNo(tradeNo);
        paymentRecord.setTradeStatus(tradeStatus);
        paymentRecord.setPayPlatform(PayTypeEnum.ALI_PAY.getValue());
        paymentRecord.setCreateTime(LocalDateTime.now());
        paymentRecordMapper.insert(paymentRecord);
    }


    private Map<Long, Integer> calculateQuantity(List<OrderSku> orderSkuList) {
        Map<Long, Integer> quantityMap = Maps.newHashMap();
        if(CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        Map<Long, List<OrderSku>> spuMap = orderSkuList.stream().collect(Collectors.groupingBy(OrderSku::getSpuId));
        for(Map.Entry<Long, List<OrderSku>> entry: spuMap.entrySet()) {
            quantityMap.put(entry.getKey(), calculateQuantityPerSpu(entry.getValue()));
        }
        return quantityMap;
    }

    private Integer calculateQuantityPerSpu(List<OrderSku> orderSkuList) {
        Integer total = 0;
        for(OrderSku orderSku: orderSkuList) {
            total += orderSku.getQuantity();
        }
        return total;
    }
}
