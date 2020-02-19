package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.CouponSaveDTO;
import com.rainbow.admin.api.dto.CouponSearchDTO;
import com.rainbow.admin.api.dto.CouponUpdateDTO;
import com.rainbow.admin.api.vo.CouponDetailVO;
import com.rainbow.admin.api.vo.CouponSimpleVO;
import com.rainbow.admin.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;

/**
 * 优惠券表 服务类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
public interface ICouponService extends IService<Coupon> {

    /**
     * 优惠券列表
     * @param param
     * @return
     */
    IPage<CouponSimpleVO> pageCoupon(CouponSearchDTO param);

    /**
     * 添加优惠券
     * @param param
     * @return
     */
    Integer addCoupon(CouponSaveDTO param);


    /**
     * 删除优惠券
     * @param param
     * @return
     */
    Integer removeCoupon(IdDTO param);


    /**
     * 更新优惠券
     * @param param
     * @return
     */
    Integer updateCoupon(CouponUpdateDTO param);


    /**
     * 获取优惠券详情
     * @param param
     * @return
     */
    CouponDetailVO getCouponDetailById(IdDTO param);
}
