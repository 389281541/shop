package com.rainbow.portal.controller;

import com.rainbow.api.vo.FlashThemeVO;
import com.rainbow.common.dto.R;
import com.rainbow.portal.service.IFlashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 秒杀服务
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@RestController
@RequestMapping("/flash")
@Api(value = "/flash", tags = "用户服务")
public class FlashController {

    @Autowired
    private IFlashService flashService;


    @ApiOperation(value = "秒杀活动列表", notes = "秒杀活动列表", httpMethod = "POST")
    @PostMapping("/theme/list")
    public R<List<FlashThemeVO>> list() {
        return new R<>(flashService.listFlashThemes());
    }


}
