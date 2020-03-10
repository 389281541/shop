package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.api.dto.GoodsSearchDTO;
import com.rainbow.api.dto.RecommendGoodsDTO;
import com.rainbow.api.entity.Spu;
import com.rainbow.api.entity.SpuImg;
import com.rainbow.api.vo.GoodsDetailVO;
import com.rainbow.api.vo.GoodsSimpleVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.vo.IdNameVO;
import com.rainbow.portal.mapper.SpuImgMapper;
import com.rainbow.portal.mapper.SpuMapper;
import com.rainbow.portal.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品服务实现类
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@Service
@Slf4j
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuImgMapper spuImgMapper;


    @Override
    public IPage<GoodsSimpleVO> pageGoods(GoodsSearchDTO param) {
        Page<Spu> spuIPage = new Page<>(param.getPageNum(), param.getPageSize());
        log.info("pageGoods param={}", param);
        IPage<Spu> spuPageInfo = spuMapper.pageSpu(spuIPage, param);
        if (spuPageInfo == null || CollectionUtils.isEmpty(spuPageInfo.getRecords())) {
            IPage<GoodsSimpleVO> goodsSimpleVOIPage = new Page<>();
            goodsSimpleVOIPage.setCurrent(param.getPageNum());
            goodsSimpleVOIPage.setSize(param.getPageSize());
            goodsSimpleVOIPage.setRecords(Lists.newArrayList());
            goodsSimpleVOIPage.setTotal(0L);
            return goodsSimpleVOIPage;
        }
        List<Spu> spuList = spuPageInfo.getRecords();
        List<GoodsSimpleVO> goodsRecords = convert2Goods(spuList);
        IPage<GoodsSimpleVO> result = new Page<>();
        BeanUtils.copyProperties(spuIPage, result);
        result.setCurrent(spuPageInfo.getCurrent());
        result.setTotal(spuPageInfo.getTotal());
        result.setPages(spuPageInfo.getPages());
        result.setSize(spuPageInfo.getSize());
        result.setRecords(goodsRecords);
        return result;
    }


    @Override
    public List<GoodsSimpleVO> listRecommendGoods(RecommendGoodsDTO param) {
        List<Spu> spuList = spuMapper.listRecommendSpu(param);
        List<GoodsSimpleVO> goodsSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuList)) {
            goodsSimpleVOList = convert2Goods(spuList);
        }
        return goodsSimpleVOList;
    }

    private List<GoodsSimpleVO> convert2Goods(List<Spu> spuList) {
        List<Long> spuIdList = spuList.stream().map(Spu::getId).collect(Collectors.toList());
        List<SpuImg> spuImgList = spuImgMapper.listBySpuIds(spuIdList);
        Map<Long, String> coverImgurlMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(spuImgList)) {
            coverImgurlMap = spuImgList.stream().collect(Collectors.toMap(SpuImg::getSpuId, SpuImg::getImgUrl));
        }
        final Map<Long, String> coverMap = coverImgurlMap;
        List<GoodsSimpleVO> goodsRecords = spuList.stream().map(x -> {
            GoodsSimpleVO goodsSimpleVO = new GoodsSimpleVO();
            goodsSimpleVO.setId(x.getId());
            goodsSimpleVO.setName(x.getName());
            goodsSimpleVO.setMinPrice(x.getMinPrice());
            goodsSimpleVO.setCoverImg(coverMap.get(x.getId()));
            goodsSimpleVO.setSale(x.getSale());
            goodsSimpleVO.setShop(new IdNameVO(x.getShopId(), x.getShopName()));
            return goodsSimpleVO;
        }).collect(Collectors.toList());
        return goodsRecords;
    }


    @Override
    public GoodsDetailVO getGoodsDetail(IdDTO param) {
        return null;
    }
}
