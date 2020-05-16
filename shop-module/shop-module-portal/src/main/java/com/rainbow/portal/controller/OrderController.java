package com.rainbow.portal.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.dto.SelfOrderSearchDTO;
import com.rainbow.api.vo.ConfirmOrderVO;
import com.rainbow.api.vo.OrderDetailVO;
import com.rainbow.api.vo.OrderSimpleVO;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单表 前端控制器
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/order")
@Api(value = "/order", tags = "订单服务")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @ApiOperation(value = "生成确认订单", notes = "生成确认订单", httpMethod = "POST")
    @PostMapping("/generateConfirmOrder")
    @NeedLogin
    public R<ConfirmOrderVO> generateConfirmOrder() {
        return new R<>(orderService.generateConfirmOrder(RB.getUserId()));
    }


    @ApiOperation(value = "生成订单", notes = "生成订单", httpMethod = "POST")
    @PostMapping("/generateOrder")
    @NeedLogin
    public R<String> generateOrder(@Valid @RequestBody OrderGenerateDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(orderService.generateOrder(param));
    }


    @ApiOperation(value = "订单列表", notes = "订单列表", httpMethod = "POST")
    @PostMapping("/page")
    @NeedLogin
    public R<IPage<OrderSimpleVO>> pageOrder(@Valid @RequestBody SelfOrderSearchDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(orderService.pageOrder(param));
    }


    @ApiOperation(value = "订单详情", notes = "订单详情", httpMethod = "POST")
    @PostMapping("/get")
    @NeedLogin
    public R<OrderDetailVO> pageOrder(@Valid @RequestBody IdDTO param) {
        return new R<>(orderService.getOrderDetailById(param));
    }


    @ApiOperation(value = "删除订单", notes = "删除订单", httpMethod = "POST")
    @PostMapping("/remove")
    @NeedLogin
    public R<Boolean> removeOrder(@Valid @RequestBody IdDTO param) {
        return new R<>(orderService.removeOrder(param));
    }


    @ApiOperation(value = "订单状态", notes = "订单状态", httpMethod = "POST")
    @PostMapping("/status")
    @NeedLogin
    public R<Boolean> status(@Valid @RequestBody ParentOrderNoDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(orderService.getOrderStatus(param));
    }

}

