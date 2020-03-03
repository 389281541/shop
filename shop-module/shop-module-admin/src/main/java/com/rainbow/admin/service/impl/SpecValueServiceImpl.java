package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.api.dto.SpecValueSaveDTO;
import com.rainbow.api.dto.SpecValueUpdateDTO;
import com.rainbow.api.dto.UpDownRankingDTO;
import com.rainbow.api.vo.SpecValueDetailVO;
import com.rainbow.api.vo.SpecValuePageVO;
import com.rainbow.api.vo.SpecValueSimpleVO;
import com.rainbow.api.entity.SpecName;
import com.rainbow.api.entity.SpecValue;
import com.rainbow.admin.mapper.SpecNameMapper;
import com.rainbow.admin.mapper.SpecValueMapper;
import com.rainbow.admin.service.ISpecValueService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.TwoTuple;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 规格值表 服务实现类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Service
public class SpecValueServiceImpl extends ServiceImpl<SpecValueMapper, SpecValue> implements ISpecValueService {

    @Resource
    private SpecNameMapper specNameMapper;

    @Override
    public SpecValuePageVO pageSpecValue(IdPageDTO param) {
        SpecValuePageVO specValuePageVO = new SpecValuePageVO();
        Page<SpecValue> specValuePage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<SpecValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpecValue::getSpecNameId, param.getId());
        wrapper.eq(SpecValue::getDelStatus, DelFlagEnum.NO.getValue());
        wrapper.orderByAsc(SpecValue::getSortId);
        IPage<SpecValue> specValueList = page(specValuePage, wrapper);
        //分页数据
        IPage<SpecValueSimpleVO> simpleVOIPage = specValueList.convert(x -> {
            SpecValueSimpleVO specValueSimpleVO = new SpecValueSimpleVO();
            specValueSimpleVO.setId(x.getId());
            specValueSimpleVO.setName(x.getSpecValue());
            specValueSimpleVO.setSortId(x.getSortId());
            specValueSimpleVO.setCreateTime(x.getCreateTime());
            return specValueSimpleVO;
        });
        specValuePageVO.setSpecValuePage(simpleVOIPage);
        SpecName specName = specNameMapper.selectById(param.getId());
        specValuePageVO.setSpecName(specName.getName());
        return specValuePageVO;
    }

    @Override
    public Integer saveSpecValue(SpecValueSaveDTO param) {
        SpecValue specValue = new SpecValue();
        specValue.setSpecNameId(param.getSpecNameId());
        specValue.setSpecValue(param.getSpecValue());
        specValue.setSortId(param.getSortId());
        specValue.setCreateTime(LocalDateTime.now());
        specValue.setDelStatus(DelFlagEnum.NO.getValue());
        return baseMapper.insert(specValue);
    }

    @Override
    public Integer updateSpecValue(SpecValueUpdateDTO param) {
        SpecValue specValue = new SpecValue();
        specValue.setId(param.getId());
        specValue.setSpecNameId(param.getSpecNameId());
        specValue.setSpecValue(param.getSpecValue());
        specValue.setSortId(param.getSortId());
        specValue.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(specValue);
    }

    @Override
    public SpecValueDetailVO getSpecValueDetailById(IdDTO param) {
        SpecValueDetailVO specValueDetailVO = new SpecValueDetailVO();
        SpecValue specValue = baseMapper.selectById(param.getId());
        specValueDetailVO.setId(specValue.getId());
        specValueDetailVO.setName(specValue.getSpecValue());
        Long specNameId = specValue.getSpecNameId();
        specValueDetailVO.setSpecNameId(specNameId);
        specValueDetailVO.setSortId(specValue.getSortId());
        SpecName specName = specNameMapper.selectById(specNameId);
        specValueDetailVO.setSpecName(specName.getName());
        return specValueDetailVO;
    }

    @Override
    public Integer removeSpecValue(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }

    @Override
    public Integer upDownRanking(UpDownRankingDTO param) {
        SpecValue specValue = baseMapper.selectById(param.getSpecValueId());
        LambdaQueryWrapper<SpecValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpecValue::getSpecNameId, param.getId());
        wrapper.eq(SpecValue::getDelStatus, DelFlagEnum.NO.getValue());
        wrapper.orderByAsc(SpecValue::getSortId);
        List<SpecValue> specValueList = baseMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(specValueList)) {
            throw new BusinessException(BaseErrorCode.PARAM_NOT_REACH);
        }
        int index = specValueList.indexOf(specValue);
        if (index == -1) {
            throw new BusinessException(BaseErrorCode.NOT_FOUND);
        }
        if (param.getType().equals(BooleanEnum.NO.getValue())) {
            //上移
            if (index == 0) {
                throw new BusinessException(BaseErrorCode.PARAM_NOT_REACH);
            }
            SpecValue lastSpecValue = specValueList.get(index - 1);
            return exchange(specValue, lastSpecValue);
        } else {
            //下移
            if (index == specValueList.size() - 1) {
                throw new BusinessException(BaseErrorCode.PARAM_NOT_REACH);
            }
            SpecValue nextSpecValue = specValueList.get(index + 1);
            return exchange(specValue, nextSpecValue);
        }

    }


    private Integer exchange(SpecValue specValue, SpecValue exchangeSpecValue) {
        List<TwoTuple<Long, Long>> list = Lists.newArrayList();
        list.add(new TwoTuple<>(specValue.getId(), exchangeSpecValue.getSortId()));
        list.add(new TwoTuple<>(exchangeSpecValue.getId(), specValue.getSortId()));
        return baseMapper.updateSortIdBatch(list);
    }
}
