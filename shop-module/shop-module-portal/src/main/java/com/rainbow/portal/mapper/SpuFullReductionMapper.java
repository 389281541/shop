package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * spu满减表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpuFullReductionMapper extends BaseMapper<SpuFullReduction> {
    /**
     * 获取spu满减
     * @param spuIds
     * @return
     */
    List<SpuFullReduction> listBySpuIds(@Param("ids") Collection<Long> spuIds);
}
