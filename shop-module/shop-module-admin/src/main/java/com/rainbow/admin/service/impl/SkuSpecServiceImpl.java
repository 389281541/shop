package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.api.vo.SkuSpecSimpleVO;
import com.rainbow.api.entity.SkuSpec;
import com.rainbow.admin.mapper.SkuSpecMapper;
import com.rainbow.admin.service.ISkuSpecService;
import com.rainbow.common.vo.MemberShipVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
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
        List<SkuSpec> skuSpecList = baseMapper.listBySkuId(skuId);
        return getSkuSpecSimpleVOList(skuSpecList);
    }

    @Override
    public List<SkuSpecSimpleVO> listBySpuId(Long spuId) {
        List<SkuSpec> skuSpecList = baseMapper.listBySpuId(spuId);
        return getSkuSpecSimpleVOList(skuSpecList);
    }

    @Override
    public List<MemberShipVO> listMemberShipBySpuId(Long spuId) {
        List<SkuSpec> skuSpecList = baseMapper.listBySpuId(spuId);
        List<MemberShipVO> memberShipVOList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(skuSpecList)) {
            Map<Long, String> specNameId2Name = skuSpecList.stream().collect(Collectors.toMap(SkuSpec::getSpecNameId, SkuSpec::getSpecName));
            Map<Long, List<SkuSpec>> membersMap = skuSpecList.stream().collect(Collectors.groupingBy(SkuSpec::getSpecNameId));
            Iterator<Map.Entry<Long,List<SkuSpec>>> it = membersMap.entrySet().iterator();
            while (it.hasNext()) {
                MemberShipVO memberShipVO = new MemberShipVO();
                Map.Entry<Long, List<SkuSpec>> entry = it.next();
                Long specNameId = entry.getKey();
                List<SkuSpec> list = entry.getValue();
                List<Long> specValueIds = list.stream().map(SkuSpec::getSpecValueId).collect(Collectors.toList());
                memberShipVO.setId(specNameId);
                memberShipVO.setName(specNameId2Name.get(specNameId));
                memberShipVO.setMemberIds(specValueIds);
                memberShipVOList.add(memberShipVO);
            }
        }
        return memberShipVOList;
    }

    private List<SkuSpecSimpleVO> getSkuSpecSimpleVOList(List<SkuSpec> skuSpecList) {
        if(CollectionUtils.isEmpty(skuSpecList)) {
            return Lists.newArrayList();
        }
        List<SkuSpecSimpleVO> skuSpecSimpleVOList = skuSpecList.stream().map(x -> {
            SkuSpecSimpleVO skuSpecSimpleVO = new SkuSpecSimpleVO();
            BeanUtils.copyProperties(x, skuSpecSimpleVO);
            return skuSpecSimpleVO;
        }).collect(Collectors.toList());
        return skuSpecSimpleVOList;
    }


    @Override
    public Integer insertBatch(List<SkuSpec> list) {
        return baseMapper.insertBatch(list);
    }
}
