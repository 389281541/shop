package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.admin.entity.BrandItem;
import com.rainbow.common.model.KV;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌种类关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-09-22
 */
@DS("goods")
public interface BrandItemMapper extends BaseMapper<BrandItem> {

    List<KV<Long,Long>> getBrandCountByItemId();

    Integer insertBatch(@Param("list") List<BrandItem> list);
}
