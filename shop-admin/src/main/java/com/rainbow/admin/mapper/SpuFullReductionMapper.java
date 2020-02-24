package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SpuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * spu满减表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-24
 */
@DS("goods")
public interface SpuFullReductionMapper extends BaseMapper<SpuFullReduction> {

}
