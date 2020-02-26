package com.rainbow.admin.mapper;

import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.entity.SkuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * sku属性关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-21
 */
@DS("goods")
public interface SkuSpecMapper extends BaseMapper<SkuSpec> {
    /**
     * 通过spuId删除
     * @param skuIds
     * @return
     */
    Integer removeBySkuIds(@Param("skuIds") Collection<Long> skuIds);

    /**
     * sku规格值列表
     * @param skuId
     * @return
     */
    List<SkuSpec> listBySkuId(@Param("skuId") Long skuId);


    /**
     * sku规格值列表
     * @param spuId
     * @return
     */
    List<SkuSpec> listBySpuId(@Param("spuId") Long spuId);


    /**
     * 批量插入
     * @param skuSpecList
     * @return
     */
    Integer insertBatch(@Param("list") List<SkuSpec> skuSpecList);
}
