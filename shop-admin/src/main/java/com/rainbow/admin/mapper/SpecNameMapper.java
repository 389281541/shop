package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.entity.SpecName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格名称表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@DS("goods")
public interface SpecNameMapper extends BaseMapper<SpecName> {

    List<SpecName> pageSpecName(@Param("param") SpecNameSearchDTO param, IPage<SpecName> page);
}
