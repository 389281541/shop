package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.CouponCustomerSearchDTO;
import com.rainbow.admin.api.entity.CouponCustomer;
import com.rainbow.admin.api.vo.CouponCustomerSimpleVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 优惠券使用、领取历史表 服务类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
public interface ICouponCustomerService extends IService<CouponCustomer> {

    /**
     * 优惠券领取列表
     * @param param
     * @return
     */
    IPage<CouponCustomerSimpleVO> pageCouponCustomer(CouponCustomerSearchDTO param);
}
