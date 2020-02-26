package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rainbow.admin.api.dto.SpecNameSaveDTO;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.api.dto.SpecNameUpdateDTO;
import com.rainbow.admin.api.dto.SpecSelectDTO;
import com.rainbow.admin.api.vo.SpecNameDetailVO;
import com.rainbow.admin.api.vo.SpecNameListVO;
import com.rainbow.admin.api.vo.SpecNameSimpleVO;
import com.rainbow.admin.entity.*;
import com.rainbow.admin.mapper.*;
import com.rainbow.admin.service.ISpecNameService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.vo.IdNameVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 规格名称表 服务实现类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Service
public class SpecNameServiceImpl extends ServiceImpl<SpecNameMapper, SpecName> implements ISpecNameService {

    @Resource
    private SpuSpecMapper spuSpecMapper;

    @Resource
    private SkuSpecMapper skuSpecMapper;

    @Resource
    private SpecValueMapper specValueMapper;

    @Resource
    private ItemMapper itemMapper;

    @Override
    public IPage<SpecNameSimpleVO> pageSpecName(SpecNameSearchDTO param) {
        IPage<SpecName> specNamePage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<SpecName> condition = new LambdaQueryWrapper<>();
        if (param.getId() != null) {
            condition.eq(SpecName::getItemId, param.getId());
        }
        if (param.getName() != null) {
            condition.like(SpecName::getName, param.getName());
        }
        if (param.getColor() != null) {
            condition.eq(SpecName::getColor, param.getColor());
        }
        if (param.getEnumeration() != null) {
            condition.eq(SpecName::getEnumeration, param.getEnumeration());
        }
        if (param.getInput() != null) {
            condition.eq(SpecName::getInput, param.getInput());
        }
        if (param.getCritical() != null) {
            condition.eq(SpecName::getCritical, param.getCritical());
        }
        if (param.getSku() != null) {
            condition.eq(SpecName::getSku, param.getSku());
        }
        if (param.getSearch() != null) {
            condition.eq(SpecName::getSearch, param.getSearch());
        }
        if (param.getMultiple() != null) {
            condition.eq(SpecName::getMultiple, param.getMultiple());
        }
        if (param.getType() != null) {
            condition.eq(SpecName::getType, param.getType());
        }
        condition.eq(SpecName::getDelStatus, DelFlagEnum.NO.getValue());
        IPage<SpecName> specNameIPage = page(specNamePage, condition);

        List<SpecName> specNameList = specNameIPage.getRecords();

        final Map<Long, List<IdNameVO>> id2vMap = getSpecValueMap(specNameList);
        return specNameIPage.convert(x -> {
            SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
            BeanUtils.copyProperties(x, specNameSimpleVO);
            specNameSimpleVO.setSpecValues(id2vMap.get(x.getId()) == null ? Lists.newArrayList() : id2vMap.get(x.getId()));
            return specNameSimpleVO;
        });
    }

    @Override
    public Integer saveSpecName(SpecNameSaveDTO param) {
        SpecName specName = new SpecName();
        BeanUtils.copyProperties(param, specName);
        return baseMapper.insert(specName);
    }

    @Override
    public Integer updateSpecName(SpecNameUpdateDTO param) {
        SpecName specName = new SpecName();
        BeanUtils.copyProperties(param, specName);
        specName.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(specName);
    }

    @Override
    public SpecNameDetailVO getSpecNameDetailById(IdDTO param) {
        SpecName specName = baseMapper.selectById(param.getId());
        SpecNameDetailVO specNameDetailVO = new SpecNameDetailVO();
        BeanUtils.copyProperties(specName, specNameDetailVO);
        Item item = itemMapper.selectById(specName.getItemId());
        specNameDetailVO.setParentItemId(item.getParentId());
        return specNameDetailVO;
    }

    @Override
    public Integer removeSpecName(IdDTO param) {
        //删除属性名对应的属性值
        LambdaQueryWrapper<SpecValue> specValueWrapper = new LambdaQueryWrapper<>();
        specValueWrapper.eq(SpecValue::getSpecNameId, param.getId());
        specValueWrapper.eq(SpecValue::getDelStatus, DelFlagEnum.NO.getValue());
        specValueMapper.delete(specValueWrapper);
        //删除属性名对应属性值
        LambdaQueryWrapper<SpuSpec> spuSpecWrapper = new LambdaQueryWrapper<>();
        spuSpecWrapper.eq(SpuSpec::getSpecNameId, param.getId());
        spuSpecMapper.delete(spuSpecWrapper);
        //删除属性名表对应的行
        return baseMapper.deleteById(param.getId());
    }


