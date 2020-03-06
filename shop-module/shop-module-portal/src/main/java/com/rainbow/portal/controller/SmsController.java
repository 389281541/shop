package com.rainbow.portal.controller;


import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.annotation.NeedLoginLevelEnum;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.SmsSendDTO;
import com.rainbow.common.dto.SmsVerifyDTO;
import com.rainbow.portal.service.ISmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
@Api(value = "/sms", tags = "用户服务")
public class SmsController {

    @Autowired
    private ISmsService smsService;

    @ApiOperation(value = "发送验证码", notes = "发送验证码", httpMethod = "POST")
    @PostMapping("/send")
    @NeedLogin
    public R<Boolean> send(@Valid @RequestBody SmsSendDTO param) {
        return new R<>(smsService.sendVerifyCode(param.getMobile()));
    }

    @ApiOperation(value = "短信验证", notes = "短信验证", httpMethod = "POST")
    @PostMapping("/verify")
    public R<Boolean> smsVerify(@Valid @RequestBody SmsVerifyDTO param) {
        return new R<>(smsService.verify(param));
    }


}
