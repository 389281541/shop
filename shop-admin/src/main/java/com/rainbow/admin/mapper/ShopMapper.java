package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 店铺表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@DS("goods")
public interface ShopMapper extends BaseMapper<Shop> {

}
