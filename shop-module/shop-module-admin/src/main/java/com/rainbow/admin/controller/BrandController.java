package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.BrandSaveDTO;
import com.rainbow.admin.api.dto.BrandUpdateDTO;
import com.rainbow.admin.api.vo.BrandDetailVO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
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
    public R<Boolean> add(@Valid @RequestBody BrandSaveDTO param) {
        Integer result = brandService.addBrand(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除品牌", notes = "删除品牌", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = brandService.removeBrand(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "更改品牌", notes = "更改品牌", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody BrandUpdateDTO param) {
        return new R<>(brandService.updateBrand(param));
    }

    @ApiOperation(value = "品牌详情", notes = "品牌详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<BrandDetailVO> update(@Valid @RequestBody IdDTO param) {
        return new R<>(brandService.getBrandDetailById(param));
    }


}

