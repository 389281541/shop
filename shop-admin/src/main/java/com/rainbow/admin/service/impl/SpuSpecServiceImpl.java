package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.entity.SpuSpec;
import com.rainbow.admin.mapper.SpuSpecMapper;
import com.rainbow.admin.service.ISpuSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SpuSpec> listBySpuId(Long spuId) {
        LambdaQueryWrapper<SpuSpec> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuSpec::getSpuId, spuId);
        return baseMapper.selectList(wrapper);
    }
}
