package com.rainbow.portal.controller;

import com.rainbow.api.vo.ShopGoodsVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.portal.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 店铺服务接口
 *
 * @author lujinwei
 * @since 2020/3/10
 */
@RestController
@RequestMapping("/shop")
@Api(value = "/shop", tags = "店铺服务")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @ApiOperation(value = "店铺展示详情", notes = "广告列表信息", httpMethod = "POST")
    @PostMapping("/goodsInfo")
    public R<ShopGoodsVO> goodsInfo(@Valid @RequestBody IdDTO param) {
        return new R<>(shopService.getRecommendGoodsInfo(param));
    }


}
