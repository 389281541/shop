package com.rainbow.portal.mapper;

import com.rainbow.api.entity.CouponItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 优惠券和商品分类关系表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@DS("goods")
public interface CouponItemMapper extends BaseMapper<CouponItem> {

}
