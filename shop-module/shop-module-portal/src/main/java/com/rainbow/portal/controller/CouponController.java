package com.rainbow.portal.controller;


import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.ICouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 优惠券表 前端控制器
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/coupon")
@Api(value = "/coupon", tags = "优惠券服务")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @ApiOperation(value = "领取优惠券", notes = "领取优惠券", httpMethod = "POST")
    @PostMapping("/collect")
    public R<Boolean> collect(@Valid @RequestBody IdDTO param) {
        Integer result = couponService.collectCoupon(param.getId(), RB.getUserId());
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

}

