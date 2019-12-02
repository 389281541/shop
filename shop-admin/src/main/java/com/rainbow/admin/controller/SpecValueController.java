package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpecValueSaveDTO;
import com.rainbow.admin.api.dto.SpecValueUpdateDTO;
import com.rainbow.admin.api.vo.SpecValueDetailVO;
import com.rainbow.admin.api.vo.SpecValuePageVO;
import com.rainbow.admin.api.vo.SpecValueSimpleVO;
import com.rainbow.admin.service.ISpecValueService;
import com.rainbow.common.dto.IdDTO;
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

    @ApiOperation(value = "属性名列表", notes = "属性名列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<SpecValuePageVO> page(@Valid @RequestBody IdPageDTO param) {
        return new R<>(specValueService.pageSpecValue(param));
    }

    @ApiOperation(value = "属性名添加", notes = "属性名添加", httpMethod = "POST")
    @PostMapping("/add")
    public R<Integer> save(@Valid @RequestBody SpecValueSaveDTO param) {
        return new R<>(specValueService.saveSpecValue(param));
    }

    @ApiOperation(value = "属性名更新", notes = "属性名更新", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody SpecValueUpdateDTO param) {
        Integer count = specValueService.updateSpecValue(param);
        return new R<>(count > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "属性名详情", notes = "属性名详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<SpecValueDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(specValueService.getSpecValueDetailById(param));
    }


    @ApiOperation(value = "属性名移除", notes = "属性名移除", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = specValueService.removeSpecValue(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

}

