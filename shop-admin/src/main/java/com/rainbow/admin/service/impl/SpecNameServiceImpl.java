package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.SpecNameSaveDTO;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.api.dto.SpecNameUpdateDTO;
import com.rainbow.admin.api.vo.SpecNameDetailVO;
import com.rainbow.admin.api.vo.SpecNameSimpleVO;
import com.rainbow.admin.entity.SpecName;
import com.rainbow.admin.entity.SpecValue;
import com.rainbow.admin.entity.SpuSpec;
import com.rainbow.admin.mapper.SpecNameMapper;
import com.rainbow.admin.mapper.SpecValueMapper;
import com.rainbow.admin.mapper.SpuSpecMapper;
import com.rainbow.admin.service.ISpecNameService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public IPage<SpecNameSimpleVO> pageSpecName(SpecNameSearchDTO param) {
        IPage<SpecName> specNamePage = new Page<>(param.getPageNum(), param.getPageSize());
        List<SpecName> specNames = baseMapper.pageSpecName(param, specNamePage);
        specNamePage.setRecords(specNames);
        return specNamePage.convert(x->{
            SpecNameSimpleVO specNameSimpleVO = new SpecNameSimpleVO();
            BeanUtils.copyProperties(x,specNameSimpleVO);
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
        return baseMapper.updateById(specName);
    }

    @Override
    public SpecNameDetailVO getSpecNameDetailById(IdDTO param) {
        SpecName specName = baseMapper.selectById(param.getId());
        SpecNameDetailVO specNameDetailVO = new SpecNameDetailVO();
        BeanUtils.copyProperties(specName, specNameDetailVO);
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
        spuSpecWrapper.eq(SpuSpec::getDelStatus, DelFlagEnum.NO.getValue());
        spuSpecMapper.delete(spuSpecWrapper);
        //删除属性名表对应的行
        return baseMapper.deleteById(param.getId());
    }
}
