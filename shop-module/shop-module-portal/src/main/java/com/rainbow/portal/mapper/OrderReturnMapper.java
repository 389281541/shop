package com.rainbow.portal.mapper;

import com.rainbow.api.entity.OrderReturn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 退单 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-28
 */
@DS("goods")
public interface OrderReturnMapper extends BaseMapper<OrderReturn> {

}
