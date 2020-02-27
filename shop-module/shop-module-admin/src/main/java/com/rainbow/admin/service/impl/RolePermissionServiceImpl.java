package com.rainbow.admin.service.impl;

import com.rainbow.admin.api.entity.RolePermission;
import com.rainbow.admin.mapper.RolePermissionMapper;
import com.rainbow.admin.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和权限关联表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
