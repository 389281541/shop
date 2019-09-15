package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.Brand;
import com.rainbow.admin.mapper.BrandMapper;
import com.rainbow.admin.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.dto.IdPageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public IPage<BrandSimpleVO> pageBrandByItem(IdPageDTO idPageDTO) {
        IPage<Brand> brandPage = new Page<>(idPageDTO.getPageNum(), idPageDTO.getPageSize());
        List<Brand> brands = baseMapper.pageBrandByItemId(idPageDTO.getId(), brandPage);
        brandPage.setRecords(brands);
        return brandPage.convert(
                brand -> {
                    BrandSimpleVO brandSimpleVO = new BrandSimpleVO();
                    brandSimpleVO.setId(brand.getId());
                    brandSimpleVO.setName(brand.getName());
                    brandSimpleVO.setCreateTime(brand.getCreateTime());
                    brandSimpleVO.setDescription(brand.getDescription());
                    brandSimpleVO.setLogo(brand.getLogo());
                    return brandSimpleVO;
                }
        );
    }
}
