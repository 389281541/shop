package com.rainbow.admin.controller;

import com.rainbow.admin.api.dto.VerifyCodeDTO;
import com.rainbow.admin.service.IKaptchaService;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 验证码控制器
 *
 * @author lujinwei
 * @since 2019-06-23
 */
@Slf4j
@RestController
@RequestMapping("/kaptcha")
@Api(value = "/kaptcha", tags = "验证码服务")
public class KaptchaController {

    @Autowired
    private IKaptchaService kaptchaService;

    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "GET")
    @GetMapping("/get")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        kaptchaService.getCaptcha(request, response);
    }


    @ApiOperation(value = "校验验证码", notes = "返回值0-验证成功，1-验证失败，2-验证码过期", httpMethod = "POST")
    @PostMapping("/verify")
    public R<IdNameVO> verifyKaptcha(HttpServletRequest request, @RequestBody @Valid VerifyCodeDTO req) {
        return new R<>(kaptchaService.verifyKaptcha(request, req));
    }
}
