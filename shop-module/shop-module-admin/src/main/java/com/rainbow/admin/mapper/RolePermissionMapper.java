package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.api.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户和权限关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@DS("admin")
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
