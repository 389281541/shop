package com.rainbow.admin.service;

import com.rainbow.admin.entity.CouponSpu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 优惠券和商品的关系表 服务类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
public interface ICouponSpuService extends IService<CouponSpu> {


    /**
     * 通过couponId删除关联表
     * @param couponId
     * @return
     */
    Integer removeByCouponId(Long couponId);

    /**
     * 查询优惠券关联商品
     * @param couponId
     * @return
     */
    List<CouponSpu> listByCouponId(Long couponId);

}
