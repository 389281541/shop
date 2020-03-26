package com.rainbow.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.dto.SelfOrderSearchDTO;
import com.rainbow.api.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.vo.ConfirmOrderVO;
import com.rainbow.api.vo.OrderDetailVO;
import com.rainbow.api.vo.OrderSimpleVO;
import com.rainbow.common.dto.IdDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单表 服务类
 *
 * @author lujinwei
 * @since 2020-03-18
 */
public interface IOrderService extends IService<Order> {

    /**
     * 生成确认订单
     * @return
     */
    ConfirmOrderVO generateConfirmOrder(Long customerId);

    /**
     * 生成订单
     * @param param
     * @return
     */
    Boolean generateOrder(OrderGenerateDTO param);


    /**
     * 订单分页
     * @param param
     * @return
     */
    IPage<OrderSimpleVO> pageOrder(SelfOrderSearchDTO param);


    /**
     * 获取订单详情
     * @param param
     * @return
     */
    OrderDetailVO getOrderDetailById(IdDTO param);


    /**
     * 取消订单
     * @param orderId
     */
    @Transactional
    void cancelOrder(Long orderId);


    /**
     * 支付成功回调
     */
    void paySucessCallback(Long orderId);


}
