package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.FlashPromotionSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionUpdateDTO;
import com.rainbow.admin.api.vo.FlashPromotionVO;
import com.rainbow.admin.service.IFlashPromotionService;
import com.rainbow.common.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 秒杀促销 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@RestController
@RequestMapping("/flashPromotion")
@Api(value = "/flashPromotion", tags = "秒杀促销服务")
public class FlashPromotionController {

    @Autowired
    private IFlashPromotionService flashPromotionService;


    @ApiOperation(value = "秒杀活动列表", notes = "秒杀活动列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<FlashPromotionVO>> pageFlashPromotion(@Valid @RequestBody SearchPageDTO param) {
        return new R<>(flashPromotionService.pageFlashPromotion(param));
    }

    @ApiOperation(value = "添加秒杀活动", notes = "添加秒杀活动", httpMethod = "POST")
    @PostMapping("/add")
    public R<Boolean> add(@Valid @RequestBody FlashPromotionSaveDTO param) {
        Integer result = flashPromotionService.addFlashPromotion(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除秒杀活动", notes = "删除秒杀活动", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = flashPromotionService.removeFlashPromotion(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "更改秒杀活动", notes = "更改秒杀活动", httpMethod = "POST")
    @PostMapping("/update")
    public R<Integer> update(@Valid @RequestBody FlashPromotionUpdateDTO param) {
        return new R<>(flashPromotionService.updateFlashPromotion(param));
    }

    @ApiOperation(value = "更改秒杀活动", notes = "更改秒杀活动", httpMethod = "POST")
    @PostMapping("/updateStatus")
    public R<Integer> updateStatus(@Valid @RequestBody StatusChangeDTO param) {
        return new R<>(flashPromotionService.updateStatus(param));
    }

    @ApiOperation(value = "秒杀活动详情", notes = "秒杀活动详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<FlashPromotionVO> update(@Valid @RequestBody IdDTO param) {
        return new R<>(flashPromotionService.getFlashPromotionDetailById(param));
    }
}

