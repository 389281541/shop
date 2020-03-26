package com.rainbow.portal.mapper;

import com.rainbow.api.dto.CartQuantityUpdateDTO;
import com.rainbow.api.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * 购物车表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-14
 */
@DS("goods")
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 更新购物车数量
     * @param param
     * @return
     */
    Integer updateQuantity(@Param("param") CartQuantityUpdateDTO param);


    /**
     * 移除购物车
     * @param customerId
     * @param ids
     * @return
     */
    Integer removeCartList(@Param("customerId") Long customerId, @Param("ids") Collection<Long> ids);


    /**
     * 计算某个spu数量
     * @param spuId
     * @return
     */
    Integer sumSpu(@Param("spuId") Long spuId);

}
