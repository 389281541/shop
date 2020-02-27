package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.api.entity.AdministratorRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户和角色关联表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-23
 */
@DS("admin")
public interface AdministratorRoleMapper extends BaseMapper<AdministratorRole> {

}
