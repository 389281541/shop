package com.rainbow.admin.controller;


import com.rainbow.admin.api.dto.FlashPromotionSessionSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionSessionUpdateDTO;
import com.rainbow.admin.api.vo.FlashPromotionSessionVO;
import com.rainbow.admin.service.IFlashPromotionSessionService;
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
import java.util.List;

/**
 * 限时购场次表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@RestController
@RequestMapping("/flashPromotionSession")
@Api(value = "/flashPromotionSession", tags = "秒杀促销时间段")
public class FlashPromotionSessionController {

    @Autowired
    private IFlashPromotionSessionService flashPromotionSessionService;


    @ApiOperation(value = "秒杀时间段列表", notes = "秒杀时间段列表", httpMethod = "POST")
    @PostMapping("/list")
    public R<List<FlashPromotionSessionVO>> listFlashPromotionSession() {
        return new R<>(flashPromotionSessionService.listFlashPromotionSession());
    }

    @ApiOperation(value = "有效秒杀时间段列表", notes = "有效秒杀时间段列表", httpMethod = "POST")
    @PostMapping("/listAvailable")
    public R<List<FlashPromotionSessionVO>> listAvailable(@Valid @RequestBody IdDTO param) {
        return new R<>(flashPromotionSessionService.listAvailable(param));
    }

    @ApiOperation(value = "添加秒杀时间段", notes = "添加秒杀时间段", httpMethod = "POST")
    @PostMapping("/add")
    public R<Boolean> add(@Valid @RequestBody FlashPromotionSessionSaveDTO param) {
        Integer result = flashPromotionSessionService.addFlashPromotionSession(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除秒杀时间段", notes = "删除秒杀时间段", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = flashPromotionSessionService.removeFlashPromotionSession(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "更改秒杀时间段", notes = "更改秒杀时间段", httpMethod = "POST")
    @PostMapping("/update")
    public R<Integer> update(@Valid @RequestBody FlashPromotionSessionUpdateDTO param) {
        return new R<>(flashPromotionSessionService.updateFlashPromotionSession(param));
    }

    @ApiOperation(value = "更改秒杀时间段转台", notes = "更改秒杀时间段", httpMethod = "POST")
    @PostMapping("/updateStatus")
    public R<Integer> updateStatus(@Valid @RequestBody StatusChangeDTO param) {
        return new R<>(flashPromotionSessionService.updateStatus(param));
    }


    @ApiOperation(value = "秒杀时间段详情", notes = "秒杀时间段详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<FlashPromotionSessionVO> update(@Valid @RequestBody IdDTO param) {
        return new R<>(flashPromotionSessionService.getFlashPromotionSessionDetailById(param));
    }
}

