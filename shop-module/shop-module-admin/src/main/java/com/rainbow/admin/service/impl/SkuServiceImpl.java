package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.api.dto.SkuBatchUpdateDTO;
import com.rainbow.api.dto.SkuUpdateDTO;
import com.rainbow.api.entity.Sku;
import com.rainbow.api.vo.SkuSimpleVO;
import com.rainbow.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.mapper.SkuMapper;
import com.rainbow.admin.service.ISkuService;
import com.rainbow.admin.service.ISkuSpecService;
import com.rainbow.common.dto.IdSearchDTO;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品sku表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Autowired
    private ISkuSpecService skuSpecService;

    @Override
    public List<SkuSimpleVO> listBySpuId(IdSearchDTO param) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sku::getSpuId, param.getId());
        wrapper.like(param.getKeyword() != null, Sku::getSkuNo, param.getKeyword());
        wrapper.eq(Sku::getDelStatus, DelFlagEnum.NO.getValue());
        List<Sku> skuList = baseMapper.selectList(wrapper);
        List<SkuSimpleVO> skuSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(skuList)) {
            skuSimpleVOList = skuList.stream().map(x -> {
                SkuSimpleVO skuSimpleVO = new SkuSimpleVO();
                BeanUtils.copyProperties(x, skuSimpleVO);
                List<SkuSpecSimpleVO> skuSpecSimpleVOList = skuSpecService.listBySkuId(x.getId());
                skuSimpleVO.setSkuSpecList(skuSpecSimpleVOList);
                Map<String, String> skuSpecMap = skuSpecSimpleVOList.stream().collect(Collectors.toMap(SkuSpecSimpleVO::getSpecName, SkuSpecSimpleVO::getSpecValue));
                skuSimpleVO.setSkuSpecMap(skuSpecMap);
                return skuSimpleVO;
            }).collect(Collectors.toList());
        }
        return skuSimpleVOList;
    }


    @Override
    public Integer removeBySpuId(Long spuId) {
        return baseMapper.removeBySpuId(spuId);
    }

    @Override
    public Boolean updateBatch(SkuBatchUpdateDTO param) {
        List<SkuUpdateDTO> skuUpdateDTOList = param.getSkuUpdateDTOList();
        Integer affectRow = baseMapper.updateBatch(skuUpdateDTOList);
        return affectRow > 0;
    }
}
