package com.rainbow.portal.mapper;

import com.rainbow.api.entity.OrderSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.vo.OrderSkuPromotionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单-sku关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@DS("goods")
public interface OrderSkuMapper extends BaseMapper<OrderSku> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertList(@Param("list")List<OrderSku> list);

    /**
     * 订单sku列表
     * @param orderId
     * @return
     */
    List<OrderSku> listByOrderId(@Param("orderId")Long orderId);

    /**
     * 释放锁定库存
     * @param list
     */
    Integer releaseLockStock(@Param("list")List<OrderSku> list);


    /**
     * 更新库存
     * @param list
     */
    Integer updateSkuStock(@Param("list")List<OrderSku> list);


    /**
     * 通过spuName查询
     * @param spuName
     * @return
     */
    List<OrderSku> listBySpuName(@Param("spuName") String spuName);

}
