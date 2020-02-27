package com.rainbow.admin.controller;


import com.rainbow.admin.api.dto.SkuBatchUpdateDTO;
import com.rainbow.admin.api.vo.SkuSimpleVO;
import com.rainbow.admin.service.ISkuService;
import com.rainbow.common.dto.IdSearchDTO;
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
 * 商品sku表 前端控制器
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/sku")
@Api(value = "/sku", tags = "sku服务")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    @ApiOperation(value = "sku列表", notes = "sku列表", httpMethod = "POST")
    @PostMapping("/list")
    public R<List<SkuSimpleVO>> list(@Valid @RequestBody IdSearchDTO param) {
        return new R<>(skuService.listBySpuId(param));
    }

    @ApiOperation(value = "批量更新sku", notes = "批量更新sku", httpMethod = "POST")
    @PostMapping("/updateBatch")
    public R<Boolean> updateBatch(@Valid @RequestBody SkuBatchUpdateDTO param) {
        return new R<>(skuService.updateBatch(param));
    }


}

