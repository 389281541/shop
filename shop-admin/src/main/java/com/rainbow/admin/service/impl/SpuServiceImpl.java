package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpuSaveDTO;
import com.rainbow.admin.api.dto.SpuUpdateDTO;
import com.rainbow.admin.api.vo.SpuDetailVO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.Spu;
import com.rainbow.admin.mapper.SpuMapper;
import com.rainbow.admin.service.ISpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import org.springframework.stereotype.Service;

/**
 * 商品表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Override
    public IPage<SpuSimpleVO> pageSpuByItem(IdNamePageDTO param) {
        return null;
    }

    @Override
    public Integer addSpu(SpuSaveDTO param) {
        return null;
    }

    @Override
    public Integer removeSpu(IdDTO param) {
        return null;
    }

    @Override
    public Boolean updateSpu(SpuUpdateDTO param) {
        return null;
    }

    @Override
    public SpuDetailVO getSpuDetailById(IdDTO param) {
        return null;
    }
}