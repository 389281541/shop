package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.OrderDetailVO;
import com.rainbow.admin.api.vo.OrderSimpleVO;
import com.rainbow.admin.entity.Order;
import com.rainbow.admin.enums.OrderStatusEnum;
import com.rainbow.admin.mapper.OrderMapper;
import com.rainbow.admin.service.IOrderService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdsDTO;
import com.rainbow.common.enums.BooleanEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public IPage<OrderSimpleVO> pageOrder(OrderSearchDTO param) {
        Page<Order> orderPage = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<Order> orderIPage = baseMapper.pageOrder(param, orderPage);
        return orderIPage.convert(x->{
            OrderSimpleVO orderSimpleVO = new OrderSimpleVO();
            BeanUtils.copyProperties(x, orderSimpleVO);
            return orderSimpleVO;
        });
    }


    @Override
    @Transactional
    public Integer deliverOrder(List<OrderDeliverDTO> param) {
        return baseMapper.deliverOrder(param);
    }


    @Override
    public Integer closeOrder(OrderCloseDTO param) {
        Order record = new Order();
        record.setStatus(OrderStatusEnum.CLOSED.getValue());
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Order::getId, param.getIds());
        wrapper.eq(Order::getDelStatus, BooleanEnum.NO.getValue());
        return baseMapper.update(record, wrapper);
    }


    @Override
    public Integer removeOrder(IdsDTO param) {
        Order record = new Order();
        record.setDelStatus(BooleanEnum.YES.getValue());
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Order::getId, param.getIds());
        wrapper.eq(Order::getDelStatus, BooleanEnum.NO.getValue());
        return baseMapper.update(record, wrapper);
    }


    @Override
    public OrderDetailVO getDetailById(IdDTO param) {
        return baseMapper.getDetailById(param.getId());
    }


    @Override
    public Integer changeReceiverInfo(ReceiverInfoChangeDTO param) {
        return baseMapper.updateReceiverInfo(param);
    }


    @Override
    public Integer changeMoneyInfo(MoneyInfoChangeDTO param) {
        return baseMapper.updateMoneyInfo(param);
    }


    @Override
    public Integer changeNote(NoteChangeDTO param) {
        return baseMapper.updateNote(param);
    }
}
