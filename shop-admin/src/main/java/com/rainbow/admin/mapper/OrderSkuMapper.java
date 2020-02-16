package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.OrderSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 订单-sku关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@DS("goods")
public interface OrderSkuMapper extends BaseMapper<OrderSku> {

}
