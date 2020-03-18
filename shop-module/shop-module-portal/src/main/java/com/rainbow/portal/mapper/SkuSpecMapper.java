package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SkuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku属性关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SkuSpecMapper extends BaseMapper<SkuSpec> {

    /**
     * sku规格值列表
     * @param spuId
     * @return
     */
    List<SkuSpec> listBySpuId(@Param("spuId") Long spuId);

    /**
     * sku规格值
     * @param skuId
     * @return
     */
    List<SkuSpec> listBySkuId(@Param("skuId") Long skuId);
}
