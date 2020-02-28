package com.rainbow.portal.mapper;

import com.rainbow.portal.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 顾客登录表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@DS("admin")
public interface CustomerMapper extends BaseMapper<Customer> {

}
