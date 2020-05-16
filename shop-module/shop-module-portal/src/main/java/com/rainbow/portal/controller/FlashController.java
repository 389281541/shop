package com.rainbow.portal.controller;

import com.rainbow.api.dto.GoConfirmOrderDTO;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.vo.ConfirmOrderVO;
import com.rainbow.api.vo.FlashThemeVO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IFlashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation(value = "秒杀活动列表", notes = "秒杀活动列表", httpMethod = "POST")
    @PostMapping("/goConfirmOrder")
    public R<Boolean> goConfirmOrder(@Valid @RequestBody GoConfirmOrderDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(flashService.goConfirmOrder(param));
    }

    @ApiOperation(value = "进行秒杀", notes = "进行秒杀", httpMethod = "POST")
    @PostMapping("/generateFlashConfirmOrder")
    public R<ConfirmOrderVO> generateFlashConfirmOrder() {
        return new R<>(flashService.generateFlashConfirmOrder(RB.getUserId()));
    }


    @ApiOperation(value = "生成秒杀订单", notes = "生成秒杀订单", httpMethod = "POST")
    @PostMapping("/{path}/generateOrder")
    public R<String> generateFlashOrder(@Valid @RequestBody OrderGenerateDTO param, @PathVariable("path") String path) {
        param.setCustomerId(RB.getUserId());
        return new R<>(flashService.generateFlashOrder(param, path));
    }


    @ApiOperation(value = "获取秒杀路径", notes = "生成秒杀订单", httpMethod = "POST")
    @PostMapping("/path/get")
    public R<String> getPath() {
        Long customerId = RB.getUserId();
        return new R<>(flashService.getPath(customerId));
    }


}
