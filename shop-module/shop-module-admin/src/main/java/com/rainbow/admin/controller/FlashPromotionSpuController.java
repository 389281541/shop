package com.rainbow.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.FlashPromotionSpuSaveDTO;
import com.rainbow.api.dto.FlashPromotionSpuUpdateDTO;
import com.rainbow.api.dto.FlashSpuSearchDTO;
import com.rainbow.api.vo.FlashPromotionSpuVO;
import com.rainbow.admin.service.IFlashPromotionSpuService;
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
import java.util.List;

/**
 * 商品限时购与商品关系表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@RestController
@RequestMapping("/flashPromotionSpu")
@Api(value = "/flashPromotionSpu", tags = "秒杀促销商品")
public class FlashPromotionSpuController {

    @Autowired
    private IFlashPromotionSpuService flashPromotionSpuService;

    @ApiOperation(value = "分页查询不同场次关联及商品信息" , notes = "分页查询不同场次关联及商品信息", httpMethod = "POST")
    @PostMapping(value = "/page")
    public R<IPage<FlashPromotionSpuVO>> pageFlashPromotion(@Valid @RequestBody FlashSpuSearchDTO param) {
        return new R<>(flashPromotionSpuService.pageFlashSpu(param));
    }


    @ApiOperation(value = "秒杀商品添加", notes = "秒杀商品添加", httpMethod = "POST")
    @PostMapping(value = "/addBatch")
    public R<Boolean> addBatch(@Valid @RequestBody List<FlashPromotionSpuSaveDTO> param) {
        return new R<>(flashPromotionSpuService.addBatch(param));
    }


    @ApiOperation(value = "秒杀商品更新", notes = "秒杀商品更新", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody FlashPromotionSpuUpdateDTO param) {
        Integer count = flashPromotionSpuService.updateFlashPromotionSpu(param);
        return new R<>(count > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除秒杀商品", notes = "删除秒杀商品", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = flashPromotionSpuService.removeFlashPromotionSpu(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "秒杀活动详情", notes = "秒杀活动详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<FlashPromotionSpuVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(flashPromotionSpuService.getFlashPromotionSpuDetailById(param));
    }

}

