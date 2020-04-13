package com.rainbow.portal.mapper;

import com.rainbow.api.entity.PaymentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 支付记录表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-04-11
 */
@DS("goods")
public interface PaymentRecordMapper extends BaseMapper<PaymentRecord> {

}
