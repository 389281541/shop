package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.api.entity.Role;

/**
 * 角色表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@DS("admin")
public interface RoleMapper extends BaseMapper<Role> {

}
