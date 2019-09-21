package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.BrandDTO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.Brand;
import com.rainbow.admin.entity.BrandItem;
import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.BrandMapper;
import com.rainbow.admin.mapper.UserMapper;
import com.rainbow.admin.service.IBrandItemService;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 品牌表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Resource
    private IBrandItemService brandItemService;

    @Resource
    private UserMapper userMapper;

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
    @Transactional
    public Integer addBrand(BrandDTO param) {

        //添加品牌
        Brand brand = new Brand();
        brand.setName(param.getName());
        brand.setCreateTime(LocalDateTime.now());
        brand.setDelStatus(DelFlagEnum.NO.getValue());
        brand.setDescription(param.getDescription());
        brand.setLogo(param.getLogo());
        Integer result = baseMapper.insert(brand);

        //获取该类别品牌总数
        LambdaQueryWrapper<BrandItem> wrapper = new LambdaQueryWrapper();
        wrapper.eq(BrandItem::getItemId,param.getItemId());
        Integer count = brandItemService.count(wrapper);

        //建立品牌类别关联关系
        BrandItem brandItem = new BrandItem();
        brandItem.setBrandId(12L);
        brandItem.setItemId(param.getItemId());
        brandItem.setCreateTime(LocalDateTime.now());
        brandItem.setDelStatus(DelFlagEnum.NO.getValue());
        brandItem.setSortId(Long.valueOf(count+1));
        brandItemService.save(brandItem);

        return result;
    }


    @Override
    public Integer removeBrand(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }
}
