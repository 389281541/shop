package com.rainbow.admin.mapper;

import com.rainbow.admin.api.entity.CouponItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 优惠券和商品分类关系表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@DS("goods")
public interface CouponItemMapper extends BaseMapper<CouponItem> {

    Integer removeByCouponId(@Param("couponId") Long couponId);
}
