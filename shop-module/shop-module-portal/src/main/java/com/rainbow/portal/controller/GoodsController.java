package com.rainbow.portal.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.GoodsSearchDTO;
import com.rainbow.api.dto.RecommendGoodsDTO;
import com.rainbow.api.vo.GoodsDetailVO;
import com.rainbow.api.vo.GoodsSimpleVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.portal.service.IGoodsService;
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
 * 商品服务
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@RestController
@RequestMapping("/goods")
@Api(value = "/goods", tags = "商品服务")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;


    @ApiOperation(value = "推荐商品列表", notes = "推荐商品列表", httpMethod = "POST")
    @PostMapping("/listRecommend")
    public R<List<GoodsSimpleVO>> list(@Valid @RequestBody RecommendGoodsDTO param) {
        return new R<>(goodsService.listRecommendGoods(param));
    }

    @ApiOperation(value = "商品列表", notes = "商品列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<GoodsSimpleVO>> page(@Valid @RequestBody GoodsSearchDTO param) {
        return new R<>(goodsService.pageGoods(param));
    }


    @ApiOperation(value = "商品详情", notes = "商品详情", httpMethod = "POST")
    @PostMapping("/get")
    public R<GoodsDetailVO> get(@Valid @RequestBody IdDTO param) {
        return new R<>(goodsService.getGoodsDetail(param));
    }

}
