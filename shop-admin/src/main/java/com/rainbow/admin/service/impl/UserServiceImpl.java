package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.UserMapper;
import com.rainbow.admin.module.LoginDTO;
import com.rainbow.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.vo.IdNameAvatarTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public IdNameAvatarTokenVO loginByPassword(LoginDTO req) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,req.getUserName());
        User user = this.getOne(queryWrapper);
        //用户不存在
        if(user == null) {
            throw new BusinessException(BaseErrorCode.NO_USER);
        }
        return null;
    }
}
