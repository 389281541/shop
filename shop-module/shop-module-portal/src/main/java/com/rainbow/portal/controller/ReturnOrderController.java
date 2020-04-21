package com.rainbow.portal.controller;

import com.rainbow.api.dto.ReturnOrderDTO;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IReturnOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 退单服务
 *
 * @author lujinwei
 * @since 2020/3/28
 */
@RestController
@RequestMapping("/returnOrder")
@Api(value = "/returnOrder", tags = "订单服务")
public class ReturnOrderController {

    @Autowired
    private IReturnOrderService returnOrderService;

    @ApiOperation(value = "退单", notes = "退单", httpMethod = "POST")
    @PostMapping("/create")
    @NeedLogin
    public R<Integer> pageOrder(@Valid @RequestBody ReturnOrderDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(returnOrderService.create(param));
    }
}
