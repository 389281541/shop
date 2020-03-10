package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpecValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 规格值表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpecValueMapper extends BaseMapper<SpecValue> {

}
