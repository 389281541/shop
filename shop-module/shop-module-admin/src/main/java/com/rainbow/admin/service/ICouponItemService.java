package com.rainbow.admin.service;

import com.rainbow.admin.api.entity.CouponItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 优惠券和商品分类关系表 服务类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
public interface ICouponItemService extends IService<CouponItem> {

    /**
     * 通过couponId删除优惠券和商品分类关系
     * @param couponId
     * @return
     */
    Integer removeByCouponId(Long couponId);


    /**
     * 优惠券商品分类关系表
     * @param couponId
     * @return
     */
    List<CouponItem> listByCouponId(Long couponId);
}
