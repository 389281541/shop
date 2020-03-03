package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-09-08
 */
@DS("goods")
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据类别对品牌分页
     * @param ItemId
     * @param brandName
     * @param page
     * @return
     */
    List<Brand> pageBrandByItemId(@Param("itemId") Long ItemId, @Param("brandName") String brandName, IPage<Brand> page);
}
