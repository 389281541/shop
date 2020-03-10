package com.rainbow.portal.mapper;

import com.rainbow.api.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品sku表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SkuMapper extends BaseMapper<Sku> {

}
