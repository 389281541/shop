package com.rainbow.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.dto.CustomerUpdateDTO;
import com.rainbow.api.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * 顾客登录表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@DS("admin")
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     * 更新用户信息
     * @param customer
     * @return
     */
    Integer updateInfo(@Param("param") CustomerUpdateDTO customer);
}
