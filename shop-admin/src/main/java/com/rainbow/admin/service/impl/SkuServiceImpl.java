package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.entity.Sku;
import com.rainbow.admin.mapper.SkuMapper;
import com.rainbow.admin.service.ISkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品sku表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Override
    public List<Sku> listBySpuId(Long spuId) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sku::getSpuId, spuId);
        wrapper.eq(Sku::getDelStatus, DelFlagEnum.NO.getValue());
        return list(wrapper);
    }


    @Override
    public Integer removeBySpuId(Long spuId) {
        return null;
    }
}
