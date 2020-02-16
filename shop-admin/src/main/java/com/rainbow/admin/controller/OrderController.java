package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.OrderDetailVO;
import com.rainbow.admin.api.vo.OrderSimpleVO;
import com.rainbow.admin.service.IOrderService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdsDTO;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 订单表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/order")
@Api(value = "/order", tags = "订单服务")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public R<IPage<OrderSimpleVO>> page(@Valid @RequestBody OrderSearchDTO param) {
        return new R<>(orderService.pageOrder(param));
    }

    @ApiOperation("批量发货")
    @PostMapping(value = "/deliver")
    public R<Integer> deliver(@Valid @RequestBody DeliverBatchDTO param) {
        return new R<>(orderService.deliverOrder(param.getDeliverList()));
    }

    @ApiOperation("批量关闭订单")
    @PostMapping(value = "/close")
    public R<Integer> close(@Valid @RequestBody OrderCloseDTO param) {
        return new R<>(orderService.closeOrder(param));
    }

    @ApiOperation("批量删除订单")
    @PostMapping(value = "/remove")
    public R<Integer> remove(@Valid @RequestBody IdsDTO param) {
        return new R<>(orderService.removeOrder(param));
    }

    @ApiOperation("获取订单详情")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public R<OrderDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(orderService.getDetailById(param));
    }

    @ApiOperation("修改收货人信息")
    @PostMapping(value = "/changeReceiverInfo")
    public R<Integer> changeReceiverInfo(@Valid @RequestBody ReceiverInfoChangeDTO param) {
        return new R<>(orderService.changeReceiverInfo(param));
    }

    @ApiOperation("修改订单费用信息")
    @PostMapping(value = "/changeMoneyInfo")
    public R<Integer> changeMoneyInfo(@Valid @RequestBody MoneyInfoChangeDTO param) {
        return new R<>(orderService.changeMoneyInfo(param));
    }

    @ApiOperation("备注订单")
    @PostMapping(value = "/updateNote")
    public R<Integer> changeNote(@Valid @RequestBody NoteChangeDTO param) {
        return new R<>(orderService.changeNote(param));
    }
}

