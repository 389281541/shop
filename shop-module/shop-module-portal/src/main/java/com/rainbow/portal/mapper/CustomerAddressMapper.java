package com.rainbow.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.entity.CustomerAddress;
import com.rainbow.common.model.KV;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 顾客地址表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-06
 */
@DS("admin")
public interface CustomerAddressMapper extends BaseMapper<CustomerAddress> {
    /**
     * 设置默认地址
     * @param list
     * @return
     */
    Integer updateDefault(@Param("list") List<KV<Long,Integer>> list);
}
