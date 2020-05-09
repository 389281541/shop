package com.rainbow.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.dto.SelfOrderSearchDTO;
import com.rainbow.api.entity.Order;
import com.rainbow.api.enums.OrderTypeEnum;
import com.rainbow.api.vo.*;
import com.rainbow.common.dto.IdDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单表 服务类
 *
 * @author lujinwei
 * @since 2020-03-18
 */
public interface IOrderService extends IService<Order> {

    /**
     * 生成确认订单
     * @return
     */
    ConfirmOrderVO generateConfirmOrder(Long customerId);

    /**
     * 生成订单
     * @param param
     * @return
     */
    String generateOrder(OrderGenerateDTO param);


    /**
     * 订单分页
     * @param param
     * @return
     */
    IPage<OrderSimpleVO> pageOrder(SelfOrderSearchDTO param);


    /**
     * 移除订单
     * @param param
     * @return
     */
    Boolean removeOrder(IdDTO param);

    /**
     * 获取订单详情
     * @param param
     * @return
     */
    OrderDetailVO getOrderDetailById(IdDTO param);


    /**
     * 取消订单
     * @param orderId
     */
    @Transactional
    void cancelOrder(Long orderId);


    /**
     * 获取订单状态
     * @param param
     * @return
     */
    Boolean getOrderStatus(ParentOrderNoDTO param);

    /**
     * 计算可用积分最大值
     * @param cartPromotionVOList
     * @param customerId
     * @return
     */
    Integer calculateMaxCanUseIntegration(List<CartPromotionVO> cartPromotionVOList, Long customerId);


    /**
     * 转换coupon
     * @param couponCustomerDetailVOList
     * @return
     */
    List<CouponSimpleVO> convertCoupon(List<CouponCustomerDetailVO> couponCustomerDetailVOList);

    /**
     * 按店铺ID生成订单
     * @param cartPromotionVOList
     * @param param
     * @param parentOrderNo
     * @param orderTypeEnum
     * @return
     */
    Boolean generateOrderByShopId(List<CartPromotionVO> cartPromotionVOList, OrderGenerateDTO param, String parentOrderNo, OrderTypeEnum orderTypeEnum);
}
