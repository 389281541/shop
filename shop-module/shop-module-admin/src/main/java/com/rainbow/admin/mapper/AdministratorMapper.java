package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.api.entity.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.admin.api.entity.Permission;
import com.rainbow.admin.api.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理用户表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@DS("admin")
public interface AdministratorMapper extends BaseMapper<Administrator> {
    /**
     * 通过userId获取权限列表
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(@Param("userId") Long userId);

    /**
     * 获取角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(@Param("userId")Long userId);
}
