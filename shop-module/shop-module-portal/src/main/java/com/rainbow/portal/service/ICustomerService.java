package com.rainbow.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.CustomerRegisterDTO;
import com.rainbow.api.dto.CustomerUpdateDTO;
import com.rainbow.api.entity.Customer;
import com.rainbow.api.vo.CustomerVO;
import com.rainbow.common.vo.IdNameAvatarTokenVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 退出登录删除相关cookie
     * @param request
     * @param response
     * @return
     */
    Boolean logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户注册
     * @param param
     * @return
     */
    Boolean register(CustomerRegisterDTO param);


    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    CustomerVO getInfo(Long userId);


    /**
     * 更新个人信息
     * @param param
     * @return
     */
    Integer updateInfo(CustomerUpdateDTO param);


}
