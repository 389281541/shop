package com.rainbow.portal.mapper;

import com.rainbow.api.entity.IntegrationRuleSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 积分规则配置表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-19
 */
@DS("goods")
public interface IntegrationRuleSettingMapper extends BaseMapper<IntegrationRuleSetting> {

}
