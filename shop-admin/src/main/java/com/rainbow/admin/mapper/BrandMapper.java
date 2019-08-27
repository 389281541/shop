package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 品牌表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@DS("goods")
public interface BrandMapper extends BaseMapper<Brand> {

}
