package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.OrderSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 订单设置表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-18
 */
@DS("goods")
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {

}
