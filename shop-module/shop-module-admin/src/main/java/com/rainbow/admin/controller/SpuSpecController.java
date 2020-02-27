package com.rainbow.admin.controller;


import com.rainbow.admin.api.vo.SpuSpecSimpleVO;
import com.rainbow.admin.service.ISpuSpecService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品基本属性表 前端控制器
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/spuSpec")
@Api(value = "/spuSpec", tags = "spu属性服务")
public class SpuSpecController {

    @Autowired
    private ISpuSpecService spuSpecService;

    @ApiOperation(value = "spu规格表", notes = "spu列表", httpMethod = "POST")
    @PostMapping("/listBySpuId")
    public R<List<SpuSpecSimpleVO>> listByItem(@Valid @RequestBody IdDTO param) {
        return new R<>(spuSpecService.listBySpuId(param.getId()));
    }
}

