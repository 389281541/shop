package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.SpecNameSaveDTO;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.api.dto.SpecNameUpdateDTO;
import com.rainbow.admin.api.vo.SpecNameDetailVO;
import com.rainbow.admin.api.vo.SpecNameListVO;
import com.rainbow.admin.api.vo.SpecNameSimpleVO;
import com.rainbow.admin.entity.Item;
import com.rainbow.admin.entity.SpecName;
import com.rainbow.admin.entity.SpecValue;
import com.rainbow.admin.entity.SpuSpec;
import com.rainbow.admin.mapper.ItemMapper;
import com.rainbow.admin.mapper.SpecNameMapper;
import com.rainbow.admin.mapper.SpecValueMapper;
import com.rainbow.admin.mapper.SpuSpecMapper;
import com.rainbow.admin.service.ISpecNameService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.vo.IdNameVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.security.krb5.internal.PAData;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public SpecNameListVO listByItemId(IdDTO param) {
        SpecNameListVO specNameListVO = new SpecNameListVO();
        LambdaQueryWrapper<SpecName> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpecName::getItemId, param.getId());
        wrapper.eq(SpecName::getDelStatus, DelFlagEnum.NO.getValue());
        List<SpecName> specNameList = baseMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(specNameList)) {
            final Map<Long, List<IdNameVO>> id2vMap = getSpecValueMap(specNameList);
            List<SpecNameSimpleVO> skuSpecList = specNameList.stream().filter(x -> x.getType().equals(BooleanEnum.NO.getValue()) && x.getInput().equals(BooleanEnum.YES.getValue()) && x.getSku().equals(BooleanEnum.YES.getValue())).map(x -> {
                SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
                BeanUtils.copyProperties(x, specNameSimpleVO);
                specNameSimpleVO.setSpecValues(id2vMap.get(x.getId()) == null ? Lists.newArrayList() : id2vMap.get(x.getId()));
                return specNameSimpleVO;
            }).collect(Collectors.toList());
            List<SpecNameSimpleVO> spuSpecList = specNameList.stream().filter(x -> x.getType().equals(BooleanEnum.YES.getValue())).map(x -> {
                SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
                specNameSimpleVO.setSpecValues(id2vMap.get(x.getId()) == null ? Lists.newArrayList() : id2vMap.get(x.getId()));
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
            map = specValues.stream().map(x -> {
                IdNameVO idNameVO = new IdNameVO();
                idNameVO.setId(x.getSpecNameId());
                idNameVO.setName(x.getSpecValue());
                return idNameVO;
            }).collect(Collectors.groupingBy(IdNameVO::getId));
        }
        return map;
    }
}