    @Override
    public SpecNameListVO listByItemId(SpecSelectDTO param) {
        SpecNameListVO specNameListVO = new SpecNameListVO();
        LambdaQueryWrapper<SpecName> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpecName::getItemId, param.getItemId());
        wrapper.eq(SpecName::getDelStatus, DelFlagEnum.NO.getValue());
        List<SpecName> specNameList = baseMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(specNameList)) {
            final Map<Long, List<IdNameVO>> id2vMap = getSpecValueMap(specNameList);
            final Map<Long, Set<String>> selectMap = getSelectSpecValueMap(param.getSpuId());
            List<SpecNameSimpleVO> skuSpecList = specNameList.stream().filter(x -> x.getType().equals(BooleanEnum.NO.getValue()) && x.getInput().equals(BooleanEnum.YES.getValue()) && x.getSku().equals(BooleanEnum.YES.getValue())).map(x -> {
                SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
                BeanUtils.copyProperties(x, specNameSimpleVO);
                specNameSimpleVO.setSpecValues(id2vMap.get(x.getId()) == null ? Lists.newArrayList() : id2vMap.get(x.getId()));
                specNameSimpleVO.setSelectSpecValues(selectMap.get(x.getId()) == null ? Sets.newHashSet() : selectMap.get(x.getId()));
                return specNameSimpleVO;
            }).collect(Collectors.toList());
            final Map<Long, List<String>> paramMap = getSelectParamMap(param.getSpuId());
            List<SpecNameSimpleVO> spuSpecList = specNameList.stream().filter(x -> x.getType().equals(BooleanEnum.YES.getValue())).map(x -> {
                SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
                BeanUtils.copyProperties(x, specNameSimpleVO);
                specNameSimpleVO.setSpecValues(id2vMap.get(x.getId()) == null ? Lists.newArrayList() : id2vMap.get(x.getId()));
                specNameSimpleVO.setSelectSpecValues(paramMap.get(x.getId()) == null ? Lists.newArrayList() : paramMap.get(x.getId()));
                return specNameSimpleVO;
            }).collect(Collectors.toList());
            specNameListVO.setSkuSpecList(skuSpecList);
            specNameListVO.setSpuSpecList(spuSpecList);
        }

        return specNameListVO;
    }

    private Map<Long, List<IdNameVO>> getSpecValueMap(List<SpecName> specNameList) {
        Map<Long, List<IdNameVO>> map = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(specNameList)) {
            Set<Long> ids = specNameList.stream().map(SpecName::getId).collect(Collectors.toSet());
            LambdaQueryWrapper<SpecValue> specValueWrapper = new LambdaQueryWrapper<>();
            specValueWrapper.in(SpecValue::getSpecNameId, ids);
            specValueWrapper.eq(SpecValue::getDelStatus, DelFlagEnum.NO.getValue());
            List<SpecValue> specValues = specValueMapper.selectList(specValueWrapper);
            Map<Long, List<SpecValue>> tempMap = specValues.stream().collect(Collectors.groupingBy(SpecValue::getSpecNameId));
            for (Map.Entry<Long, List<SpecValue>> entry : tempMap.entrySet()) {
                Long specNameId = entry.getKey();
                List<SpecValue> value = entry.getValue();
                List<IdNameVO> specValueList = value.stream().map(x -> {
                    IdNameVO idNameVO = new IdNameVO();
                    idNameVO.setId(x.getId());
                    idNameVO.setName(x.getSpecValue());
                    return idNameVO;
                }).collect(Collectors.toList());
                map.put(specNameId, specValueList);
            }
        }
        return map;
    }

    private Map<Long, Set<String>> getSelectSpecValueMap(Long spuId) {
        Map<Long, Set<String>> map = Maps.newHashMap();
        if (spuId == null) {
            return map;
        }
        List<SkuSpec> skuSpecList = skuSpecMapper.listBySpuId(spuId);
        if (!CollectionUtils.isEmpty(skuSpecList)) {
            Map<Long, List<SkuSpec>> membersMap = skuSpecList.stream().collect(Collectors.groupingBy(SkuSpec::getSpecNameId));
            Iterator<Map.Entry<Long, List<SkuSpec>>> it = membersMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, List<SkuSpec>> entry = it.next();
                List<SkuSpec> list = entry.getValue();
                Set<String> selectSpecValues = list.stream().map(SkuSpec::getSpecValue).collect(Collectors.toSet());
                map.put(entry.getKey(), selectSpecValues);
            }
        }
        return map;
    }

    private Map<Long, List<String>> getSelectParamMap(Long spuId) {
        Map<Long, List<String>> map = Maps.newHashMap();
        if (spuId == null) {
            return map;
        }

        List<SpuSpec> spuSpecList = spuSpecMapper.listBySpuId(spuId);
        if (!CollectionUtils.isEmpty(spuSpecList)) {
            Map<Long, String> selectValueMap = spuSpecList.stream().collect(Collectors.toMap(SpuSpec::getSpecNameId, SpuSpec::getSpecValue));
            Iterator<Map.Entry<Long, String>> it = selectValueMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, String> entry = it.next();
                String selectSpecValue = entry.getValue();
                List<String> list = new ArrayList<>();
                list.add(selectSpecValue);
                map.put(entry.getKey(), list);
            }
        }
        return map;
    }
}
