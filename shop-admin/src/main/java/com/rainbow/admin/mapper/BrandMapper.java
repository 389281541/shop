package com.rainbow.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.admin.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-09-08
 */
public interface BrandMapper extends BaseMapper<Brand> {

    List<Brand> pageBrandByItemId(@Param("itemId") Long ItemId, IPage<Brand> page);
}
