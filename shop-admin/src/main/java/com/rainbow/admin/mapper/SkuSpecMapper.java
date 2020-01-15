package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SkuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
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
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);
}
