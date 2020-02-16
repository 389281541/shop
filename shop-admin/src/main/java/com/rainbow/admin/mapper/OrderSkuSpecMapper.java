package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.OrderSkuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 订单条目-属性关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@DS("goods")
public interface OrderSkuSpecMapper extends BaseMapper<OrderSkuSpec> {

}
