package com.rainbow.admin.controller;


import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.IdNameAvatarVO;
import com.rainbow.common.vo.IdNameTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 后台管理用户表 前端控制器
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/administrator")
@Api(value = "/administrator", tags = "管理员相关")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;


    @ApiOperation(value = "登陆", notes = "通过用户名登陆", httpMethod = "POST")
    @PostMapping("/login")
    public R<IdNameTokenVO> login(@Valid @RequestBody LoginDTO loginRequest, HttpServletResponse httpResponse) {
        return new R<>(administratorService.loginByPassword(loginRequest, httpResponse));
    }


    @ApiOperation(value = "登陆", notes = "通过用户名登陆", httpMethod = "POST")
    @GetMapping("/info")
    public R<IdNameAvatarVO> info() {
        return new R<>();
    }



}

