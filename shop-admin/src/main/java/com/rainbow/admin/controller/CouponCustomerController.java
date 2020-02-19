package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.CouponCustomerSearchDTO;
import com.rainbow.admin.api.vo.CouponCustomerSimpleVO;
import com.rainbow.admin.service.ICouponCustomerService;
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
 * 优惠券使用、领取历史表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@RestController
@RequestMapping("/couponCustomer")
@Api(value = "/couponCustomer", tags = "优惠券领取服务")
public class CouponCustomerController {

    @Autowired
    private ICouponCustomerService couponCustomerService;

    @ApiOperation(value = "优惠券用户列表", notes = "优惠券用户列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<CouponCustomerSimpleVO>> page(@Valid @RequestBody CouponCustomerSearchDTO param) {
        return new R<>(couponCustomerService.pageCouponCustomer(param));
    }

}

