package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.admin.api.entity.Item;

/**
 * 类别表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-08-24
 */
@DS("goods")
public interface ItemMapper extends BaseMapper<Item> {
}

