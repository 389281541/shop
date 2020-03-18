package com.rainbow.portal.controller;


import com.rainbow.api.dto.CartQuantityUpdateDTO;
import com.rainbow.api.dto.CartSaveDTO;
import com.rainbow.api.vo.CartPromotionVO;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.ICartService;
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
 * 购物车表 前端控制器
 *
 * @author lujinwei
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/cart")
@Api(value = "/cart", tags = "购物车服务")
public class CartController {

    @Autowired
    private ICartService cartService;


    @ApiOperation(value = "添加到购物车", notes = "添加到购物车", httpMethod = "POST")
    @PostMapping("/add")
    @NeedLogin
    public R<Boolean> add(@Valid @RequestBody CartSaveDTO param) {
        param.setCustomerId(RB.getUserId());
        Integer res = cartService.addCart(param);
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "添加到购物车", notes = "添加到购物车", httpMethod = "POST")
    @PostMapping("/list")
    @NeedLogin
    public R<List<CartPromotionVO>> list() {
        return new R<>(cartService.list(RB.getUserId()));
    }


    @ApiOperation(value = "更新购物车商品数量", notes = "更新购物车商品数量", httpMethod = "POST")
    @PostMapping("/updateQuantity")
    @NeedLogin
    public R<Boolean> updateQuantity(@Valid @RequestBody CartQuantityUpdateDTO param) {
        param.setCustomerId(RB.getUserId());
        Integer res = cartService.updateQuantity(param);
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除购物车商品", notes = "删除购物车商品", httpMethod = "POST")
    @PostMapping("/remove")
    @NeedLogin
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer res = cartService.removeCart(RB.getUserId(), param.getId());
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }
}

