package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SpuSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品基本属性表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@DS("goods")
public interface SpuSpecMapper extends BaseMapper<SpuSpec> {

}
