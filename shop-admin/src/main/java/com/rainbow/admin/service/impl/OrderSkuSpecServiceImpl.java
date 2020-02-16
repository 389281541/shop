package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.OrderSkuSpec;
import com.rainbow.admin.mapper.OrderSkuSpecMapper;
import com.rainbow.admin.service.IOrderSkuSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单条目-属性关联表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Service
public class OrderSkuSpecServiceImpl extends ServiceImpl<OrderSkuSpecMapper, OrderSkuSpec> implements IOrderSkuSpecService {

}
