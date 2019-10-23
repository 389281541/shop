package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.admin.entity.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 后台管理用户表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@DS("admin")
public interface AdministratorMapper extends BaseMapper<Administrator> {

}
