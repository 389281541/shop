package com.rainbow.admin.mapper.user;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.admin.entity.User;

/**
 * 后台管理用户表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@DS("admin")
public interface UserMapper extends BaseMapper<User> {

}
