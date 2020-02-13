package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.api.vo.SpuImgSimpleVO;
import com.rainbow.admin.entity.SpuImg;
import com.rainbow.admin.mapper.SpuImgMapper;
import com.rainbow.admin.service.ISkuSpecService;
import com.rainbow.admin.service.ISpuImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品图片表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@Service
public class SpuImgServiceImpl extends ServiceImpl<SpuImgMapper, SpuImg> implements ISpuImgService {

    @Autowired
    private ISkuSpecService skuSpecService;

    @Override
    public Integer removeBySpuId(Long spuId) {
        return baseMapper.removeBySpuId(spuId);
    }


    public List<SpuImgSimpleVO> listBySpuId(Long spuId) {
        List<SpuImgSimpleVO> spuImgSimpleVOList = Lists.newArrayList();

        List<SkuSpecSimpleVO> skuSpecSimpleVOList = skuSpecService.listBySpuId(spuId);
        Map<Long, List<SkuSpecSimpleVO>> skuIdMap = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(skuSpecSimpleVOList)) {
            skuIdMap = skuSpecSimpleVOList.stream().collect(Collectors.groupingBy(SkuSpecSimpleVO::getSkuId));
        }
        LambdaQueryWrapper<SpuImg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuImg::getSpuId, spuId);
        List<SpuImg> spuImgList = list(wrapper);
        if (!CollectionUtils.isEmpty(spuImgList)) {
            final Map<Long, List<SkuSpecSimpleVO>> skuIdDic = skuIdMap;
            spuImgSimpleVOList = spuImgList.stream().map(x -> {
                SpuImgSimpleVO spuImgSimpleVO = new SpuImgSimpleVO();
                BeanUtils.copyProperties(x, spuImgSimpleVO);
                spuImgSimpleVO.setSkuSpecList(skuIdDic.get(x.getSkuId()));
                return spuImgSimpleVO;
            }).collect(Collectors.toList());
        }
        return spuImgSimpleVOList;
    }

}
