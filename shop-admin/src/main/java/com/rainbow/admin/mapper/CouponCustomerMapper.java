package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.CouponCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 优惠券使用、领取历史表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@DS("goods")
public interface CouponCustomerMapper extends BaseMapper<CouponCustomer> {

}
