package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.SpuDetailVO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.Sku;
import com.rainbow.admin.entity.SkuSpec;
import com.rainbow.admin.entity.Spu;
import com.rainbow.admin.entity.SpuImg;
import com.rainbow.admin.enums.AdminErrorCode;
import com.rainbow.admin.mapper.SpuMapper;
import com.rainbow.admin.service.*;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.exception.BusinessException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
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
        Long spuId = spu.getId();
        //sku参数
        List<SkuSimpleDTO> skuSimpleDTOList = param.getSkuSimpleDTOList();
        List<String> imgList = param.getImgList();
        //关联sku spuId
        relateSkuList(skuSimpleDTOList, spuId);
        //填充skuNo
        fillSkuNo(skuSimpleDTOList, spuId);
        //保存sku
        insertList(skuSimpleDTOList);
        //关联sku属性
        relateSkuSpecValue(skuSimpleDTOList);
        //关联图片
        relateSpuImg(imgList, skuSimpleDTOList, spuId);
        return res;
    }

    private void relateSpuImg(List<String> imgList, List<SkuSimpleDTO> skuSimpleDTOList, Long spuId) {
        List<SpuImg> spuImgList = Lists.newArrayList();
        int count = 0;
        if (!CollectionUtils.isEmpty(imgList)) {
            for (String img : imgList) {
                SpuImg spuImg = new SpuImg();
                spuImg.setSpuId(spuId);
                spuImg.setSkuId(null);
                spuImg.setImgUrl(img);
                spuImg.setIsColor(BooleanEnum.NO.getValue());
                spuImg.setIsMaster(BooleanEnum.YES.getValue());
                spuImg.setIsCover(BooleanEnum.NO.getValue());
                spuImg.setSortId(++count);
                spuImg.setCreateTime(LocalDateTime.now());
                spuImgList.add(spuImg);
            }
        }

        if (!CollectionUtils.isEmpty(skuSimpleDTOList)) {
            for (int i = 0; i < skuSimpleDTOList.size(); i++) {
                SkuSimpleDTO skuSimpleDTO = skuSimpleDTOList.get(i);
                if (Strings.isNotBlank(skuSimpleDTO.getImgUrl())) {
                    continue;
                }
                SpuImg spuImg = new SpuImg();
                spuImg.setSpuId(spuId);
                spuImg.setSkuId(skuSimpleDTO.getSkuId());
                spuImg.setImgUrl(skuSimpleDTO.getImgUrl());
                spuImg.setIsColor(BooleanEnum.YES.getValue());
                spuImg.setIsMaster(BooleanEnum.NO.getValue());
                spuImg.setIsCover(i == 0 ? BooleanEnum.YES.getValue() : BooleanEnum.NO.getValue());
                spuImg.setSortId(++count);
                spuImg.setCreateTime(LocalDateTime.now());
                spuImgList.add(spuImg);
            }
        }
        spuImgService.saveBatch(spuImgList);
    }


    private void insertList(List<SkuSimpleDTO> skuSimpleDTOList) {
        if (CollectionUtils.isEmpty(skuSimpleDTOList))
            return;
        for (SkuSimpleDTO skuSimpleDTO : skuSimpleDTOList) {
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuSimpleDTO, sku);
            skuService.save(sku);
            skuSimpleDTO.setSkuId(sku.getId());
        }
    }

    private void relateSkuList(List<SkuSimpleDTO> skuSimpleDTOList, Long spuId) {
        if (CollectionUtils.isEmpty(skuSimpleDTOList))
            return;
        for (SkuSimpleDTO skuSimpleDTO : skuSimpleDTOList) {
            skuSimpleDTO.setSpuId(spuId);
        }
    }

    private void fillSkuNo(List<SkuSimpleDTO> skuSimpleDTOList, Long spuId) {
        if (CollectionUtils.isEmpty(skuSimpleDTOList))
            return;
        for (int i = 0; i < skuSimpleDTOList.size(); i++) {
            SkuSimpleDTO skuSimpleDTO = skuSimpleDTOList.get(i);
            if (StringUtils.isEmpty(skuSimpleDTO.getSkuNo())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(formatter.format(LocalDate.now()));
                //六位位商品id
                sb.append(String.format("%04d", spuId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuSimpleDTO.setSkuNo(sb.toString());
            }
        }
    }

    private void relateSkuSpecValue(List<SkuSimpleDTO> skuSimpleDTOList) {
        List<SkuSpec> skuSpecList = Lists.newArrayList();
        int count = 0;
        for (int i = 0; i < skuSimpleDTOList.size(); i++) {
            SkuSimpleDTO skuSimpleDTO = skuSimpleDTOList.get(i);
            List<Long> specValueIdList = skuSimpleDTO.getSpecValueIdList();
            for (int j = 0; j < specValueIdList.size(); j++) {
                SkuSpec skuSpec = new SkuSpec();
                skuSpec.setSkuId(skuSimpleDTO.getSkuId());
                skuSpec.setSortId(++count);
                skuSpec.setSpecValueId(specValueIdList.get(j));
                skuSpec.setCreateTime(LocalDateTime.now());
                skuSpecList.add(skuSpec);
            }
        }
        skuSpecService.saveBatch(skuSpecList);
    }


    @Override
    public Boolean removeSpu(IdDTO param) {
        //查看sku是否全部下架
        Long spuId = param.getId();
        Spu spu = getById(spuId);
        if (spu.getSaleStatus().equals(BooleanEnum.YES.getValue())) {
            throw new BusinessException(AdminErrorCode.SPU_PULL_OFF);
        }
        //删除图片关联表
        spuImgService.removeBySpuId(spuId);

        //删除关联sku
        skuService.removeBySpuId(spuId);
        //删除spu表
        Boolean res = removeById(spuId);
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
