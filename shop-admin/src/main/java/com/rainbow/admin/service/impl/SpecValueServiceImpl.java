package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpecValueSaveDTO;
import com.rainbow.admin.api.dto.SpecValueUpdateDTO;
import com.rainbow.admin.api.vo.SpecValueDetailVO;
import com.rainbow.admin.api.vo.SpecValueSimpleVO;
import com.rainbow.admin.entity.SpecValue;
import com.rainbow.admin.mapper.SpecValueMapper;
import com.rainbow.admin.service.ISpecValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import org.springframework.stereotype.Service;

/**
 * 规格值表 服务实现类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Service
public class SpecValueServiceImpl extends ServiceImpl<SpecValueMapper, SpecValue> implements ISpecValueService {


    @Override
    public IPage<SpecValueSimpleVO> pageSpecValue(IdPageDTO param) {
        return null;
    }

    @Override
    public Integer saveSpecValue(SpecValueSaveDTO param) {
        return null;
    }

    @Override
    public Integer updateSpecValue(SpecValueUpdateDTO param) {
        return null;
    }

    @Override
    public SpecValueDetailVO getSpecValueDetailById(IdDTO param) {
        return null;
    }

    @Override
    public Integer removeSpecValue(IdDTO param) {
        return null;
    }
}
