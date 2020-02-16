package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.OrderSku;
import com.rainbow.admin.mapper.OrderSkuMapper;
import com.rainbow.admin.service.IOrderSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单-sku关联表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Service
public class OrderSkuServiceImpl extends ServiceImpl<OrderSkuMapper, OrderSku> implements IOrderSkuService {

}
