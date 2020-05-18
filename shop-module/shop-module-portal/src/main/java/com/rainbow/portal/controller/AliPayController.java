package com.rainbow.portal.controller;

import com.rainbow.api.dto.AliPayNotifyDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IAliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 支付接口
 *
 * @author lujinwei
 * @since 2020/4/7
 */
@RestController
@RequestMapping("/aliPay")
@Api(value = "/aliPay", tags = "支付服务")
public class AliPayController {

    @Autowired
    private IAliPayService aliPayService;

    @ApiOperation(value = "创建支付二维码", notes = "创建支付二维码", httpMethod = "POST")
    @PostMapping("/goPay")
    @NeedLogin
    public R<String> goPay(@Valid @RequestBody ParentOrderNoDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(aliPayService.goPay(param));
    }


    @ApiOperation(value = "支付宝支付成功回调", notes = "支付宝支付成功回调", httpMethod = "POST")
    @PostMapping("/callback")
    public R<Boolean> alipayCallback(@Valid @RequestBody AliPayNotifyDTO param) {
        return new R<>(aliPayService.paySucessCallback(RB.getUserId(), param));
    }

}
