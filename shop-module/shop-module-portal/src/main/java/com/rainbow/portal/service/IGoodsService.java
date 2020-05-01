package com.rainbow.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.GoodsSearchDTO;
import com.rainbow.api.dto.RecommendGoodsDTO;
import com.rainbow.api.vo.GoodsDetailVO;
import com.rainbow.api.vo.GoodsSimpleVO;
import com.rainbow.common.dto.IdDTO;

import java.util.List;

/**
 * 商品service
 */
public interface IGoodsService {

    /**
     * 搜索商品列表
     * @param param
     * @return
     */
    IPage<GoodsSimpleVO> pageGoods(GoodsSearchDTO param);


    /**
     * 推荐商品列表页
     * @param param
     * @return
     */
    List<GoodsSimpleVO> listRecommendGoods(RecommendGoodsDTO param);


    /**
     * 获取商品详情页
     * @param param
     * @return
     */
    GoodsDetailVO getGoodsDetail(IdDTO param);


    /**
     * 获取店铺新品列表
     * @param shopId
     * @return
     */
    List<GoodsSimpleVO> listShopRecommendGoods(Long shopId);


    /**
     * 获取店铺热销列表
     * @param shopId
     * @return
     */
    List<GoodsSimpleVO> listShopHotGoods(Long shopId);
}
