package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@DS("goods")
public interface SpuMapper extends BaseMapper<Spu> {

}
