package com.rainbow.admin.controller;


import com.rainbow.common.dto.IdPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 品牌表 前端控制器
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@RestController
@RequestMapping("/brand")
@Api(value = "/brand", tags = "品牌服务")
public class BrandController {

//    @ApiOperation(value = "品牌列表", notes = "品牌列表", httpMethod = "POST")
//    @PostMapping("/page")
//    public R<IPage<ItemSimpleVO>> pageParentItem(@Valid @RequestBody IdPageDTO param) {
//        return new R<>(itemService.pageParentItem(param));
//    }

}

