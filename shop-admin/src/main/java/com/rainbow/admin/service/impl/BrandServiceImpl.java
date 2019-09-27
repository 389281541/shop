package com.rainbow.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.BrandSaveDTO;
import com.rainbow.admin.api.dto.BrandUpdateDTO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.Brand;
import com.rainbow.admin.entity.BrandItem;
import com.rainbow.admin.mapper.BrandItemMapper;
import com.rainbow.admin.mapper.BrandMapper;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 品牌表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@Service
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
                    brandSimpleVO.setDescription(brand.getDescription());
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
        String itemIdsStr = param.getItemIdsStr();
        String[] itemIdsArray = itemIdsStr.split(",");
        for(String itemIdStr:itemIdsArray) {
            Long itemId = Long.parseLong(itemIdStr);

            //获取该类别品牌总数
            LambdaQueryWrapper<BrandItem> wrapper = new LambdaQueryWrapper();
            wrapper.eq(BrandItem::getItemId,itemId);
            Integer count = brandItemMapper.selectCount(wrapper);

            //建立品牌类别关联关系
            BrandItem brandItem = new BrandItem();
            brandItem.setBrandId(brand.getId());
            brandItem.setItemId(itemId);
            brandItem.setCreateTime(LocalDateTime.now());
            brandItem.setDelStatus(DelFlagEnum.NO.getValue());
            brandItem.setSortId(Long.valueOf(count+1));
        }

        brandItemMapper.insert(brandItem);
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeBrand(IdDTO param) {
        Integer result = baseMapper.deleteById(param.getId());
        LambdaQueryWrapper<BrandItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BrandItem::getBrandId, param.getId());
        List<BrandItem> brandItems = brandItemMapper.selectList(wrapper);
        if(!CollectionUtils.isEmpty(brandItems)) {
            Set<Long> ids = brandItems.stream().map(BrandItem::getId).collect(Collectors.toSet());
            brandItemMapper.deleteBatchIds(ids);
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateBrand(BrandUpdateDTO param) {

        Brand brand = new Brand();
        brand.setId(param.getId());
        brand.setName(param.getName());
        brand.setLogo(param.getLogo());
        brand.setDescription(param.getDescription());
        brand.setDelStatus(DelFlagEnum.NO.getValue());
        brand.setCreateTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());

        baseMapper.updateById(brand);

        return null;
    }
}
