package com.rainbow.portal.mapper;

import com.rainbow.api.entity.WxPayOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 团购支付订单 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface WxPayOrderMapper extends BaseMapper<WxPayOrder> {

}
