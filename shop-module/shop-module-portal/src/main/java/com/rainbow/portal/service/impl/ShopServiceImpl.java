package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.entity.Shop;
import com.rainbow.api.entity.Spu;
import com.rainbow.api.vo.GoodsSimpleVO;
import com.rainbow.api.vo.ShopGoodsVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.portal.mapper.ShopMapper;
import com.rainbow.portal.service.IGoodsService;
import com.rainbow.portal.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * shop service 实现类
 *
 * @author lujinwei
 * @since 2020/3/10
 */
@Service
@Slf4j
public class ShopServiceImpl implements IShopService {

    @Resource
    private ShopMapper shopMapper;

    @Autowired
    private IGoodsService goodsService;

    @Override
    public ShopGoodsVO getRecommendGoodsInfo(IdDTO param) {
        ShopGoodsVO shopGoodsVO = new ShopGoodsVO();
        //获取商品基本信息
        Shop shop = shopMapper.selectById(param.getId());
        shopGoodsVO.setId(shop.getId());
        shopGoodsVO.setName(shop.getName());
        shopGoodsVO.setAvatar(shop.getLogo());
        //商铺新品信息
        List<GoodsSimpleVO> newGoodList = goodsService.listShopRecommendGoods(param.getId());
        //商铺热销商品列表
        List<GoodsSimpleVO> hotGoodsList = goodsService.listShopHotGoods(param.getId());
        shopGoodsVO.setNewGoodsList(newGoodList);
        shopGoodsVO.setHotGoodsList(hotGoodsList);
        return shopGoodsVO;
    }
}
