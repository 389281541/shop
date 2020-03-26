package com.rainbow.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.CartQuantityUpdateDTO;
import com.rainbow.api.dto.CartSaveDTO;
import com.rainbow.api.entity.Cart;
import com.rainbow.api.vo.CartPromotionVO;

import java.util.Collection;
import java.util.List;

/**
 * 购物车表 服务类
 *
 * @author lujinwei
 * @since 2020-03-14
 */
public interface ICartService extends IService<Cart> {

    /**
     * 添加购物车
     * @param param
     * @return
     */
    Integer addCart(CartSaveDTO param);


    /**
     * 购物车列表
     * @param customerId
     * @return
     */
    List<CartPromotionVO> list(Long customerId);


    /**
     * 更新购物车数量
     * @param param
     * @return
     */
    Integer updateQuantity(CartQuantityUpdateDTO param);


    /**
     * 删除购物车
     * @param customerId
     * @param id
     * @return
     */
    Integer removeCart(Long customerId, Long id);


    /**
     * 删除购物车列表
     * @param customerId
     * @param ids
     * @return
     */
    Integer removeCartList(Long customerId, Collection<Long> ids);
}
