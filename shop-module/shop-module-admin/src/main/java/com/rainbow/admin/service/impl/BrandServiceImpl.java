package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.BrandSaveDTO;
import com.rainbow.admin.api.dto.BrandUpdateDTO;
import com.rainbow.admin.api.vo.BrandDetailVO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.api.entity.Brand;
import com.rainbow.admin.api.entity.BrandItem;
import com.rainbow.admin.mapper.BrandItemMapper;
import com.rainbow.admin.mapper.BrandMapper;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.model.KV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 品牌表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@Service
@Slf4j
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Resource
    private BrandItemMapper brandItemMapper;

    @Override
    public IPage<BrandSimpleVO> pageBrandByItem(IdNamePageDTO idNamePageDTO) {
        IPage<Brand> brandPage = new Page<>(idNamePageDTO.getPageNum(), idNamePageDTO.getPageSize());
        List<Brand> brands = baseMapper.pageBrandByItemId(idNamePageDTO.getId(), idNamePageDTO.getName(), brandPage);
        brandPage.setRecords(brands);
        return brandPage.convert(
                brand -> {
                    BrandSimpleVO brandSimpleVO = new BrandSimpleVO();
                    brandSimpleVO.setId(brand.getId());
                    brandSimpleVO.setName(brand.getName());
                    brandSimpleVO.setCreateTime(brand.getCreateTime());
                    brandSimpleVO.setUpdateTime(brand.getUpdateTime());
                    brandSimpleVO.setLogo(brand.getLogo());
                    return brandSimpleVO;
                }
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addBrand(BrandSaveDTO param) {

        //添加品牌
        Brand brand = new Brand();
        brand.setName(param.getName());
        brand.setCreateTime(LocalDateTime.now());
        brand.setDelStatus(DelFlagEnum.NO.getValue());
        brand.setDescription(param.getDescription());
        brand.setLogo(param.getLogo());
        Integer result = baseMapper.insert(brand);
        //获取类别
        List<Long> itemIds = param.getItemIds();
        //建立品牌-类别
        buildBrandItemRelation(itemIds, brand.getId());
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeBrand(IdDTO param) {
        Long brandId = param.getId();
        Integer result = baseMapper.deleteById(brandId);
        removeBrandItemRelation(brandId);
        return result;
    }

    @Override
    public Boolean updateBrand(BrandUpdateDTO param) {

        //更新品牌内容
        Long brandId = param.getId();
        List<Long> itemIds = param.getItemIds();
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setName(param.getName());
        brand.setLogo(param.getLogo());
        brand.setDescription(param.getDescription());
        brand.setUpdateTime(LocalDateTime.now());
        Integer brandChange = baseMapper.updateById(brand);
        //删除旧关联
        removeBrandItemRelation(brandId);
        //建立新关联
        Integer relationChange = buildBrandItemRelation(itemIds, brandId);
        if (brandChange == 0 && relationChange == 0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    @Override
    public BrandDetailVO getBrandDetailById(IdDTO param) {

        BrandDetailVO brandDetailVO = new BrandDetailVO();
        Long brandId = param.getId();
        Brand brand = baseMapper.selectById(brandId);
        brandDetailVO.setId(brand.getId());
        brandDetailVO.setName(brand.getName());
        brandDetailVO.setLogo(brand.getLogo());
        brandDetailVO.setCreateTime(brand.getCreateTime());
        brandDetailVO.setDescription(brand.getDescription());

        LambdaQueryWrapper<BrandItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BrandItem::getBrandId, brandId);
        List<BrandItem> brandItems = brandItemMapper.selectList(wrapper);
        List<Long> itemIds = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(brandItems)) {
            itemIds = brandItems.stream().map(BrandItem::getItemId).collect(Collectors.toList());
        }
        brandDetailVO.setItemIds(itemIds);
        return brandDetailVO;
    }

    private Integer buildBrandItemRelation(List<Long> itemIds, Long brandId) {

        List<KV<Long, Long>> brandCountList = brandItemMapper.getBrandCountByItemId();
        Map<Long, Long> brandCountMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(brandCountList)) {
            brandCountMap = brandCountList.stream().collect(Collectors.toMap(KV::getK, KV::getV));
        }
        List<BrandItem> brandItems = Lists.newArrayList();
        for (Long itemId : itemIds) {
            //建立品牌类别关联关系
            BrandItem brandItem = new BrandItem();
            brandItem.setBrandId(brandId);
            brandItem.setItemId(itemId);
            brandItem.setCreateTime(LocalDateTime.now());
            Long brandCount = brandCountMap.get(itemId);
            brandItem.setSortId((brandCount == null ? 0 : brandCount) + 1);
            brandItems.add(brandItem);
        }

        return brandItemMapper.insertBatch(brandItems);
    }


    private Integer removeBrandItemRelation(Long brandId) {
        Integer result = 0;
        LambdaQueryWrapper<BrandItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BrandItem::getBrandId, brandId);
        List<BrandItem> brandItems = brandItemMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(brandItems)) {
            Set<Long> ids = brandItems.stream().map(BrandItem::getId).collect(Collectors.toSet());
            result = brandItemMapper.deleteBatchIds(ids);
        }
        return result;
    }


}
