package com.rainbow.admin.service;

import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.entity.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.vo.IdNameTokenVO;

import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理用户表 服务类
 *
 * @author lujinwei
 * @since 2019-10-22
 */
public interface IAdministratorService extends IService<Administrator> {
    /**
     * 通过用户名和密码登陆
     * @param loginRequest
     * @param httpResponse
     * @return
     */
    IdNameTokenVO loginByPassword(LoginDTO loginRequest, HttpServletResponse httpResponse);
}
