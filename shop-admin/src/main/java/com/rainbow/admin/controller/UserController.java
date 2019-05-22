package com.rainbow.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller类
 *
 * @author lujinwei
 * @Date 2019-05-21 09:44
 */
@Api(value = "/user", position = 1, tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "测试swagger",notes = "测试用",httpMethod = "POST")
    @PostMapping("/test")
    public void testSwagger() {
        System.out.println("hello,world");
    }

}
