package com.rainbow.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.vo.IdNameAvatarTokenVO;
import com.rainbow.portal.api.dto.CustomerRegisterDTO;
import com.rainbow.portal.entity.Customer;

/**
 * 顾客登录表 服务类
 *
 * @author lujinwei
 * @since 2020-02-29
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * C端用户通过用户名、密码登录
     * @param username
     * @param password
     * @return
     */
    IdNameAvatarTokenVO loginByPassword(String username, String password);


    /**
     * 用户注册
     * @param param
     * @return
     */
    Boolean register(CustomerRegisterDTO param);



}
