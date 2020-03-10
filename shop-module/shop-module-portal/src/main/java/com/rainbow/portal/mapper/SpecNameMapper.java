package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpecName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 规格名称表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpecNameMapper extends BaseMapper<SpecName> {

}
