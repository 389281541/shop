package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.SpuDetailVO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.*;
import com.rainbow.admin.enums.AdminErrorCode;
import com.rainbow.admin.mapper.SpuMapper;
import com.rainbow.admin.service.*;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ISkuSpecService skuSpecService;

    @Autowired
    private ISpecValueService specValueService;

    @Autowired
    private ISpuImgService spuImgService;

    @Override
    public IPage<SpuSimpleVO> pageSpu(SpuSearchDTO param) {
        Page<Spu> spuPage = new Page<>(param.getPageNum(), param.getPageSize());
        return baseMapper.pageSpu(param, spuPage);
    }

    @Override
    public Integer addSpu(SpuSaveDTO param) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(param, spu);
        int res = baseMapper.insert(spu);
        buildRelation(spu, param);
        return res;
    }

    private void buildRelation(Spu spu, SpuSaveDTO param) {
        Long spuId = spu.getId();
        List<List<Long>> specValueIdsList = Lists.newArrayList();
        List<SkuSimpleDTO> skuDtoList = param.getSkuList();
        List<Sku> skuList = skuDtoList.stream().map(x -> {
            List<Long> specValueIdList = x.getSpecValueIdList();
            specValueIdsList.add(specValueIdList);
            Sku sku = new Sku();
            BeanUtils.copyProperties(x, sku);
            sku.setSkuName(getSkuName(specValueIdList, param.getName()));
            sku.setSpuId(spuId);
            return sku;
        }).collect(Collectors.toList());
        skuService.saveBatch(skuList);
        buildSkuSpecValueRelation(skuList, specValueIdsList);
        List<SpuImgSimpleDTO> spuImgList = param.getSpuImgList();
        buildImgRelation(skuDtoList, skuList, spuImgList, spuId);

    }

    private void buildImgRelation(List<SkuSimpleDTO> skuDtoList, List<Sku> skuList, List<SpuImgSimpleDTO> spuImgSimpleDTOList, Long spuId) {
        List<SpuImg> spuImgList = Lists.newArrayList();
        int count = 0;
        //sku图片部分
        for (int i = 0; i < skuDtoList.size(); i++) {
            SkuSimpleDTO skuSimpleDTO = skuDtoList.get(i);
            Sku sku = skuList.get(i);
            SpuImg spuImg = new SpuImg();
            spuImg.setSkuId(sku.getId());
            spuImg.setSpuId(sku.getSpuId());
            spuImg.setSortId(++count);
            spuImg.setImgUrl(skuSimpleDTO.getImgUrl());
            spuImg.setIsColor(BooleanEnum.NO.getValue());
            spuImg.setIsMaster(BooleanEnum.NO.getValue());
            spuImg.setIsCover(BooleanEnum.NO.getValue());
            spuImg.setCreateTime(LocalDateTime.now());
            spuImg.setDelStatus(DelFlagEnum.NO.getValue());
            spuImgList.add(spuImg);
        }
        List<SpuImg> imgList = Lists.newArrayList();
        for(SpuImgSimpleDTO spuImgSimpleDTO:spuImgSimpleDTOList) {
            SpuImg spuImg = new SpuImg();
            spuImg.setSpuId(spuId);
            spuImg.setImgUrl(spuImgSimpleDTO.getImgUrl());
            spuImg.setIsCover(DelFlagEnum.NO.getValue());
            spuImg.setIsMaster(DelFlagEnum.NO.getValue());
            spuImg.setIsColor(DelFlagEnum.NO.getValue());
            spuImg.setCreateTime(LocalDateTime.now());
            spuImg.setSortId(++count);
            imgList.add(spuImg);
        }
        spuImgList.addAll(imgList);
        spuImgService.saveBatch(spuImgList);
    }

    private void buildSkuSpecValueRelation(List<Sku> skuList, List<List<Long>> specValueIdsList) {
        List<SkuSpec> skuSpecList = Lists.newArrayList();
        int count = 0;
        for (int i = 0; i < skuList.size(); i++) {
            Sku sku = skuList.get(i);
            List<Long> specValueIdList = specValueIdsList.get(i);
            for (int j = 0; j < specValueIdList.size(); j++) {
                SkuSpec skuSpec = new SkuSpec();
                skuSpec.setSkuId(sku.getId());
                skuSpec.setSortId(++count);
                skuSpec.setSpecValueId(specValueIdList.get(j));
                skuSpec.setCreateTime(LocalDateTime.now());
                skuSpec.setDelStatus(DelFlagEnum.NO.getValue());
                skuSpecList.add(skuSpec);
            }
        }
        skuSpecService.saveBatch(skuSpecList);
    }


    private String getSkuName(List<Long> specValueIdList, String spuName) {
        StringBuilder skuName = new StringBuilder();
        if (!CollectionUtils.isEmpty(specValueIdList)) {
            //获取
            Collection<SpecValue> specValues = specValueService.listByIds(specValueIdList);
            for (SpecValue specValue : specValues) {
                skuName.append(specValue.getSpecValue());
            }
        }
        skuName.append(spuName);
        return skuName.toString();
    }


    @Override
    public Boolean removeSpu(IdDTO param) {
        //查看sku是否全部下架
        Long spuId = param.getId();
        Spu spu = getById(spuId);
        if(spu.getSaleStatus().equals(BooleanEnum.YES.getValue())) {
            throw new BusinessException(AdminErrorCode.SPU_PULL_OFF);
        }
        //删除spu表
        Boolean res = removeById(spuId);
        //删除图片关联表
        spuImgService.removeBySpuId(spuId);
        //删除关联sku
        skuService.removeBySpuId(spuId);
        return res;
    }

    @Override
    public Boolean updateSpu(SpuUpdateDTO param) {
        Long spuId = param.getId();
        //删除图片关联表
        spuImgService.removeBySpuId(spuId);
        //删除关联sku
        skuService.removeBySpuId(spuId);
        //更改spu
        Spu spu = new Spu();
        BeanUtils.copyProperties(param, spu);
        Boolean res = updateById(spu);
        buildRelation(spu, param);
        return res;
    }

    @Override
    public SpuDetailVO getSpuDetailById(IdDTO param) {
        SpuDetailVO spuDetailVO = new SpuDetailVO();
        Long spuId = param.getId();
        Spu spu = getById(spuId);
        BeanUtils.copyProperties(spu, spuDetailVO);
        List<SpuImg> spuImgList = spuImgService.listBySpuId(spuId);
        List<Sku> skuList = skuService.listBySpuId(spuId);
        spuDetailVO.setSkuList(skuList);
        return null;
    }
}
