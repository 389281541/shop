package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.FlashPromotionSpuSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionSpuUpdateDTO;
import com.rainbow.admin.api.dto.FlashSpuSearchDTO;
import com.rainbow.admin.api.vo.FlashPromotionSpuVO;
import com.rainbow.admin.api.entity.FlashPromotionSpu;
import com.rainbow.admin.mapper.FlashPromotionSpuMapper;
import com.rainbow.admin.service.IFlashPromotionSpuService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.model.KV;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品限时购与商品关系表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@Service
public class FlashPromotionSpuServiceImpl extends ServiceImpl<FlashPromotionSpuMapper, FlashPromotionSpu> implements IFlashPromotionSpuService {

    @Override
    public Map<Long, Long> getCountMap(Long flashPromotionId) {
        List<KV<Long, Long>> kvList = baseMapper.getCountMap(flashPromotionId);
        Map<Long, Long> countMap = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(kvList)) {
            countMap = kvList.stream().collect(Collectors.toMap(KV::getK, KV::getV));
        }
        return countMap;
    }


    @Override
    public IPage<FlashPromotionSpuVO> pageFlashSpu(FlashSpuSearchDTO param) {
        IPage<FlashPromotionSpu> flashPromotionSpuIPage = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<FlashPromotionSpuVO> flashPromotionSpuVOIPage = baseMapper.pageFlashSku(flashPromotionSpuIPage, param.getFlashPromotionId(), param.getFlashPromotionSessionId());
        if(flashPromotionSpuIPage==null || CollectionUtils.isEmpty(flashPromotionSpuVOIPage.getRecords())) {
            IPage<FlashPromotionSpuVO> flashPromotionSpuVOPage = new Page<>();
            flashPromotionSpuVOPage.setCurrent(param.getPageNum());
            flashPromotionSpuVOPage.setSize(param.getPageSize());
            flashPromotionSpuVOPage.setRecords(Lists.newArrayList());
            flashPromotionSpuVOPage.setTotal(0L);
            return flashPromotionSpuVOPage;
        }
        return flashPromotionSpuVOIPage;
    }


    @Override
    public Boolean addBatch(List<FlashPromotionSpuSaveDTO> list) {
        if(CollectionUtils.isEmpty(list)) {
            throw new BusinessException(BaseErrorCode.PARAM_ERROR);
        }
        List<FlashPromotionSpu> flashPromotionSpuList = list.stream().map(x -> {
            FlashPromotionSpu flashPromotionSpu = new FlashPromotionSpu();
            BeanUtils.copyProperties(x, flashPromotionSpu);
            return flashPromotionSpu;
        }).collect(Collectors.toList());
        return saveBatch(flashPromotionSpuList);
    }


    @Override
    public Integer updateFlashPromotionSpu(FlashPromotionSpuUpdateDTO param) {

        FlashPromotionSpu flashPromotionSpu = new FlashPromotionSpu();
        BeanUtils.copyProperties(param, flashPromotionSpu);
        return  baseMapper.updateById(flashPromotionSpu);
    }


    @Override
    public Integer removeFlashPromotionSpu(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }


    @Override
    public FlashPromotionSpuVO getFlashPromotionSpuDetailById(IdDTO param) {
        FlashPromotionSpuVO flashPromotionSpuVO = new FlashPromotionSpuVO();
        FlashPromotionSpu flashPromotionSpu = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(flashPromotionSpu, flashPromotionSpuVO);
        return flashPromotionSpuVO;
    }
}
