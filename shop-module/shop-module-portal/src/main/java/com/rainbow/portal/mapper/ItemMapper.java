package com.rainbow.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.entity.Item;

/**
 * 类别表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-02
 */
@DS("goods")
public interface ItemMapper extends BaseMapper<Item> {

}
