package com.rainbow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.entity.SpuSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品基本属性表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@DS("goods")
public interface SpuSpecMapper extends BaseMapper<SpuSpec> {
    /**
     * 通过spuId删除spu_spec关联表
     * @param spuId
     * @return
     */
    Integer removeBySpuId(@Param("spuId") Long spuId);


    /**
     * 通过spu
     * @param spuId
     * @return
     */
    List<SpuSpec> listBySpuId(Long spuId);
}
