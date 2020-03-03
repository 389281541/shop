package com.rainbow.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.entity.Administrator;
import com.rainbow.api.entity.Permission;
import com.rainbow.api.entity.Role;
import com.rainbow.api.vo.AdminstratorSimpleVO;
import com.rainbow.common.vo.IdNameTokenVO;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    IdNameTokenVO loginByPassword(String username, String password);


    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    Boolean logout(HttpServletRequest request, HttpServletResponse response);


    /**
     * 通过userId获取用户权限
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(Long userId);

    /**
     * 通过userId获取角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(Long userId);


    /**
     * 通过username获取管理者信息
     * @param username
     * @return
     */
    AdminstratorSimpleVO getInfoByUserName(String username);
}
