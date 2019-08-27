package com.rainbow.admin.controller;


import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.service.IUserService;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.IdNameTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 后台管理用户控制器
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "用户相关")
public class UserController {

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "登陆", notes = "通过用户名登陆", httpMethod = "POST")
    @PostMapping("/login")
    public R<IdNameTokenVO> login(@Valid @RequestBody LoginDTO req) {
        return new R<>(userService.loginByPassword(req));
    }

}

