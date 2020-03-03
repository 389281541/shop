package com.rainbow.admin.controller;


import com.rainbow.api.vo.AdminstratorSimpleVO;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.common.dto.LoginDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.IdNameTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

/**
 * 后台管理用户表 前端控制器
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/admin")
@Api(value = "/admin", tags = "管理员相关")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;


    @ApiOperation(value = "登陆", notes = "通过用户名登陆", httpMethod = "POST")
    @PostMapping("/login")
    public R<IdNameTokenVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return new R<>(administratorService.loginByPassword(loginDTO.getUsername(), loginDTO.getPassword()));
    }


    @ApiOperation(value = "退出", notes = "退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public R<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        return new R<>(administratorService.logout(request, response));
    }


    @ApiOperation(value = "用户信息", notes = "用户信息", httpMethod = "GET")
    @GetMapping("/info")
    public R<AdminstratorSimpleVO> info(Principal principal) {
        String username = principal.getName();
        return new R<>(administratorService.getInfoByUserName(username));
    }



}

