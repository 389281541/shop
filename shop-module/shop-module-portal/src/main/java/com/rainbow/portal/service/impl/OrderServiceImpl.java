package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.dto.SelfOrderSearchDTO;
import com.rainbow.api.entity.*;
import com.rainbow.api.enums.*;
import com.rainbow.api.vo.*;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.enums.RedisKeyEnums;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.model.KV;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.mq.CancelOrderSender;
import com.rainbow.portal.service.IAddressService;
import com.rainbow.portal.service.ICartService;
import com.rainbow.portal.service.ICouponService;
import com.rainbow.portal.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.rainbow.common.util.GeneratorUtils.generateParentOrderNO;

/**
 * 订单表 服务实现类
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICouponService couponService;

    @Resource
    private IntegrationRuleSettingMapper integrationRuleSettingMapper;

    @Resource
    private SpuImgMapper spuImgMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public ConfirmOrderVO generateConfirmOrder(Long customerId) {
        ConfirmOrderVO confirmOrderVO = new ConfirmOrderVO();
        //购物车列表带促销信息
        List<CartPromotionVO> cartPromotionVOList = cartService.list(customerId);
        if (CollectionUtils.isEmpty(cartPromotionVOList)) {
            throw new BusinessException(PortalErrorCode.CART_CAN_NOT_EMPTY);
        }
        confirmOrderVO.setCartPromotionList(cartPromotionVOList);
        Set<Long> shopIdSet = cartPromotionVOList.stream().map(CartPromotionVO::getShopId).collect(Collectors.toSet());
        confirmOrderVO.setOrderNum(shopIdSet.size());
        //收货地址
        List<CustomerAddressVO> customerAddressVOList = addressService.listAddress(customerId);
        confirmOrderVO.setCustomerAddressList(customerAddressVOList);
        //计算订单能使用的最大积分
        confirmOrderVO.setRecommendIntegration(calculateMaxCanUseIntegration(cartPromotionVOList, customerId));
        //可以使用的优惠券列表
        List<CouponCustomerDetailVO> couponCustomerDetailVOList = couponService.listOrderAvailable(customerId, cartPromotionVOList, BooleanEnum.YES.getValue());
        confirmOrderVO.setCouponList(convertCoupon(couponCustomerDetailVOList));
        return confirmOrderVO;
    }

    /**
     * 计算最大可用积分
     *
     * @param cartPromotionVOList
     * @return
     */
    public Integer calculateMaxCanUseIntegration(List<CartPromotionVO> cartPromotionVOList, Long customerId) {
        if (CollectionUtils.isEmpty(cartPromotionVOList)) {
            throw new BusinessException(PortalErrorCode.CART_CAN_NOT_EMPTY);
        }
        //1、计算购物车原价总金额
        //积分使用规则
        IntegrationRuleSetting integrationRuleSetting = integrationRuleSettingMapper.selectById(1);
        BigDecimal totalAmount = new BigDecimal(0);
        for (CartPromotionVO cartPromotionVO : cartPromotionVOList) {
            totalAmount = totalAmount.add(cartPromotionVO.getOriginalPrice());
        }
        //2、计算订单抵扣最大积分限制
        Integer maxBillIntegeration = totalAmount.multiply(new BigDecimal(integrationRuleSetting.getMaxPercentPerOrder())).intValue();
        //3、计算sku每一项积分抵扣限制
        Set<Long> spuIds = cartPromotionVOList.stream().map(CartSimpleVO::getSpuId).collect(Collectors.toSet());
        List<Spu> spuList = spuMapper.listBySpuIds(spuIds);
        Map<Long, Spu> spuMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Function.identity()));
        Integer skuTotalIntegrationLimit = 0;
        for (CartPromotionVO cartPromotionVO : cartPromotionVOList) {
            Spu spu = spuMap.get(cartPromotionVO.getSpuId());
            skuTotalIntegrationLimit = skuTotalIntegrationLimit + spu.getUseIntegrationLimit() * cartPromotionVO.getQuantity();
        }
        //4、计算最大抵扣积分
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException(PortalErrorCode.USER_NOT_EXIST);
        }
        return Math.min(Math.min(maxBillIntegeration, skuTotalIntegrationLimit), customer.getIntegration());
    }

    public List<CouponSimpleVO> convertCoupon(List<CouponCustomerDetailVO> couponCustomerDetailVOList) {
        if (org.springframework.util.CollectionUtils.isEmpty(couponCustomerDetailVOList)) {
            return Lists.newArrayList();
        }
        return couponCustomerDetailVOList.stream().map(x -> {
            CouponSimpleVO couponrSimpleVO = new CouponSimpleVO();
            BeanUtils.copyProperties(x.getCoupon(), couponrSimpleVO);
            return couponrSimpleVO;
        }).collect(Collectors.toList());
    }

    @Override
    public String generateOrder(OrderGenerateDTO param) {
        List<CartPromotionVO> cartPromotionVOList = cartService.list(param.getCustomerId());
        if (CollectionUtils.isEmpty(cartPromotionVOList)) {
            throw new BusinessException(PortalErrorCode.CART_CAN_NOT_EMPTY);
        }
        //生成交易单号
        String parentOrderNo = generateParentOrderNO(param.getCustomerId());
        Map<Long, List<CartPromotionVO>> shopIdCartMap = cartPromotionVOList.stream().collect(Collectors.groupingBy(CartPromotionVO::getShopId));
        for (Map.Entry<Long, List<CartPromotionVO>> entry : shopIdCartMap.entrySet()) {
            List<CartPromotionVO> cartPromotionVOS = entry.getValue();
            generateOrderByShopId(cartPromotionVOS, param, parentOrderNo, OrderTypeEnum.COMMON);
        }
        return parentOrderNo;
    }



    public Boolean generateOrderByShopId(List<CartPromotionVO> cartPromotionVOList, OrderGenerateDTO param, String parentOrderNo, OrderTypeEnum orderTypeEnum) {
        //1、获取orderSkuList
        List<OrderSkuPromotionVO> orderSkuList = getOrderSkuList(cartPromotionVOList);
        //2、存储校验
        checkStock(cartPromotionVOList);
        //3、优惠券使用处理
        handleCoupon(param.getCustomerId(), param.getCouponId(), cartPromotionVOList, orderSkuList);
        //4、积分抵扣处理
        Integer useIntegration = calculateMaxCanUseIntegration(cartPromotionVOList, param.getCustomerId());
        handleIntegration(param.getCustomerId(), useIntegration, orderSkuList, param.getCouponId());
        //5、计算支付金额
        calculatePerRealAmount(orderSkuList);
        //6、锁定存储
        lockStock(cartPromotionVOList);
        //7、构建订单信息并入库
        Long orderId = buildAndInsertOrder(orderSkuList, param, parentOrderNo, orderTypeEnum);
        //8、更改优惠券使用状态、扣减积分
        couponService.updateCouponStatus(param.getCustomerId(), param.getCouponId(), BooleanEnum.YES.getValue());
        reduceCustomerIntegration(param.getCustomerId(), useIntegration);
        //9、删除购物车列表
        reomoveCartList(param.getCustomerId(), cartPromotionVOList);
        //10、发送订单取消延迟消息
        sendCalcelOrderDelayMessage(orderId);
        return Boolean.TRUE;
    }

    /**
     * @param orderId
     */
    public void sendCalcelOrderDelayMessage(Long orderId) {
        //获取普通订单超时时间
        OrderSetting orderSetting = orderSettingMapper.selectById(1L);
        cancelOrderSender.sendMessage(orderId, orderSetting.getNormalOrderOvertime() * 60 * 1000L);
    }


    private void reomoveCartList(Long customerId, List<CartPromotionVO> cartPromotionVOList) {
        if (CollectionUtils.isEmpty(cartPromotionVOList)) {
            return;
        }
        Set<Long> ids = cartPromotionVOList.stream().map(CartPromotionVO::getId).collect(Collectors.toSet());
        cartService.removeCartList(customerId, ids);
    }

    /**
     * 减少用户积分
     *
     * @param customerId
     * @param useIntegration
     */
    private void reduceCustomerIntegration(Long customerId, Integer useIntegration) {
        if (useIntegration == null) {
            return;
        }
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException(PortalErrorCode.USER_NOT_EXIST);
        }
        if (useIntegration.compareTo(customer.getIntegration()) > 0) {
            throw new BusinessException(PortalErrorCode.INTEGRATION_NOT_ENOUGH);
        }
        Integer result = customerMapper.updateIntegration(customerId, customer.getIntegration() - useIntegration);
        if (result <= 0) {
            throw new BusinessException(PortalErrorCode.INTEGRATION_UPDATE_FAILED);
        }
    }


    /**
     * 构建并入库订单
     *
     * @param orderSkuList
     * @param param
     */
    private Long buildAndInsertOrder(List<OrderSkuPromotionVO> orderSkuList, OrderGenerateDTO param, String parentOrderNO, OrderTypeEnum orderTypeEnum) {
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        Order order = new Order();
        order.setTotalAmount(calculateTotalAmount(orderSkuList));
        order.setDeliverFee(param.getDeliverFee());
        order.setPayType(param.getPayType());
        order.setPromotionAmount(calculatePromotionAmount(orderSkuList));
        if (param.getCouponId() == null) {
            order.setCouponAmount(new BigDecimal(0));
        } else {
            order.setUseCouponId(param.getCouponId());
            order.setCouponAmount(calculateCouponAmount(orderSkuList));
        }
        if (param.getUseIntegration() == null) {
            order.setIntegrationAmount(new BigDecimal(0));
        } else {
            order.setUseIntegration(param.getUseIntegration());
            order.setIntegrationAmount(calculateIntegrationAmount(orderSkuList));
        }
        order.setTotalAmount(calculatePayAmount(order));
        order.setCustomerId(param.getCustomerId());
        Customer customer = customerMapper.selectById(param.getCustomerId());
        if (customer == null) {
            throw new BusinessException(PortalErrorCode.USER_NOT_EXIST);
        }
        order.setCustomerName(customer.getUserName());
        order.setPayType(param.getPayType());
        //订单状态
        order.setStatus(OrderStatusEnum.NON_PAY.getValue());
        //设置订单类型 0-普通订单 1-秒杀订单
        order.setOrderType(orderTypeEnum.getValue());
        CustomerAddress customerAddress = addressService.getById(param.getAddressId());
        if (customerAddress == null) {
            throw new BusinessException(PortalErrorCode.ADDRESS_NOT_EXIST);
        }
        order.setReceiverName(customerAddress.getReceiverName());
        order.setReceiverProvince(customerAddress.getProvince());
        order.setReceiverCity(customerAddress.getCity());
        order.setReceiverRegion(customerAddress.getDistrict());
        order.setReceiverDetailAddress(customerAddress.getAddress());
        order.setReceiverPostCode(customerAddress.getZip());
        order.setReceiverPhone(customerAddress.getPhone());
        order.setConfirmStatus(BooleanEnum.NO.getValue());
        order.setDelStatus(DelFlagEnum.NO.getValue());
        order.setIntegrationAward(calculateIntegrationAward(orderSkuList));
        //order入库
        order.setCreateTime(LocalDateTime.now());
        order.setOrderNo(generateOrderNo(order));
        order.setParentOrderNo(parentOrderNO);
        OrderSetting orderSetting = orderSettingMapper.selectById(1L);
        order.setAutoConfirmDay(orderSetting.getConfirmOvertime());
        orderMapper.insert(order);
        List<OrderSku> orderSkuSaveList = Lists.newArrayList();
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            OrderSku orderSku = new OrderSku();
            BeanUtils.copyProperties(orderSkuPromotionVO, orderSku);
            orderSku.setOrderId(order.getId());
            orderSku.setOrderNo(order.getOrderNo());
            orderSku.setSkuName(orderSkuPromotionVO.getSpuName());
            orderSkuSaveList.add(orderSku);
        }
        //order_sku入库
        orderSkuMapper.insertList(orderSkuSaveList);
        return order.getId();
    }


    /**
     * 生成订单信息
     *
     * @param order
     * @return
     */
    private String generateOrderNo(Order order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_ORDER_ID.getRedisKey() + date;
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", BooleanEnum.NO.getValue()));
        sb.append(String.format("%02d", order.getPayType()));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

    /**
     * 计算积分数额
     *
     * @param orderSkuList
     * @return
     */
    private Integer calculateIntegrationAward(List<OrderSkuPromotionVO> orderSkuList) {
        Integer sum = 0;
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            sum += orderSkuPromotionVO.getIntegrationAward() * orderSkuPromotionVO.getQuantity();
        }
        return sum;
    }


    /**
     * 计算支付金额
     *
     * @param order
     * @return
     */
    private BigDecimal calculatePayAmount(Order order) {
        //总金额+运费-促销优惠-优惠券优惠-积分抵扣
        BigDecimal payAmount = order.getTotalAmount()
                .add(order.getDeliverFee())
                .subtract(order.getPromotionAmount())
                .subtract(order.getCouponAmount())
                .subtract(order.getIntegrationAmount());
        return payAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calculateIntegrationAmount(List<OrderSkuPromotionVO> orderSkuList) {
        BigDecimal integrationAmount = new BigDecimal(0);
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            if (orderSkuPromotionVO.getIntegrationAmount() != null) {
                integrationAmount = integrationAmount.add(orderSkuPromotionVO.getIntegrationAmount().multiply(new BigDecimal(orderSkuPromotionVO.getQuantity())));
            }
        }
        return integrationAmount.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算订单活动优惠
     *
     * @param orderItemList
     * @return
     */
    private BigDecimal calculatePromotionAmount(List<OrderSkuPromotionVO> orderItemList) {
        BigDecimal promotionAmount = new BigDecimal(0);
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderItemList) {
            if (orderSkuPromotionVO.getPromotionAmount() != null) {
                promotionAmount = promotionAmount.add(orderSkuPromotionVO.getPromotionAmount().multiply(new BigDecimal(orderSkuPromotionVO.getQuantity())));
            }
        }
        return promotionAmount.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 锁定库存
     *
     * @param cartPromotionVOList
     */
    private void lockStock(List<CartPromotionVO> cartPromotionVOList) {
        if (CollectionUtils.isEmpty(cartPromotionVOList)) {
            throw new BusinessException(PortalErrorCode.CART_CAN_NOT_EMPTY);
        }
        cartPromotionVOList.stream().forEach(cartPromotionVO -> {
            Sku sku = skuMapper.selectById(cartPromotionVO.getSkuId());
            sku.setLockStock(sku.getLockStock() + cartPromotionVO.getQuantity());
            skuMapper.updateById(sku);
        });
    }

    /**
     * 计算每项实际要支付金额
     *
     * @param orderSkuList
     */
    private void calculatePerRealAmount(List<OrderSkuPromotionVO> orderSkuList) {
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        orderSkuList.stream().forEach(orderSku -> {
            BigDecimal realAmount = orderSku.getPrice()
                    .subtract(orderSku.getPromotionAmount())
                    .subtract(orderSku.getCouponAmount())
                    .subtract(orderSku.getIntegrationAmount());
            orderSku.setRealAmount(realAmount.setScale(0, BigDecimal.ROUND_HALF_UP));
        });
    }

    /**
     * 处理积分问题
     *
     * @param customerId
     * @param useIntegration
     * @param orderSkuList
     * @param couponId
     */
    private void handleIntegration(Long customerId, Integer useIntegration, List<OrderSkuPromotionVO> orderSkuList, Long couponId) {
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        if (useIntegration == null || useIntegration.equals(0)) {
            //不使用积分
            orderSkuList.stream().forEach(x -> {
                x.setIntegrationAmount(new BigDecimal(0));
                x.setIntegrationAward(x.getPrice().intValue());
            });
        } else {
            //使用积分
            BigDecimal totalAmount = calculateTotalAmount(orderSkuList);
            BigDecimal integrationAmount = getUseIntegrationAmount(customerId, useIntegration, totalAmount, couponId != null);
            if (integrationAmount.compareTo(new BigDecimal(0)) > 0) {
                orderSkuList.stream().forEach(x -> {
                    BigDecimal perAmount = x.getPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(integrationAmount);
                    x.setIntegrationAmount(perAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
                    x.setIntegrationAward(x.getPrice().intValue());
                });
            }
        }
    }

    /**
     * 获取可用积分抵扣金额
     *
     * @param customerId
     * @param useIntegration
     * @param totalAmount
     * @param useCouponFlag
     * @return
     */
    private BigDecimal getUseIntegrationAmount(Long customerId, Integer useIntegration, BigDecimal totalAmount, Boolean useCouponFlag) {
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException(PortalErrorCode.USER_NOT_EXIST);
        }
        // 用户没有那么多积分
        if (useIntegration.compareTo(customer.getIntegration()) > 0) {
            throw new BusinessException(PortalErrorCode.INTEGRATION_NOT_ENOUGH);
        }
        IntegrationRuleSetting integrationRuleSetting = integrationRuleSettingMapper.selectById(1L);
        if (integrationRuleSetting == null) {
            throw new BusinessException(PortalErrorCode.INTEGRATION_RULE_NOT_EXIST);
        }
        //如果积分不可以和优惠券一起使用
        if (useCouponFlag && integrationRuleSetting.getCouponStatus().equals(BooleanEnum.NO.getValue())) {
            return new BigDecimal(0);
        }
        //如果没有达到积分所用的最小单位
        if (useIntegration.compareTo(integrationRuleSetting.getUseUnit()) < 0) {
            return new BigDecimal(0);
        }

        //计算积分抵扣额
        BigDecimal integrationAmount = new BigDecimal(useIntegration).divide(new BigDecimal(integrationRuleSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxPercent = new BigDecimal(integrationRuleSetting.getMaxPercentPerOrder()).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
        if (new BigDecimal(useIntegration).compareTo(totalAmount.multiply(maxPercent)) > 0) {
            //超过了积分抵扣上限
            throw new BusinessException(PortalErrorCode.INTEGRATION_CAN_NOT_USE);
        }

        return integrationAmount;
    }

    /**
     * 优惠券使用处理函数
     *
     * @param customerId
     * @param couponId
     * @param orderSkuList
     */
    private void handleCoupon(Long customerId, Long couponId, List<CartPromotionVO> cartList, List<OrderSkuPromotionVO> orderSkuList) {
        if (CollectionUtils.isEmpty(orderSkuList)) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        // couponId为空表示没有使用优惠券
        if (couponId == null) {
            orderSkuList.stream().forEach(x -> x.setCouponAmount(new BigDecimal(0)));
        } else {
            List<CouponCustomerDetailVO> couponCustomerList = couponService.listOrderAvailable(customerId, cartList, BooleanEnum.YES.getValue());
            if (CollectionUtils.isEmpty(couponCustomerList)) {
                throw new BusinessException(PortalErrorCode.COUPON_NOT_EXIST);
            }
            CouponCustomerDetailVO couponCustomerDetailVO = null;
            for (CouponCustomerDetailVO customerDetailVO : couponCustomerList) {
                if (customerDetailVO.getCouponId().equals(couponId)) {
                    couponCustomerDetailVO = customerDetailVO;
                }
            }
            if (couponCustomerDetailVO == null) {
                throw new BusinessException(PortalErrorCode.COUPON_NOT_EXIST);
            }
            Coupon coupon = couponCustomerDetailVO.getCoupon();
            if (coupon.getScopeType().equals(CouponScopeTypeEnum.ALL_SCENE.getValue())) {
                //全场通用
                calculatePerCouponAmount(orderSkuList, coupon);
            } else if (coupon.getScopeType().equals(CouponScopeTypeEnum.SPECIFIED_ITEM.getValue())) {
                //指定分类
                List<OrderSkuPromotionVO> couponOrderSkuList = getCouponOrderSkuByRelation(couponCustomerDetailVO, orderSkuList, 0);
                calculatePerCouponAmount(couponOrderSkuList, coupon);
            } else if (coupon.getScopeType().equals(CouponScopeTypeEnum.SPECIFIED_SPU.getValue())) {
                //指定商品
                List<OrderSkuPromotionVO> couponOrderSkuList = getCouponOrderSkuByRelation(couponCustomerDetailVO, orderSkuList, 1);
                calculatePerCouponAmount(couponOrderSkuList, coupon);
            }
        }
    }

    /**
     * 获取关联优惠券信息的order_sku信息
     *
     * @param couponCustomerDetailVO
     * @param orderSkuList
     * @param type                   1-指定类别   2-指定商品
     * @return
     */
    private List<OrderSkuPromotionVO> getCouponOrderSkuByRelation(CouponCustomerDetailVO couponCustomerDetailVO, List<OrderSkuPromotionVO> orderSkuList, Integer type) {
        List<OrderSkuPromotionVO> result = Lists.newArrayList();
        if (type.equals(CouponScopeTypeEnum.SPECIFIED_ITEM.getValue())) {
            List<Long> itemIdList = Lists.newArrayList();
            for (CouponItem couponItem : couponCustomerDetailVO.getCouponItemList()) {
                itemIdList.add(couponItem.getItemId());
            }
            for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
                if (itemIdList.contains(orderSkuPromotionVO.getItemId())) {
                    result.add(orderSkuPromotionVO);
                } else {
                    orderSkuPromotionVO.setCouponAmount(new BigDecimal(0));
                }
            }
        } else if (type.equals(CouponScopeTypeEnum.SPECIFIED_SPU.getValue())) {
            List<Long> spuIdList = Lists.newArrayList();
            for (CouponSpu couponSpu : couponCustomerDetailVO.getCouponSpuList()) {
                spuIdList.add(couponSpu.getSpuId());
            }
            for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
                if (spuIdList.contains(orderSkuPromotionVO.getSpuId())) {
                    result.add(orderSkuPromotionVO);
                } else {
                    orderSkuPromotionVO.setCouponAmount(new BigDecimal(0));
                }
            }
        }
        return result;
    }

    /**
     * 分摊优惠券优惠金额
     *
     * @param orderSkuList
     * @param coupon
     */
    private void calculatePerCouponAmount(List<OrderSkuPromotionVO> orderSkuList, Coupon coupon) {
        BigDecimal totalAmount = calculateTotalAmount(orderSkuList);
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            //(商品价格/可用商品总价)*优惠券面额
            BigDecimal couponAmount = orderSkuPromotionVO.getPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(coupon.getAmount());
            orderSkuPromotionVO.setCouponAmount(couponAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    /**
     * 计算订单优惠金额
     *
     * @param orderSkuList
     * @return
     */
    private BigDecimal calculateCouponAmount(List<OrderSkuPromotionVO> orderSkuList) {
        BigDecimal couponAmount = new BigDecimal(0);
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            if (orderSkuPromotionVO.getCouponAmount() != null) {
                couponAmount = couponAmount.add(orderSkuPromotionVO.getCouponAmount().multiply(new BigDecimal(orderSkuPromotionVO.getQuantity())));
            }
        }
        return couponAmount.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算总金额
     *
     * @param orderSkuList
     * @return
     */
    private BigDecimal calculateTotalAmount(List<OrderSkuPromotionVO> orderSkuList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderSkuPromotionVO orderSkuPromotionVO : orderSkuList) {
            totalAmount = totalAmount.add(orderSkuPromotionVO.getPrice().multiply(new BigDecimal(orderSkuPromotionVO.getQuantity())));
        }
        return totalAmount;
    }

    /**
     * 检查库存
     *
     * @param list
     */
    private void checkStock(List<CartPromotionVO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (CartPromotionVO cartPromotionVO : list) {
            if (cartPromotionVO.getSkuStock() == null || cartPromotionVO.getSkuStock() <= 0) {
                throw new BusinessException(PortalErrorCode.NOT_ENOUGH_STOCK);
            }
        }
    }

    private List<OrderSkuPromotionVO> getOrderSkuList(List<CartPromotionVO> list) {
        List<Long> spuIds = list.stream().map(CartPromotionVO::getSpuId).collect(Collectors.toList());
        List<Spu> spuList = spuMapper.selectBatchIds(spuIds);
        final Map<Long, Spu> spuMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Function.identity()));
        List<Long> skuIds = list.stream().filter(x -> x.getSkuId() != null).map(CartPromotionVO::getSkuId).collect(Collectors.toList());
        List<SpuImg> spuImgList = spuImgMapper.listBySkuIds(skuIds);
        Map<Long, String> imgMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(spuImgList)) {
            imgMap = spuImgList.stream().collect(Collectors.toMap(SpuImg::getSkuId, SpuImg::getImgUrl));
        }
        final Map<Long, String> skuImgMap = imgMap;
        List<OrderSkuPromotionVO> orderSkuPromotionVOList = list.stream().map(x -> {
            OrderSkuPromotionVO orderSkuPromotionVO = new OrderSkuPromotionVO();
            Spu spu = spuMap.get(x.getSpuId());
            orderSkuPromotionVO.setSpuId(x.getSpuId());
            orderSkuPromotionVO.setSkuId(x.getSkuId());
            orderSkuPromotionVO.setSkuSpec(x.getSkuSpec());
            orderSkuPromotionVO.setSkuPic(skuImgMap.get(x.getSkuId()));
            orderSkuPromotionVO.setBrandId(spu.getBrandId());
            orderSkuPromotionVO.setBrandName(spu.getBrandName());
            orderSkuPromotionVO.setSpuName(spu.getName());
            orderSkuPromotionVO.setItemId(x.getItemId());
            orderSkuPromotionVO.setShopId(x.getShopId());
            orderSkuPromotionVO.setQuantity(x.getQuantity());
            orderSkuPromotionVO.setPrice(x.getPrice());
            orderSkuPromotionVO.setPromotionName(PromotionTypeEnum.getNameFromType(x.getPromotionType()));
            orderSkuPromotionVO.setPromotionAmount(x.getPerReduceAmount());
            return orderSkuPromotionVO;
        }).collect(Collectors.toList());
        return orderSkuPromotionVOList;
    }


    @Override
    public IPage<OrderSimpleVO> pageOrder(SelfOrderSearchDTO param) {
        log.info("pageOrder, OrderSearchDTO param = {}", param);
        List<OrderSku> orderSkuList = orderSkuMapper.listByOrderNo(param.getOrderNo());
        Set<Long> orderIdSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(orderSkuList)) {
            orderIdSet = orderSkuList.stream().map(OrderSku::getOrderId).collect(Collectors.toSet());
        }
        param.setOrderIds(orderIdSet);
        Page<Order> orderPage = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<Order> orderIPage = orderMapper.pageOrder(orderPage, param);
        if (orderIPage == null || CollectionUtils.isEmpty(orderIPage.getRecords())) {
            IPage<OrderSimpleVO> orderSimpleVOIPage = new Page<>();
            orderSimpleVOIPage.setCurrent(param.getPageNum());
            orderSimpleVOIPage.setSize(param.getPageSize());
            orderSimpleVOIPage.setRecords(Lists.newArrayList());
            orderSimpleVOIPage.setTotal(0L);
            return orderSimpleVOIPage;
        }
        List<Order> records = orderIPage.getRecords();
        List<Long> orderIds = records.stream().map(Order::getId).collect(Collectors.toList());
        List<KV<Long,String>> kvList = orderSkuMapper.listCoverImgByOrderIds(orderIds);
        final Map<Long, String> coverImgMap = kvList.stream().collect(Collectors.toMap(KV::getK, KV::getV, (x1, x2)->x2));
        return orderIPage.convert(x -> {
            OrderSimpleVO orderSimpleVO = new OrderSimpleVO();
            orderSimpleVO.setCoverImg(coverImgMap.get(x.getId()));
            BeanUtils.copyProperties(x, orderSimpleVO);
            return orderSimpleVO;
        });
    }

    @Override
    public Boolean removeOrder(IdDTO param) {
        Order record = new Order();
        record.setDelStatus(BooleanEnum.YES.getValue());
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Order::getId, param.getId());
        wrapper.eq(Order::getDelStatus, BooleanEnum.NO.getValue());
        int res = baseMapper.update(record, wrapper);
        return res > 0;
    }

    @Override
    public OrderDetailVO getOrderDetailById(IdDTO param) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        Order order = orderMapper.selectById(param.getId());
        BeanUtils.copyProperties(order, orderDetailVO);
        List<OrderSku> orderSkus = orderSkuMapper.listByOrderId(param.getId());
        orderDetailVO.setOrderSkuList(orderSkus);
        return orderDetailVO;
    }


    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        //1、查看订单状态，如果已经支付了，就不做处理
        Order order = orderMapper.selectById(orderId);
        if (!order.getStatus().equals(OrderStatusEnum.NON_PAY.getValue())) {
            return;
        }
        //2、修改订单状态
        Order updateOrder = new Order();
        updateOrder.setId(orderId);
        updateOrder.setUpdateTime(LocalDateTime.now());
        updateOrder.setStatus(OrderStatusEnum.CLOSED.getValue());
        orderMapper.updateById(updateOrder);
        //3、释放库存
        List<OrderSku> orderSkuList = orderSkuMapper.listByOrderId(orderId);
        if (CollectionUtils.isNotEmpty(orderSkuList)) {
            orderSkuMapper.releaseLockStock(orderSkuList);
        }

        //4、如果使用了优惠券 返还优惠券
        if (order.getUseCouponId() != null) {
            couponService.updateCouponStatus(order.getCustomerId(), order.getUseCouponId(), BooleanEnum.NO.getValue());
        }
    }

    public void returnCustomerIntegration(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        Customer customer = customerMapper.selectById(order.getCustomerId());
        customerMapper.updateIntegration(order.getCustomerId(), customer.getIntegration() + order.getUseIntegration());
    }

    @Override
    public Boolean getOrderStatus(ParentOrderNoDTO param) {
        List<Order> orderList = orderMapper.getByParentOrderNo(param.getCustomerId(), param.getParentOrderNo());
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException(PortalErrorCode.ORDER_NOT_EXIST);
        }
        for (Order order : orderList) {
            if (!order.getStatus().equals(OrderStatusEnum.NON_DELIVER)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
