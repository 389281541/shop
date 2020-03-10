package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * spu满减表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpuFullReductionMapper extends BaseMapper<SpuFullReduction> {

}
