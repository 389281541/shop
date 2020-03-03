package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.ItemSaveDTO;
import com.rainbow.api.dto.ItemUpdateDTO;
import com.rainbow.api.vo.ItemDetailVO;
import com.rainbow.api.vo.ItemSimpleVO;
import com.rainbow.api.vo.ItemWithChildrenVO;
import com.rainbow.admin.service.IItemService;
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
import java.util.List;

/**
 * 类别表 前端控制器
 *
 * @author lujinwei
 * @since 2019-08-24
 */
@RestController
@RequestMapping("/item")
@Api(value = "/item", tags = "类别服务")
public class ItemController {

    @Autowired
    private IItemService itemService;


    @ApiOperation(value = "类别列表", notes = "类别列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<ItemSimpleVO>> page(@Valid @RequestBody IdPageDTO param) {
        return new R<>(itemService.pageItem(param));
    }

    @ApiOperation(value = "类别添加", notes = "类别添加", httpMethod = "POST")
    @PostMapping("/add")
    public R<Integer> save(@Valid @RequestBody ItemSaveDTO param) {
        return new R<>(itemService.saveItem(param));
    }

    @ApiOperation(value = "类别更新", notes = "类别更新", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody ItemUpdateDTO param) {
        Integer count = itemService.updateItem(param);
        return new R<>(count > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "类别详情", notes = "类别详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<ItemDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(itemService.getItemDetailById(param));
    }


    @ApiOperation(value = "类别移除", notes = "类别移除", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = itemService.removeItem(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "父类及其子类别列表", notes = "父类及其子类别列表", httpMethod = "POST")
    @PostMapping("/listWithChildren")
    public R<List<ItemWithChildrenVO>> listWithChildren() {
        return new R<>(itemService.listWidthSubItem());
    }

    @ApiOperation(value = "子类别列表", notes = "子类别列表", httpMethod = "POST")
    @PostMapping("/subList")
    public R<List<ItemSimpleVO>> subList() {
        return new R<>(itemService.listAllSubItem());
    }

}

