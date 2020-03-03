package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.rainbow.api.entity.SpuSpec;
import com.rainbow.api.vo.SpuSpecSimpleVO;
import com.rainbow.admin.mapper.SpuSpecMapper;
import com.rainbow.admin.service.ISpuSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品基本属性表 服务实现类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Service
public class SpuSpecServiceImpl extends ServiceImpl<SpuSpecMapper, SpuSpec> implements ISpuSpecService {


    @Override
    public Integer removeBySpuId(Long spuId) {
        return baseMapper.removeBySpuId(spuId);
    }

    @Override
    public List<SpuSpecSimpleVO> listBySpuId(Long spuId) {
        LambdaQueryWrapper<SpuSpec> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuSpec::getSpuId, spuId);
        List<SpuSpec> spuSpecList = baseMapper.selectList(wrapper);
        List<SpuSpecSimpleVO> spuSpecSimpleVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(spuSpecList)) {
            spuSpecSimpleVOList = spuSpecList.stream().map(x -> {
                SpuSpecSimpleVO spuSpecSimpleVO = new SpuSpecSimpleVO();
                BeanUtils.copyProperties(x, spuSpecSimpleVO);
                return spuSpecSimpleVO;
            }).collect(Collectors.toList());
        }
        return spuSpecSimpleVOList;
    }
}
