package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.*;
import com.rainbow.admin.api.vo.*;
import com.rainbow.admin.entity.*;
import com.rainbow.admin.enums.AdminErrorCode;
import com.rainbow.admin.mapper.SpuMapper;
import com.rainbow.admin.service.*;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdSearchDTO;
import com.rainbow.common.dto.StatusBatchChangeDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

/**
 * 商品表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@Service
@Slf4j
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ISkuSpecService skuSpecService;

    @Autowired
    private ISpuImgService spuImgService;

    @Autowired
    private ISpuSpecService spuSpecService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private ISpuFullReductionService spuFullReductionService;


    @Override
    public IPage<SpuSimpleVO> pageSpu(SpuSearchDTO param) {
        log.info("pageSpu parameter is param = {}", param);
        Page<Spu> spuPage = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<Spu> spuIPage = baseMapper.pageSpu(spuPage, param);
        return spuIPage.convert(x -> {
            SpuSimpleVO spuSimpleVO = new SpuSimpleVO();
            BeanUtils.copyProperties(x, spuSimpleVO);
            return spuSimpleVO;
        });
    }

    @Override
    public Integer addSpu(SpuSaveDTO param) {
        log.info("addSpu parameter is param = {}", param);
        Spu spu = new Spu();
        BeanUtils.copyProperties(param, spu);
        int res = baseMapper.insert(spu);
        Long spuId = spu.getId();
        log.info("addSpu spuId = {}", spuId);
        buildRelation(spuId, param);
        return res;
    }

    private void buildRelation(Long spuId, SpuSaveDTO param) {
        //sku参数
        List<SkuSaveDTO> skuSaveDTOList = param.getSkuList();
        //关联sku  回填skuId到skuSaveDTOList
        relateAndInsertSku(skuSaveDTOList, spuId);
        //关联sku规格
        relateAndInsertSkuSpec(skuSaveDTOList);
        //关联SPU参数
        List<SpuSpecSaveDTO> spuSpecSaveDTOList = param.getSpuSpecList();
        relateAndInsertSpuSpec(spuSpecSaveDTOList, spuId);
        //关联图片
        List<SpuImgSaveDTO> spuImgSaveDTOList = param.getSpuImgList();
        relateAndInsertSpuImg(spuImgSaveDTOList, skuSaveDTOList, spuId);
        //关联满减
        List<SpuFullReductionSaveDTO> spuFullReductionSaveDTOList = param.getSpuFullReductionList();
        relateAndInsertSpuFulReduction(spuFullReductionSaveDTOList, spuId);
    }

    private void relateAndInsertSpuFulReduction(List<SpuFullReductionSaveDTO> spuImgSaveDTOList, Long spuId) {
        List<SpuFullReduction> spuFullReductionList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(spuImgSaveDTOList)) {
            for(int i=0; i<spuImgSaveDTOList.size(); i++) {
                SpuFullReductionSaveDTO spuFullReductionSaveDTO = spuImgSaveDTOList.get(i);
                SpuFullReduction spuFullReduction = new SpuFullReduction();
                spuFullReduction.setSpuId(spuId);
                spuFullReduction.setFullPrice(spuFullReductionSaveDTO.getFullPrice());
                spuFullReduction.setReducePrice(spuFullReductionSaveDTO.getReducePrice());
                spuFullReductionList.add(spuFullReduction);
            }
        }
        spuFullReductionService.saveBatch(spuFullReductionList);
    }


    private void relateAndInsertSpuSpec(List<SpuSpecSaveDTO> spuSpecSaveDTOList, Long spuId) {
        List<SpuSpec> spuSpecList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuSpecSaveDTOList)) {
            for (int i = 0; i < spuSpecSaveDTOList.size(); i++) {
                SpuSpecSaveDTO spuSpecSaveDTO = spuSpecSaveDTOList.get(i);
                SpuSpec spuSpec = new SpuSpec();
                spuSpec.setSpuId(spuId);
                spuSpec.setSpecNameId(spuSpecSaveDTO.getSpecNameId());
                spuSpec.setSpecName(spuSpecSaveDTO.getSpecName());
                spuSpec.setSpecValueId(spuSpecSaveDTO.getSpecValueId());
                spuSpec.setSpecValue(spuSpecSaveDTO.getSpecValue());
                spuSpec.setCreateTime(LocalDateTime.now());
                spuSpecList.add(spuSpec);
            }
        }
        spuSpecService.saveBatch(spuSpecList);
    }

    private void relateAndInsertSku(List<SkuSaveDTO> skuSaveList, Long spuId) {
        //关联sku spuId
        if (CollectionUtils.isEmpty(skuSaveList))
            return;
        for (SkuSaveDTO skuSaveDTO : skuSaveList) {
            skuSaveDTO.setSpuId(spuId);
        }
        //填充skuNo
        for (int i = 0; i < skuSaveList.size(); i++) {
            SkuSaveDTO skuSaveDTO = skuSaveList.get(i);
            if (StringUtils.isEmpty(skuSaveDTO.getSkuNo())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(formatter.format(LocalDate.now()));
                //六位位商品id
                sb.append(String.format("%04d", spuId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuSaveDTO.setSkuNo(sb.toString());
            }
        }
        //保存sku
        for (SkuSaveDTO skuSaveDTO : skuSaveList) {
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuSaveDTO, sku);
            skuService.save(sku);
            skuSaveDTO.setSkuId(sku.getId());
            List<SkuSpecSaveDTO> skuSpecList = skuSaveDTO.getSkuSpecList();
            if(!CollectionUtils.isEmpty(skuSpecList)) {
                for(SkuSpecSaveDTO skuSpecSaveDTO: skuSpecList) {
                    skuSpecSaveDTO.setSkuId(sku.getId());
                }
            }
        }

    }

    private void relateAndInsertSpuImg(List<SpuImgSaveDTO> spuImgSaveDTOList, List<SkuSaveDTO> skuSaveDTOList, Long spuId) {
        List<SpuImg> spuImgList = Lists.newArrayList();
        int count = 0;
        int skuSize = skuSaveDTOList.size();
        int imgSize = spuImgSaveDTOList.size();
        if (!CollectionUtils.isEmpty(skuSaveDTOList)) {
            for (int i = 0; i < skuSize; i++) {
                SkuSaveDTO skuSaveDTO = skuSaveDTOList.get(i);
                SpuImgSaveDTO spuImgSaveDTO = spuImgSaveDTOList.get(i);
                SpuImg spuImg = new SpuImg();
                BeanUtils.copyProperties(spuImgSaveDTO, spuImg);
                spuImg.setSkuId(skuSaveDTO.getSkuId());
                spuImg.setSpuId(spuId);
                spuImg.setSortId(++count);
                spuImg.setCreateTime(LocalDateTime.now());
                spuImgList.add(spuImg);
            }
        }

        if (imgSize > skuSize) {
            for (int i = skuSize; i < imgSize; i++) {
                SpuImgSaveDTO spuImgSaveDTO = spuImgSaveDTOList.get(i);
                SpuImg spuImg = new SpuImg();
                BeanUtils.copyProperties(spuImgSaveDTO, spuImg);
                spuImg.setSortId(++count);
                spuImg.setCreateTime(LocalDateTime.now());
                spuImgList.add(spuImg);
            }
        }
        spuImgService.insertBatch(spuImgList);
    }


    private void relateAndInsertSkuSpec(List<SkuSaveDTO> skuSaveDTOList) {
        List<SkuSpec> skuSpecList = Lists.newArrayList();
        int count = 0;
        for (int i = 0; i < skuSaveDTOList.size(); i++) {
            SkuSaveDTO skuSaveDTO = skuSaveDTOList.get(i);
            List<SkuSpecSaveDTO> skuSaveSpecList = skuSaveDTO.getSkuSpecList();
            for (int j = 0; j < skuSaveSpecList.size(); j++) {
                SkuSpecSaveDTO skuSpecSaveDTO = skuSaveSpecList.get(j);
                SkuSpec skuSpec = new SkuSpec();
                BeanUtils.copyProperties(skuSpecSaveDTO, skuSpec);
                skuSpec.setSortId(++count);
                skuSpec.setCreateTime(LocalDateTime.now());
                skuSpecList.add(skuSpec);
            }
        }
        Integer res = skuSpecService.insertBatch(skuSpecList);
        System.out.println(res);
    }


    @Override
    public Boolean removeSpu(IdDTO param) {
        //查看sku是否全部下架
        Long spuId = param.getId();
        Spu spu = getById(spuId);
        if (spu.getSaleStatus().equals(BooleanEnum.YES.getValue())) {
            throw new BusinessException(AdminErrorCode.SPU_PULL_OFF);
        }
        removeRelation(spuId);
        //删除spu表
        Boolean res = removeById(spuId);
        return res;
    }

    private void removeRelation(Long spuId) {
        //删除图片关联表
        spuImgService.removeBySpuId(spuId);
        List<SkuSimpleVO> skuList = skuService.listBySpuId(new IdSearchDTO(spuId));
        //删除sku_spec关联表
        if (!CollectionUtils.isEmpty(skuList)) {
            List<Long> skuIds = skuList.stream().map(SkuSimpleVO::getId).collect(Collectors.toList());
            skuSpecService.removeBySkuIds(skuIds);
        }
        //删除关联sku
        skuService.removeBySpuId(spuId);
        //删除关联spu参数
        spuSpecService.removeBySpuId(spuId);
        //删除关联优惠
        spuFullReductionService.removeBySpuId(spuId);
    }

    @Override
    public Boolean updateSpu(SpuUpdateDTO param) {
        Long spuId = param.getId();
        //删除关联关系
        removeRelation(spuId);
        //建立关联关系
        buildRelation(spuId, param);
        Spu spu = new Spu();
        BeanUtils.copyProperties(param, spu);
        Boolean res = updateById(spu);
        return res;
    }

    @Override
    public SpuDetailVO getSpuDetailById(IdDTO param) {
        SpuDetailVO spuDetailVO = new SpuDetailVO();
        Long spuId = param.getId();
        Spu spu = getById(spuId);
        BeanUtils.copyProperties(spu, spuDetailVO);

        Brand brand = brandService.getById(spu.getBrandId());
        spuDetailVO.setBrandId(brand.getId());
        spuDetailVO.setBrandName(brand.getName());
        Item item = itemService.getById(spu.getItemId());
        spuDetailVO.setItemId(item.getId());
        spuDetailVO.setParentItemId(item.getParentId());
        spuDetailVO.setItemName(item.getName());
        Shop shop = shopService.getById(spu.getShopId());
        spuDetailVO.setShopId(shop.getId());
        spuDetailVO.setShopName(shop.getName());

        List<SkuSimpleVO> skuSimpleVOList = skuService.listBySpuId(new IdSearchDTO(spuId));
        List<SpuImgSimpleVO> spuImgSimpleVOList = spuImgService.listBySpuId(spuId);
        List<SpuSpecSimpleVO> spuSpecSimpleVOList = spuSpecService.listBySpuId(spuId);
        List<SpuFullReductionSimpleVO> spuFullReductionSimpleVOList = spuFullReductionService.listBySpuId(spuId);

        spuDetailVO.setSkuList(skuSimpleVOList);
        spuDetailVO.setSpuImgList(spuImgSimpleVOList);
        spuDetailVO.setSpuSpecList(spuSpecSimpleVOList);
        spuDetailVO.setSpuFullReductionList(spuFullReductionSimpleVOList);
        return spuDetailVO;
    }


    @Override
    public Boolean setSaleStatus(StatusBatchChangeDTO param) {
        Integer affectRow = baseMapper.updateSaleStatus(param);
        return affectRow > 0;
    }

    @Override
    public Boolean setRecommendStatus(StatusBatchChangeDTO param) {
        Integer affectRow = baseMapper.updateRecommendStatus(param);
        return affectRow > 0;
    }

    @Override
    public List<SpuSimpleVO> listSimple(String keyword) {
        if(Strings.isBlank(keyword)) {
            return Lists.newArrayList();
        }
        return baseMapper.listSimple(keyword);
    }
}
