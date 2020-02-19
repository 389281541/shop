package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.CouponSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 优惠券和商品的关系表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@DS("goods")
public interface CouponSpuMapper extends BaseMapper<CouponSpu> {

    /**
     * 删除优惠券关联表
     * @param couponId
     * @return
     */
    Integer removeByCouponId(@Param("couponId") Long couponId);
}
