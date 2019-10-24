package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.Permission;
import com.rainbow.admin.mapper.PermissionMapper;
import com.rainbow.admin.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 权限表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}