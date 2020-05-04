package com.rainbow.portal.mapper;

import com.rainbow.api.entity.WxRefundOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 退款订单表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface WxRefundOrderMapper extends BaseMapper<WxRefundOrder> {

}
