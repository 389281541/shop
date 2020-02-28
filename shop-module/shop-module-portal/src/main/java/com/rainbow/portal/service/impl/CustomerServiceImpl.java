package com.rainbow.portal.service.impl;

import com.rainbow.portal.entity.Customer;
import com.rainbow.portal.mapper.CustomerMapper;
import com.rainbow.portal.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 顾客登录表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
