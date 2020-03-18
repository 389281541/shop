package com.rainbow.portal.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.api.dto.GoodsSearchDTO;
import com.rainbow.api.dto.RecommendGoodsDTO;
import com.rainbow.api.entity.Spu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpuMapper extends BaseMapper<Spu> {

    /**
     * spu分页展示
     * @param page
     * @param param
     * @return
     */
    IPage<Spu> pageSpu(Page<Spu> page, @Param("param") GoodsSearchDTO param);


    /**
     * 推荐商品列表
     * @param param
     * @return
     */
    List<Spu> listRecommendSpu(@Param("param")RecommendGoodsDTO param);


    /**
     * 商铺新品spu列表
     * @param shopId
     * @return
     */
    List<Spu> listShopNewSpu(@Param("shopId") Long shopId);


    /**
     * 商铺热销spu列表
     * @param shopId
     * @return
     */
    List<Spu> listShopHotSpu(@Param("shopId") Long shopId);


}
