package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.entity.SkuSpec;
import com.rainbow.admin.entity.SpecValue;
import com.rainbow.admin.mapper.SkuSpecMapper;
import com.rainbow.admin.service.ISkuSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.service.ISpecValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * sku属性关联表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-21
 */
@Service
public class SkuSpecServiceImpl extends ServiceImpl<SkuSpecMapper, SkuSpec> implements ISkuSpecService {


    @Override
    public Integer removeBySkuIds(Collection<Long> skuIdList) {
        return baseMapper.removeBySkuIds(skuIdList);
    }

    @Override
    public List<SkuSpecSimpleVO> listBySkuId(Long skuId) {
        List<SkuSpecSimpleVO> skuSpecSimpleVOList = baseMapper.listBySkuId(skuId);
        if(CollectionUtils.isEmpty(skuSpecSimpleVOList)) {
           return Lists.newArrayList();
        }
        return skuSpecSimpleVOList;
    }



}
