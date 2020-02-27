package com.rainbow.admin.service.impl;

import com.rainbow.admin.api.entity.Role;
import com.rainbow.admin.mapper.RoleMapper;
import com.rainbow.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
