package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.ItemDTO;
import com.rainbow.admin.api.vo.ItemSimpleVO;
import com.rainbow.admin.service.IItemService;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.PageDTO;
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

    @ApiOperation(value = "一级类别列表", notes = "一级类别列表", httpMethod = "POST")
    @PostMapping("/pageParentItem")
    public R<IPage<ItemSimpleVO>> pageParentItem(@Valid @RequestBody PageDTO param) {
        return new R<>(itemService.pageParentItem(param));
    }

    @ApiOperation(value = "二级类别列表", notes = "二级类别列表", httpMethod = "POST")
    @PostMapping("/pageSubItem")
    public R<IPage<ItemSimpleVO>> pageSubItem(@Valid @RequestBody IdPageDTO param) {
        return new R<>(itemService.pageSubItem(param));
    }

    @ApiOperation(value = "类别添加", notes = "类别添加", httpMethod = "POST")
    @PostMapping("/save")
    public R<Integer> save(@Valid @RequestBody ItemDTO param) {
        return new R<>(itemService.saveItem(param));
    }

    @ApiOperation(value = "类别更新", notes = "类别更新", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody ItemDTO param) {
        Integer count = itemService.updateItem(param);
        if (count > 0) {
            return new R<>(Boolean.TRUE);
        } else {
            return new R<>(Boolean.FALSE);
        }
    }


}

