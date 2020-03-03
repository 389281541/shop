package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.HomeAdvertiseSaveDTO;
import com.rainbow.api.dto.HomeAdvertiseSearchDTO;
import com.rainbow.api.dto.HomeAdvertiseUpdateDTO;
import com.rainbow.api.vo.AdvertiseVO;
import com.rainbow.api.vo.HomeAdvertiseVO;
import com.rainbow.admin.service.IHomeAdvertiseService;
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

/**
 * 轮播图表 前端控制器
 *
 * @author lujinwei
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/homeAdvertise")
@Api(value = "/homeAdvertise", tags = "首页广告位")
public class HomeAdvertiseController {

    @Autowired
    private IHomeAdvertiseService homeAdvertiseService;

    @ApiOperation(value = "广告列表", notes = "广告列表", httpMethod = "POST")
    @PostMapping("/listByType")
    R<IPage<HomeAdvertiseVO>> list(@RequestBody @Valid HomeAdvertiseSearchDTO param) {
        return new R<>(homeAdvertiseService.pageHomeAdvertise(param));
    }


    @ApiOperation(value = "首页广告详情", notes = "首页广告详情", httpMethod = "POST")
    @PostMapping("/detail")
    R<HomeAdvertiseVO> detail(@RequestBody @Valid IdDTO param) {
        return new R<>(homeAdvertiseService.getDetailHomeAdvertiseById(param));
    }


    @ApiOperation(value = "添加首页广告", notes = "添加首页广告", httpMethod = "POST")
    @PostMapping("/add")
    R<Boolean> update(@RequestBody @Valid HomeAdvertiseSaveDTO param) {
        Integer result = homeAdvertiseService.addHomeAdvertise(param);
        return new R<>(result > 0 ? Boolean.TRUE: Boolean.FALSE);
    }


    @ApiOperation(value = "更新首页广告", notes = "更新首页广告", httpMethod = "POST")
    @PostMapping("/update")
    R<Boolean> update(@RequestBody @Valid HomeAdvertiseUpdateDTO param) {
        Integer result = homeAdvertiseService.updateHomeAdvertise(param);
        return new R<>(result > 0 ? Boolean.TRUE: Boolean.FALSE);
    }


    @ApiOperation(value = "移除首页广告", notes = "移除首页广告", httpMethod = "POST")
    @PostMapping("/remove")
    R<Boolean> remove(@RequestBody @Valid IdDTO param) {
        Integer result = homeAdvertiseService.removeHomeAdvertise(param);
        return new R<>(result > 0 ? Boolean.TRUE: Boolean.FALSE);
    }


}

