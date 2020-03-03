package com.rainbow.portal.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.entity.HomeAdvertise;

/**
 * 轮播图表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-03
 */
@DS("goods")
public interface HomeAdvertiseMapper extends BaseMapper<HomeAdvertise> {

}
