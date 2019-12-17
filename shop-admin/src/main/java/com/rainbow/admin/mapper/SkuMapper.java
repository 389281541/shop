package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品sku表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@DS("goods")
public interface SkuMapper extends BaseMapper<Sku> {

}
