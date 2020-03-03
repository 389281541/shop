package com.rainbow.admin.service.impl;

import com.rainbow.api.entity.AdministratorRole;
import com.rainbow.admin.mapper.AdministratorRoleMapper;
import com.rainbow.admin.service.IAdministratorRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-23
 */
@Service
public class AdministratorRoleServiceImpl extends ServiceImpl<AdministratorRoleMapper, AdministratorRole> implements IAdministratorRoleService {

}
