package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.SpuSaveDTO;
import com.rainbow.api.dto.SpuSearchDTO;
import com.rainbow.api.dto.SpuUpdateDTO;
import com.rainbow.api.vo.SpuDetailVO;
import com.rainbow.api.vo.SpuSimpleVO;
import com.rainbow.admin.service.ISpuService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.StatusBatchChangeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品表 前端控制器
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/spu")
@Api(value = "/spu", tags = "spu服务")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    @ApiOperation(value = "SPU列表", notes = "品牌列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<SpuSimpleVO>> pageSpuByItem(@Valid @RequestBody SpuSearchDTO param) {
        return new R<>(spuService.pageSpu(param));
    }

    @ApiOperation(value = "SPU品牌", notes = "添加SPU", httpMethod = "POST")
    @PostMapping("/add")
    public R<Boolean> add(@Valid @RequestBody SpuSaveDTO param) {
        Integer result = spuService.addSpu(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "删除SPU", notes = "删除SPU", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        return new R<>(spuService.removeSpu(param));
    }

    @ApiOperation(value = "更改SPU", notes = "更改SPU", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody SpuUpdateDTO param) {
        return new R<>(spuService.updateSpu(param));
    }

    @ApiOperation(value = "SPU详情", notes = "SPU详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<SpuDetailVO> update(@Valid @RequestBody IdDTO param) {
        return new R<>(spuService.getSpuDetailById(param));
    }

    @ApiOperation(value = "更新上架状态", notes = "SPU详情", httpMethod = "POST")
    @PostMapping("/setSaleStatus")
    public R<Boolean> setSaleStatus(@Valid @RequestBody StatusBatchChangeDTO param) {
        return new R<>(spuService.setSaleStatus(param));
    }


    @ApiOperation(value = "更新推荐状态", notes = "SPU详情", httpMethod = "POST")
    @PostMapping("/setRecommendStatus")
    public R<Boolean> setRecommendStatus(@Valid @RequestBody StatusBatchChangeDTO param) {
        return new R<>(spuService.setRecommendStatus(param));
    }


    @ApiOperation("根据商品名称或货号模糊查询")
    @GetMapping(value = "/simpleList")
    public R<List<SpuSimpleVO>> getList(@RequestParam(value="keyword", required = false) String keyword) {
        return new R<>(spuService.listSimple(keyword));
    }


}

