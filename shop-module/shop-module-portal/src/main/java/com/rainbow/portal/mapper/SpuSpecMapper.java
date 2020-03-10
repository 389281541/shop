package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品基本属性表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpuSpecMapper extends BaseMapper<SpuSpec> {

}
