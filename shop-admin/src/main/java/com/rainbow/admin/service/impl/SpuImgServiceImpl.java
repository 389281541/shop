package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.entity.SpuImg;
import com.rainbow.admin.mapper.SpuImgMapper;
import com.rainbow.admin.service.ISpuImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品图片表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@Service
public class SpuImgServiceImpl extends ServiceImpl<SpuImgMapper, SpuImg> implements ISpuImgService {

    @Override
    public Integer removeBySpuId(Long spuId) {
        return baseMapper.removeBySpuId(spuId);
    }


    public List<SpuImg> listBySpuId(Long spuId) {
        LambdaQueryWrapper<SpuImg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuImg::getSpuId, spuId);
        return list(wrapper);
    }

}
