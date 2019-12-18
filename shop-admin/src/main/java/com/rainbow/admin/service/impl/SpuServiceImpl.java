package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.SpuSaveDTO;
import com.rainbow.admin.api.dto.SpuSearchDTO;
import com.rainbow.admin.api.dto.SpuUpdateDTO;
import com.rainbow.admin.api.vo.SpuDetailVO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.Spu;
import com.rainbow.admin.mapper.SpuMapper;
import com.rainbow.admin.service.ISpuService;
import com.rainbow.common.dto.IdDTO;
import org.springframework.beans.BeanUtils;
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
    public IPage<SpuSimpleVO> pageSpu(SpuSearchDTO param) {
        Page<Spu> spuPage = new Page<>(param.getPageNum(), param.getPageSize());
        return baseMapper.pageSpu(param, spuPage);
    }

    @Override
    public Integer addSpu(SpuSaveDTO param) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(param, spu);
        baseMapper.insert(spu);
        buildSkuRelation(spu, param);
        return null;
    }

    private void buildSkuRelation(Spu spu, SpuSaveDTO param) {

    }

    private void buildImgRelation(Spu spu, SpuSaveDTO param) {

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
