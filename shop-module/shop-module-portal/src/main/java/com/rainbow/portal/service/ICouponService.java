package com.rainbow.portal.service;

import com.rainbow.api.vo.CartPromotionVO;
import com.rainbow.api.vo.CouponCustomerDetailVO;
import com.rainbow.api.vo.CouponCustomerSimpleVO;

import java.util.List;

/**
 * 优惠券 service
 *
 * @author lujinwei
 * @since 2020/3/19
 */
public interface ICouponService {

    /**
     * 获取订单可用或不可用优惠券列表
     * @param customerId 用户ID
     * @param list 促销购物车列表
     * @param type 0-不可用的  1-可用的
     * @return
     */
    List<CouponCustomerDetailVO> listOrderAvailable(Long customerId, List<CartPromotionVO> list, Integer type);


    /**
     * 更新优惠券状态
     * @param customerId 用户ID
     * @param couponId 优惠券ID
     * @param useStatus 使用状态 0-未使用 1-已使用
     * @return
     */
    Integer updateCouponStatus(Long customerId, Long couponId, Integer useStatus);

}
