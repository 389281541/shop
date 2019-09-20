package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdNamePageDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IBrandService brandService;

    @ApiOperation(value = "品牌列表", notes = "品牌列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<BrandSimpleVO>> pageBrandByItem(@Valid @RequestBody IdNamePageDTO param) {
        return new R<>(brandService.pageBrandByItem(param));
    }

    @ApiOperation(value = "添加品牌", notes = "添加品牌", httpMethod = "POST")
    @PostMapping("/add")
    public R<IPage<BrandSimpleVO>> add(@Valid @RequestBody IdPageDTO param) {
        return new R<>(brandService.pageBrandByItem(param));
    }

}

