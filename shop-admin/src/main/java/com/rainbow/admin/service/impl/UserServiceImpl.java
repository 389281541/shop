package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.UserMapper;
import com.rainbow.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
