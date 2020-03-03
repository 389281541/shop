package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.rainbow.api.vo.SpuFullReductionSimpleVO;
import com.rainbow.api.entity.SpuFullReduction;
import com.rainbow.admin.mapper.SpuFullReductionMapper;
import com.rainbow.admin.service.ISpuFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * spu满减表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-24
 */
@Service
public class SpuFullReductionServiceImpl extends ServiceImpl<SpuFullReductionMapper, SpuFullReduction> implements ISpuFullReductionService {

    @Override
    public List<SpuFullReductionSimpleVO> listBySpuId(Long spuId) {
        LambdaQueryWrapper<SpuFullReduction> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuFullReduction::getSpuId, spuId);
        wrapper.eq(SpuFullReduction::getDelStatus, DelFlagEnum.NO.getValue());
        List<SpuFullReduction> spuFullReductionList = baseMapper.selectList(wrapper);
        List<SpuFullReductionSimpleVO> spuFullReductionSimpleVOList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(spuFullReductionList)) {
            spuFullReductionSimpleVOList = spuFullReductionList.stream().map(x->{
                SpuFullReductionSimpleVO spuFullReductionSimpleVO = new SpuFullReductionSimpleVO();
                BeanUtils.copyProperties(x, spuFullReductionSimpleVO);
                return spuFullReductionSimpleVO;
            }).collect(Collectors.toList());
        }
        return spuFullReductionSimpleVOList;
    }

    @Override
    public Integer removeBySpuId(Long spuId) {
        return baseMapper.removeBySpuId(spuId);
    }
}
