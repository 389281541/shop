package com.rainbow.admin.service.impl;

import com.rainbow.api.dto.OrderSettingDTO;
import com.rainbow.api.vo.OrderSettingVO;
import com.rainbow.api.entity.OrderSetting;
import com.rainbow.admin.mapper.OrderSettingMapper;
import com.rainbow.admin.service.IOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.dto.IdDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 订单设置表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-18
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {


    @Override
    public OrderSettingVO getDetailById(IdDTO param) {
        OrderSettingVO orderSettingVO = new OrderSettingVO();
        OrderSetting orderSetting = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(orderSetting, orderSettingVO);
        return orderSettingVO;
    }

    @Override
    public Integer updateOrderSetting(OrderSettingDTO param) {
        OrderSetting orderSetting = new OrderSetting();
        BeanUtils.copyProperties(param, orderSetting);
        return baseMapper.updateById(orderSetting);
    }
}
