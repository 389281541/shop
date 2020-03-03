package com.rainbow.admin.controller;


import com.rainbow.api.dto.OrderSettingDTO;
import com.rainbow.api.vo.OrderSettingVO;
import com.rainbow.admin.service.IOrderSettingService;
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
 * 订单设置表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/orderSetting")
@Api(value = "/orderSetting", tags = "订单设置服务")
public class OrderSettingController {

    @Autowired
    private IOrderSettingService orderSettingService;

    @ApiOperation("获取订单详情")
    @PostMapping(value = "/get")
    public R<OrderSettingVO> get(@Valid @RequestBody IdDTO param) {
        return new R<>(orderSettingService.getDetailById(param));
    }

    @ApiOperation("修改收货人信息")
    @PostMapping(value = "/update")
    public R<Integer> update(@Valid @RequestBody OrderSettingDTO param) {
        return new R<>(orderSettingService.updateOrderSetting(param));
    }
}

