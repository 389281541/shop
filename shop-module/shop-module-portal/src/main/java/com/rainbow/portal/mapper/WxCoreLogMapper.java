package com.rainbow.portal.mapper;

import com.rainbow.api.entity.WxCoreLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 微信日志 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface WxCoreLogMapper extends BaseMapper<WxCoreLog> {

}
