package com.rainbow.portal.service.impl;

import com.rainbow.api.dto.ReturnOrderDTO;
import com.rainbow.api.entity.Customer;
import com.rainbow.api.entity.Order;
import com.rainbow.api.entity.OrderReturn;
import com.rainbow.api.entity.Shop;
import com.rainbow.api.enums.PortalErrorCode;
import com.rainbow.api.enums.ReturnOrderStatusEnum;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.portal.mapper.CustomerMapper;
import com.rainbow.portal.mapper.OrderMapper;
import com.rainbow.portal.mapper.OrderReturnMapper;
import com.rainbow.portal.mapper.ShopMapper;
import com.rainbow.portal.service.IReturnOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 退单service实现
 *
 * @author lujinwei
 * @since 2020/3/28
 */
@Service
@Slf4j
public class ReturnOrderServiceImpl implements IReturnOrderService {


    @Resource
    private OrderReturnMapper orderReturnMapper;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Integer create(ReturnOrderDTO param) {
        return buildAndInsertReturnOrder(param);
    }


    private Integer buildAndInsertReturnOrder(ReturnOrderDTO param) {
        OrderReturn orderReturn = new OrderReturn();
        Shop shop = shopMapper.selectById(param.getShopId());
        if(shop == null) {
            throw new BusinessException(PortalErrorCode.SHOP_NOT_EXIST);
        }
        Order order = orderMapper.selectById(param.getOrderId());
        if(order == null) {
            throw new BusinessException(PortalErrorCode.ORDER_NOT_EXIST);
        }
        orderReturn.setType(param.getType());
        orderReturn.setShopId(param.getShopId());
        orderReturn.setReceiverName(shop.getKeeperName());
        orderReturn.setReceiverPhone(shop.getPhoneNumber());
        orderReturn.setShopAddress(shop.getAddress());
        orderReturn.setOrderId(param.getOrderId());
        orderReturn.setOrderNo(order.getOrderNo());
        orderReturn.setReturnAmount(order.getTotalAmount());
        Customer customer = customerMapper.selectById(param.getCustomerId());
        if(customer == null) {
            throw new BusinessException(PortalErrorCode.USER_NOT_EXIST);
        }
        orderReturn.setUserName(customer.getUserName());
        orderReturn.setUserPhone(customer.getMobile());
        orderReturn.setStatus(ReturnOrderStatusEnum.WAIT_TO_HANDLE.getValue());
        orderReturn.setCreateTime(LocalDateTime.now());
        orderReturn.setPayPrice(order.getTotalAmount());
        orderReturn.setReason(param.getReason());
        orderReturn.setDescription(param.getDescription());
        orderReturn.setProofPic(param.getProofPics());
        return orderReturnMapper.insert(orderReturn);
    }

}
