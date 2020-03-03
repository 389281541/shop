package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.CouponSaveDTO;
import com.rainbow.api.dto.CouponSearchDTO;
import com.rainbow.api.dto.CouponUpdateDTO;
import com.rainbow.api.vo.CouponDetailVO;
import com.rainbow.api.vo.CouponSimpleVO;
import com.rainbow.admin.service.ICouponService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
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
 * @since 2020-02-19
 */
@RestController
@RequestMapping("/coupon")
@Api(value = "/coupon", tags = "优惠券服务")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @ApiOperation(value = "优惠券列表", notes = "优惠券列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<CouponSimpleVO>> page(@Valid @RequestBody CouponSearchDTO param) {
        return new R<>(couponService.pageCoupon(param));
    }

    @ApiOperation(value = "添加优惠券", notes = "添加优惠券", httpMethod = "POST")
    @PostMapping("/add")
    public R<Boolean> add(@Valid @RequestBody CouponSaveDTO param) {
        Integer result = couponService.addCoupon(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除优惠券", notes = "删除优惠券", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = couponService.removeCoupon(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "修改优惠券", notes = "修改优惠券", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody CouponUpdateDTO param) {
        Integer result = couponService.updateCoupon(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "优惠券详情", notes = "优惠券详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<CouponDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(couponService.getCouponDetailById(param));
    }
}

