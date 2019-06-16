package com.rainbow.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.entity.User;
import com.rainbow.common.vo.IdNameTokenVO;

/**
 * 后台管理用户表 服务类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名和密码登陆
     * @param req
     * @return
     */
    IdNameTokenVO loginByPassword(LoginDTO req);

    /**
     * 添加用户
     */

}
