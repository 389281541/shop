package com.rainbow.portal.controller;


import com.rainbow.api.dto.CustomerRegisterDTO;
import com.rainbow.common.dto.LoginDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.IdNameAvatarTokenVO;
import com.rainbow.portal.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 顾客登录表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@RestController
@RequestMapping("/customer")
@Api(value = "/customer", tags = "用户服务")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @ApiOperation(value = "登陆", notes = "通过用户名登陆", httpMethod = "POST")
    @PostMapping("/login")
    public R<IdNameAvatarTokenVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return new R<>(customerService.loginByPassword(loginDTO.getUsername(), loginDTO.getPassword()));
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆", httpMethod = "POST")
    @PostMapping("/logout")
    public R<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        return new R<>(customerService.logout(request, response));
    }

    @ApiOperation(value = "注册", notes = "注册", httpMethod = "POST")
    @PostMapping("/register")
    public R<Boolean> register(@Valid @RequestBody CustomerRegisterDTO param) {
        return new R<>(customerService.register(param));
    }


}

