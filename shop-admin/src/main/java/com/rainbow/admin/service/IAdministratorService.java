package com.rainbow.admin.service;

import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.entity.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.entity.Permission;
import com.rainbow.common.vo.IdNameTokenVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 后台管理用户表 服务类
 *
 * @author lujinwei
 * @since 2019-10-22
 */
public interface IAdministratorService extends IService<Administrator> {
    /**
     * 通过用户名和密码登陆
     * @param username
     * @param password
     * @param httpResponse
     * @return
     */
    IdNameTokenVO loginByPassword(String username, String password, HttpServletResponse httpResponse);


    /**
     * 通过userId获取用户权限
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(Long userId);
}
