package com.rainbow.admin.controller;


import com.rainbow.admin.service.ISpecValueService;
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
 * 规格值表 前端控制器
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/specValue")
@Api(value = "/specValue", tags = "属性值服务")
public class SpecValueController {

    @Autowired
    private ISpecValueService specValueService;

//    @ApiOperation(value = "属性名列表", notes = "属性名列表", httpMethod = "POST")
//    @PostMapping("/page")
//    public R<IPage<SpecValueSimpleVO>> page(@Valid @RequestBody IdPageDTO param) {
//        return new R<>(specNameService.pageSpecName(param));
//    }
//
//    @ApiOperation(value = "属性名添加", notes = "属性名添加", httpMethod = "POST")
//    @PostMapping("/add")
//    public R<Integer> save(@Valid @RequestBody SpecNameSaveDTO param) {
//        return new R<>(specNameService.saveSpecName(param));
//    }
//
//    @ApiOperation(value = "属性名更新", notes = "属性名更新", httpMethod = "POST")
//    @PostMapping("/update")
//    public R<Boolean> update(@Valid @RequestBody SpecNameUpdateDTO param) {
//        Integer count = specNameService.updateSpecName(param);
//        return new R<>(count > 0 ? Boolean.TRUE : Boolean.FALSE);
//    }
//
//
//    @ApiOperation(value = "属性名详情", notes = "属性名详情", httpMethod = "POST")
//    @PostMapping("/detail")
//    public R<SpecNameDetailVO> detail(@Valid @RequestBody IdDTO param) {
//        return new R<>(specNameService.getSpecNameDetailById(param));
//    }
//
//
//    @ApiOperation(value = "属性名移除", notes = "属性名移除", httpMethod = "POST")
//    @PostMapping("/remove")
//    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
//        Integer result = specNameService.removeSpecName(param);
//        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
//    }

}
