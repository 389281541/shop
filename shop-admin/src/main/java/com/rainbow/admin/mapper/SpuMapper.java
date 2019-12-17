package com.rainbow.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpuSearchDTO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

/**
 * 商品表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@DS("goods")
public interface SpuMapper extends BaseMapper<Spu> {

    IPage<SpuSimpleVO> pageSpu(@Param("param")SpuSearchDTO param, IPage<Spu> page);
}
