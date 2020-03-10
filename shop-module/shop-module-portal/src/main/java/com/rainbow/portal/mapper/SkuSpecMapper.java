package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SkuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * sku属性关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SkuSpecMapper extends BaseMapper<SkuSpec> {

}
