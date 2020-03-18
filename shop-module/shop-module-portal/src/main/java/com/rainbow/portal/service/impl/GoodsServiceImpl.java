package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.api.dto.GoodsSearchDTO;
import com.rainbow.api.dto.RecommendGoodsDTO;
import com.rainbow.api.entity.*;
import com.rainbow.api.vo.*;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.vo.FatherChildrenVO;
import com.rainbow.common.vo.IdNameVO;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private SpuFullReductionMapper spuFullReductionMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SkuSpecMapper skuSpecMapper;

    @Resource
    private SpuSpecMapper spuSpecMapper;


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
        List<SpuImg> spuImgList = spuImgMapper.listCoversBySpuIds(spuIdList);
        Map<Long, String> coverImgurlMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(spuImgList)) {
            coverImgurlMap = spuImgList.stream().collect(Collectors.toMap(SpuImg::getSpuId, SpuImg::getImgUrl));
        }
        final Map<Long, String> coverMap = coverImgurlMap;
        List<GoodsSimpleVO> goodsRecords = spuList.stream().map(x -> {
            GoodsSimpleVO goodsSimpleVO = new GoodsSimpleVO();
            goodsSimpleVO.setId(x.getId());
            goodsSimpleVO.setSale(x.getSale());
            goodsSimpleVO.setName(x.getName());
            goodsSimpleVO.setMinPrice(x.getMinPrice());
            goodsSimpleVO.setCoverImg(coverMap.get(x.getId()));
            goodsSimpleVO.setShop(new IdNameVO(x.getShopId(), x.getShopName()));
            return goodsSimpleVO;
        }).collect(Collectors.toList());
        return goodsRecords;
    }


    @Override
    public List<GoodsSimpleVO> listShopRecommendGoods(Long shopId) {
        List<Spu> spuList = spuMapper.listShopNewSpu(shopId);
        List<GoodsSimpleVO> goodsSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuList)) {
            goodsSimpleVOList = convert2Goods(spuList);
        }
        return goodsSimpleVOList;
    }

    @Override
    public List<GoodsSimpleVO> listShopHotGoods(Long shopId) {
        List<Spu> spuList = spuMapper.listShopHotSpu(shopId);
        List<GoodsSimpleVO> goodsSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuList)) {
            goodsSimpleVOList = convert2Goods(spuList);
        }
        return goodsSimpleVOList;
    }

    @Override
    public GoodsDetailVO getGoodsDetail(IdDTO param) {
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        //获取基本信息
        Spu spu = spuMapper.selectById(param.getId());
        goodsDetailVO.setId(param.getId());
        goodsDetailVO.setName(spu.getName());
        goodsDetailVO.setMinPrice(spu.getMinPrice());
        goodsDetailVO.setSale(spu.getSale());
        goodsDetailVO.setShop(new IdNameVO(spu.getShopId(), spu.getShopName()));
        goodsDetailVO.setPromotionType(spu.getPromotionType());
        goodsDetailVO.setPromotionStartTime(spu.getPromotionStartTime());
        goodsDetailVO.setPromotionEndTime(spu.getPromotionEndTime());
        //获取图片信息
        getPhotoList(param.getId(), goodsDetailVO);
        //获取满减信息
        goodsDetailVO.setFullReductionList(getSpuFullReductionList(param.getId()));
        //获取sku列表信息
        goodsDetailVO.setSkuList(getSkuList(param.getId()));
        //获取属性信息
        goodsDetailVO.setSpecNameList(getSpecValueList(param.getId()));
        //获取商品参数
        goodsDetailVO.setSpuParameters(getSpuSpecList(param.getId()));
        return goodsDetailVO;
    }

    private List<SpuSpecSimpleVO> getSpuSpecList(Long spuId) {
        LambdaQueryWrapper<SpuSpec> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuSpec::getSpuId, spuId);
        List<SpuSpec> spuSpecList = spuSpecMapper.selectList(wrapper);
        List<SpuSpecSimpleVO> spuSpecSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuSpecList)) {
            spuSpecSimpleVOList = spuSpecList.stream().map(x -> {
                SpuSpecSimpleVO spuSpecSimpleVO = new SpuSpecSimpleVO();
                BeanUtils.copyProperties(x, spuSpecSimpleVO);
                return spuSpecSimpleVO;
            }).collect(Collectors.toList());
        }
        return spuSpecSimpleVOList;
    }

    /**
     * 获取主图信息
     * @param spuId
     * @param goodsDetailVO
     */
    private void getPhotoList(Long spuId, GoodsDetailVO goodsDetailVO) {
        LambdaQueryWrapper<SpuImg> spuImgCondition = new LambdaQueryWrapper<>();
        spuImgCondition.eq(SpuImg::getSpuId, spuId);
        List<SpuImg> spuImgList = spuImgMapper.selectList(spuImgCondition);
        List<String> photoList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuImgList)) {
            for (SpuImg spuImg : spuImgList) {
                if (spuImg.getMasterFlag().equals(BooleanEnum.YES.getValue())) {
                    goodsDetailVO.setMasterImgUrl(spuImg.getImgUrl());
                } else if (spuImg.getColorFlag().equals(BooleanEnum.YES.getValue())) {
                    photoList.add(spuImg.getImgUrl());
                }
            }
        }
        goodsDetailVO.setPhotoList(photoList);
    }

    /**
     * 获取满减信息
     * @param spuId
     * @return
     */
    private List<SpuFullReductionSimpleVO> getSpuFullReductionList(Long spuId) {
        LambdaQueryWrapper<SpuFullReduction> fullReductionCondition = new LambdaQueryWrapper<>();
        fullReductionCondition.eq(SpuFullReduction::getSpuId, spuId);
        fullReductionCondition.eq(SpuFullReduction::getDelStatus, DelFlagEnum.NO.getValue());
        List<SpuFullReduction> spuFullReductionList = spuFullReductionMapper.selectList(fullReductionCondition);
        List<SpuFullReductionSimpleVO> spuFullReductionSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuFullReductionList)) {
            spuFullReductionSimpleVOList = spuFullReductionList.stream().map(x -> {
                SpuFullReductionSimpleVO spuFullReductionSimpleVO = new SpuFullReductionSimpleVO();
                BeanUtils.copyProperties(x, spuFullReductionSimpleVO);
                return spuFullReductionSimpleVO;
            }).collect(Collectors.toList());
        }
        return spuFullReductionSimpleVOList;
    }

    /**
     * 获取sku列表信息
     * @param spuId
     * @return
     */
    private List<SkuSimpleVO> getSkuList(Long spuId) {
        LambdaQueryWrapper<Sku> skuCondition = new LambdaQueryWrapper<>();
        skuCondition.eq(Sku::getDelStatus, DelFlagEnum.NO.getValue());
        skuCondition.eq(Sku::getSpuId, spuId);
        List<Sku> skuList = skuMapper.selectList(skuCondition);
        List<SkuSimpleVO> skuSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(skuList)) {
            skuSimpleVOList = skuList.stream().map(x -> {
                SkuSimpleVO skuSimpleVO = new SkuSimpleVO();
                BeanUtils.copyProperties(x, skuSimpleVO);
                return skuSimpleVO;
            }).collect(Collectors.toList());
        }
        return skuSimpleVOList;
    }

    /**
     * 获取商品属性信息
     * @param spuId
     * @return
     */
    private List<FatherChildrenVO> getSpecValueList(Long spuId) {
        List<FatherChildrenVO> fatherChildrenVOList = Lists.newArrayList();
        List<SkuSpec> skuSpecList = skuSpecMapper.listBySpuId(spuId);
        if (!CollectionUtils.isEmpty(skuSpecList)) {
            Map<Long, String> specNameMap = skuSpecList.stream().collect(Collectors.toMap(SkuSpec::getSpecNameId, SkuSpec::getSpecName, (x, y)->y));
            Map<Long, List<SkuSpec>> membersMap = skuSpecList.stream().collect(Collectors.groupingBy(SkuSpec::getSpecNameId));
            Iterator<Map.Entry<Long, List<SkuSpec>>> it = membersMap.entrySet().iterator();
            while (it.hasNext()) {
                FatherChildrenVO fatherChildrenVO = new FatherChildrenVO();
                Map.Entry<Long, List<SkuSpec>> entry = it.next();
                List<SkuSpec> list = entry.getValue();
                Set<IdNameVO> idNameVOS = list.stream().map(x -> {
                    IdNameVO idNameVO = new IdNameVO();
                    idNameVO.setId(x.getSpecValueId());
                    idNameVO.setName(x.getSpecValue());
                    return idNameVO;
                }).collect(Collectors.toSet());
                fatherChildrenVO.setId(entry.getKey());
                fatherChildrenVO.setName(specNameMap.get(entry.getKey()));
                fatherChildrenVO.setChildren(new ArrayList<>(idNameVOS));
                fatherChildrenVOList.add(fatherChildrenVO);
            }
        }
        return fatherChildrenVOList;
    }
}
