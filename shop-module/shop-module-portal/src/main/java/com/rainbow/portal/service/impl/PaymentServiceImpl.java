package com.rainbow.portal.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.entity.Order;
import com.rainbow.api.entity.OrderSku;
import com.rainbow.api.entity.PaymentRecord;
import com.rainbow.api.entity.Spu;
import com.rainbow.api.enums.OrderStatusEnum;
import com.rainbow.api.enums.PayTypeEnum;
import com.rainbow.api.enums.PortalErrorCode;
import com.rainbow.api.vo.UploadImageVO;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.portal.mapper.OrderMapper;
import com.rainbow.portal.mapper.OrderSkuMapper;
import com.rainbow.portal.mapper.PaymentRecordMapper;
import com.rainbow.portal.mapper.SpuMapper;
import com.rainbow.portal.service.IPaymentService;
import com.rainbow.portal.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class PaymentServiceImpl implements IPaymentService {

    @Value("${rainbow.alipay.callback.url}")
    private String alipayCallbackUrl;

    @Value("${rainbow.alipay.qrCode.path}")
    private String qrCodePath;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderSkuMapper orderSkuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    @Resource
    private IUploadService uploadService;

    private static AlipayTradeService tradeService;

    static {

        Configs.init("zfbinfo.properties");

        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    @Override
    public UploadImageVO createPayQrCodeImage(ParentOrderNoDTO param) {
        //1、获取订单信息
        List<Order> orderList = orderMapper.getByParentOrderNo(param.getCustomerId(), param.getParentOrderNo());
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException(PortalErrorCode.WAIT_TO_PAY_ORDER_ERROR);
        }
        //2、构建支付宝支付信息
        AlipayTradePrecreateRequestBuilder builder = buildAlipayParam(orderList, param.getParentOrderNo(), param.getCustomerId());
        //3、调用trade
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        //4、如果成功生成二维码图片并上传
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();

                // 需要修改为运行机器上的路径
                File folder = new File(qrCodePath);
                if (!folder.exists()) {
                    folder.setWritable(true);
                    folder.mkdirs();
                }
                //细节细节细节
                String qrPath = String.format(qrCodePath + "qr-%s.png", response.getOutTradeNo());

                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);
                File qrCodeImage = new File(qrPath);
                return uploadService.upload(qrCodeImage);
            case FAILED:
                log.error("支付宝预下单失败!!!");
                throw new BusinessException(PortalErrorCode.ALIPAY_ORDER_FAILED);

            case UNKNOWN:
                log.error("系统异常, 预下单状态未知!!!");
                throw new BusinessException(PortalErrorCode.ALIPAY_EXCEPTION_UNKNOWN);

            default:
                log.error("不支持的交易状态, 交易返回异常!!!");
                throw new BusinessException(PortalErrorCode.ALIPAY_UNSUPPORT_EXCEPTION);
        }
    }

    private AlipayTradePrecreateRequestBuilder buildAlipayParam(List<Order> orderList, String parentOrderNo, Long customerId) {
        //商户网站订单系统中唯一订单号
        String outTradeNo = parentOrderNo;
        //订单标题
        String subject = "Rainbow扫码支付,订单号:" + outTradeNo;
        //订单总金额, 单位为元
        String totalAmount = calculateTotalAmount(orderList).toString();
        //订单不可打折金额
        String undiscountableAmount = "0";
        //卖家支付宝账号ID
        String sellerId = "";
        //订单描述
        String body = "订单" + outTradeNo + "购买商品共" + totalAmount + "元";
        //商户操作员编号
        String operatorId = "test_operator_id";
        //商户门店编号
        String storeId = "test_store_id";
        //业务扩展参数
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");
        //支付超时时间
        String timeoutExpress = "120m";
        //商品明细列表
        List<GoodsDetail> goodsDetailList = Lists.newArrayList();
        List<OrderSku> orderSkuList = Lists.newArrayList();
        for (Order order : orderList) {
            List<OrderSku> orderSkus = orderSkuMapper.listByOrderId(order.getId());
            orderSkuList.addAll(orderSkus);
        }
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        Set<Long> spuIds = orderSkuList.stream().map(OrderSku::getSpuId).collect(Collectors.toSet());
        List<Spu> spuList = spuMapper.selectBatchIds(spuIds);
        Map<Long, String> spuMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Spu::getName));
        for (OrderSku orderSku : orderSkuList) {
            GoodsDetail goods = GoodsDetail.newInstance(orderSku.getSpuId().toString(), spuMap.get(orderSku.getSpuId()),
                    orderSku.getPrice().multiply(new BigDecimal(100)).longValue(),
                    orderSku.getQuantity());
            goodsDetailList.add(goods);
        }

        // 创建扫码支付请求builder, 设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setNotifyUrl(alipayCallbackUrl)
                .setGoodsDetailList(goodsDetailList);
        return builder;
    }

    private BigDecimal calculateTotalAmount(List<Order> orderList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (Order order : orderList) {
            totalAmount = totalAmount.add(order.getTotalAmount());
        }
        return totalAmount;
    }


    @Override
    public Boolean paySucessCallback(Long customerId, HttpServletRequest request) {
        Map<String, String> params = getParameterMap(request);
        try {
            //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
            if (!alipayRSACheckedV2) {
                throw new BusinessException(PortalErrorCode.ALIPAY_VERIFY_ERROR);
            }
        } catch (AlipayApiException e) {
            log.error("支付宝验证回调异常", e);
        }
        // 验证各种数据
        alipay(customerId, params);
        return Boolean.FALSE;
    }

    private Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        log.info("支付宝回调,sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());

        params.remove("sign_type");
        return params;
    }

    private void alipay(Long customerId, Map<String, String> params) {
        log.info("支付宝回调. - aliPayCallback. params={}", params);
        String orderNo = params.get("out_trade_no");
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        //修改订单状态
        List<Order> orderList = orderMapper.getByParentOrderNo(customerId, orderNo);
        if(CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException(PortalErrorCode.ORDER_NOT_EXIST);
        }
        for (Order order: orderList) {
            order.setUpdateTime(LocalDateTime.now());
            order.setPaymentTime(LocalDateTime.parse(params.get("gmt_payment")));
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
        orderSkuMapper.updateSkuStock(orderSkuList);
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(customerId);
        paymentRecord.setParentOrderNo(orderNo);
        paymentRecord.setTradeNo(tradeNo);
        paymentRecord.setTradeStatus(tradeStatus);
        paymentRecord.setPayPlatform(PayTypeEnum.ALI_PAY.getValue());
        paymentRecord.setCreateTime(LocalDateTime.now());
        paymentRecordMapper.insert(paymentRecord);
    }
}
