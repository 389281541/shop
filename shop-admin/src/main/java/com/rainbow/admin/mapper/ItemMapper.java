package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 类别表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-08-24
 */
@DS("goods")
public interface ItemMapper extends BaseMapper<Item> {

}

