package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.ShopSaveDTO;
import com.rainbow.admin.api.dto.ShopSearchDTO;
import com.rainbow.admin.api.dto.ShopUpdateDTO;
import com.rainbow.admin.api.vo.ShopDetailVO;
import com.rainbow.admin.api.vo.ShopSimpleVO;
import com.rainbow.admin.service.IShopService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.StatusChangeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 店铺表 前端控制器
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/shop")
@Api(value = "/shop", tags = "店铺服务")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @ApiOperation(value = "分页列表", notes = "店铺列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<ShopSimpleVO>> page(@Valid @RequestBody ShopSearchDTO param) {
        return new R<>(shopService.pageShop(param));
    }

    @ApiOperation(value = "店铺添加", notes = "店铺添加", httpMethod = "POST")
    @PostMapping("/add")
    public R<Integer> save(@Valid @RequestBody ShopSaveDTO param) {
        return new R<>(shopService.saveShop(param));
    }

    @ApiOperation(value = "店铺更新", notes = "店铺更新", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody ShopUpdateDTO param) {
        Integer count = shopService.updateShop(param);
        return new R<>(count > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "店铺详情", notes = "店铺详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<ShopDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(shopService.getShopDetailById(param));
    }


    @ApiOperation(value = "店铺移除", notes = "店铺移除", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = shopService.removeShop(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "店铺审核", notes = "店铺审核", httpMethod = "POST")
    @PostMapping("/audit")
    public R<Boolean> audit(@Valid @RequestBody StatusChangeDTO param) {
        Integer result = shopService.audit(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


}

