package com.rainbow.admin.controller;


import com.rainbow.admin.module.LoginDTO;
import com.rainbow.common.dto.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 后台管理用户表 前端控制器
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDTO req) {

        return null;
    }


}

