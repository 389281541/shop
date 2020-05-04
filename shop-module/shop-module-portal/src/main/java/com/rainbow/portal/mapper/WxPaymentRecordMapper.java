package com.rainbow.portal.mapper;

import com.rainbow.api.entity.WxPaymentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 团购微信打款记录 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface WxPaymentRecordMapper extends BaseMapper<WxPaymentRecord> {

}
