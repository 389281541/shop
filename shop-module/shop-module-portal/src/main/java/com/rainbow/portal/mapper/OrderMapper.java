package com.rainbow.portal.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.api.dto.SelfOrderSearchDTO;
import com.rainbow.api.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-18
 */
@DS("goods")
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单分页
     * @param param
     * @param page
     * @return
     */
    IPage<Order> pageOrder(Page<Order> page, @Param("param") SelfOrderSearchDTO param);


    /**
     *
     * @param customerId
     * @return
     */
    List<Order> getByParentOrderNo(@Param("customerId")Long customerId, @Param("parentOrderNo") String parentOrderNo);
}
