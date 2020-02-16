package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.OrderDetailVO;
import com.rainbow.admin.api.vo.OrderSimpleVO;
import com.rainbow.admin.entity.Order;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdsDTO;

import java.util.List;

/**
 * 订单表 服务类
 *
 * @author lujinwei
 * @since 2020-02-16
 */
public interface IOrderService extends IService<Order> {


    /**
     * 订单分页查询
     * @param param
     * @return
     */
    IPage<OrderSimpleVO> pageOrder(OrderSearchDTO param);

    /**
     * 订单发货
     * @param param
     * @return
     */
    Integer deliverOrder(List<OrderDeliverDTO> param);


    /**
     * 关闭订单
     * @param param
     * @return
     */
    Integer closeOrder(OrderCloseDTO param);


    /**
     * 删除订单
     * @param param
     * @return
     */
    Integer removeOrder(IdsDTO param);


    /**
     * 获取订单详情
     * @param param
     * @return
     */
    OrderDetailVO getDetailById(IdDTO param);


    /**
     * 修改收货人信息
     * @param param
     * @return
     */
    Integer changeReceiverInfo(ReceiverInfoChangeDTO param);


    /**
     * 修改费用信息
     * @param param
     * @return
     */
    Integer changeMoneyInfo(MoneyInfoChangeDTO param);


    /**
     * 订单备注信息
     * @param param
     * @return
     */
    Integer changeNote(NoteChangeDTO param);

}
