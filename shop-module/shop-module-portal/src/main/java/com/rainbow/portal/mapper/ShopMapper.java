package com.rainbow.portal.mapper;

import com.rainbow.api.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 店铺表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface ShopMapper extends BaseMapper<Shop> {

}
