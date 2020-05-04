package com.rainbow.portal.mapper;

import com.rainbow.api.entity.GrouponOrderSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 团购订单表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface GrouponOrderSkuMapper extends BaseMapper<GrouponOrderSku> {

}
