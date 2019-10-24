package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 权限表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@DS("admin")
public interface PermissionMapper extends BaseMapper<Permission> {

}
