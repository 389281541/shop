package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.SkuSpec;
import com.rainbow.admin.mapper.SkuSpecMapper;
import com.rainbow.admin.service.ISkuSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * sku属性关联表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-21
 */
@Service
public class SkuSpecServiceImpl extends ServiceImpl<SkuSpecMapper, SkuSpec> implements ISkuSpecService {
    @Override
    public Integer removeBySpuId(Long spuId) {
        return null;
    }
}
