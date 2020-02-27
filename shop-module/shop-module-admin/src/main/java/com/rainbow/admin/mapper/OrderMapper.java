package com.rainbow.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.OrderDetailVO;
import com.rainbow.admin.api.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@DS("goods")
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单分页
     * @param param
     * @param page
     * @return
     */
    IPage<Order> pageOrder( Page<Order> page, @Param("param")OrderSearchDTO param);

    /**
     * 批量送货
     * @param list
     * @return
     */
    Integer deliverOrder(@Param("list") List<OrderDeliverDTO> list);


    /**
     * 更新收件人信息
     * @param param
     * @return
     */
    Integer updateReceiverInfo(@Param("param") ReceiverInfoChangeDTO param);

    /**
     * 更新资金信息
     * @param param
     * @return
     */
    Integer updateMoneyInfo(@Param("param") MoneyInfoChangeDTO param);


    /**
     * 更新备注
     * @param param
     * @return
     */
    Integer updateNote(@Param("param") NoteChangeDTO param);
}
