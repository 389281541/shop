package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.entity.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.admin.entity.Permission;
import com.rainbow.admin.entity.Role;
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

    List<Role> getRoleByUserId(@Param("userId")Long userId);
}
