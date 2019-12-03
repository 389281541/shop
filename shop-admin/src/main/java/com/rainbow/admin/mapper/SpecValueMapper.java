package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SpecValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.common.dto.TwoTuple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格值表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@DS("goods")
public interface SpecValueMapper extends BaseMapper<SpecValue> {
    Integer updateSortIdBatch(@Param("list") List<TwoTuple<Long,Long>> list);
}
