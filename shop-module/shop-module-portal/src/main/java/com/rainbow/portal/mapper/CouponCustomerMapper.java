package com.rainbow.portal.mapper;

import com.rainbow.api.entity.CouponCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.vo.CouponCustomerDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券使用、领取历史表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@DS("goods")
public interface CouponCustomerMapper extends BaseMapper<CouponCustomer> {

    /**
     * 获取优惠券详情列表
     * @param customerId
     * @return
     */
    List<CouponCustomerDetailVO> listDetailByCustomerId(@Param("customerId") Long customerId);

    /**
     * 更新优惠券使用状态
     * @param customerId
     * @param couponId
     * @return
     */
    Integer updateCouponStatus(@Param("customerId") Long customerId, @Param("couponId") Long couponId, @Param("useStatus") Integer useStatus);


    /**
     * 获取优惠券领取数量
     * @param customerId
     * @param couponId
     * @return
     */
    Integer getReceiveNumByCustomerId(@Param("customerId") Long customerId, @Param("couponId") Long couponId);
}
