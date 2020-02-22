package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.FlashPromotionSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionUpdateDTO;
import com.rainbow.admin.api.vo.FlashPromotionVO;
import com.rainbow.admin.entity.FlashPromotion;
import com.rainbow.admin.mapper.FlashPromotionMapper;
import com.rainbow.admin.service.IFlashPromotionService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.SearchPageDTO;
import com.rainbow.common.dto.StatusChangeDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

/**
 * 秒杀促销 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@Service
public class FlashPromotionServiceImpl extends ServiceImpl<FlashPromotionMapper, FlashPromotion> implements IFlashPromotionService {


    @Override
    public IPage<FlashPromotionVO> pageFlashPromotion(SearchPageDTO param) {
        IPage<FlashPromotion> flashPromotionPage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<FlashPromotion> wrapper = new LambdaQueryWrapper<>();
        if(Strings.isNotBlank(param.getKeyword())) {
            wrapper.like(FlashPromotion::getTheme, param.getKeyword());
        }
        IPage<FlashPromotion> flashPromotionIPage = baseMapper.selectPage(flashPromotionPage, wrapper);
        if(flashPromotionIPage == null || CollectionUtils.isEmpty(flashPromotionIPage.getRecords())) {
            IPage<FlashPromotionVO> flashSimpleVOIPage = new Page<>();
            flashSimpleVOIPage.setCurrent(param.getPageNum());
            flashSimpleVOIPage.setSize(param.getPageSize());
            flashSimpleVOIPage.setRecords(Lists.newArrayList());
            flashSimpleVOIPage.setTotal(0L);
            return flashSimpleVOIPage;
        }
        return flashPromotionIPage.convert(x->{
            FlashPromotionVO flashPromotionVO = new FlashPromotionVO();
            BeanUtils.copyProperties(x, flashPromotionVO);
            return flashPromotionVO;
        });
    }


    @Override
    public Integer addFlashPromotion(FlashPromotionSaveDTO param) {
        FlashPromotion flashPromotion = new FlashPromotion();
        BeanUtils.copyProperties(param, flashPromotion);
        flashPromotion.setCreateTime(LocalDateTime.now());
        return baseMapper.insert(flashPromotion);
    }

    @Override
    public Integer removeFlashPromotion(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }

    @Override
    public Integer updateFlashPromotion(FlashPromotionUpdateDTO param) {
        FlashPromotion flashPromotion = new FlashPromotion();
        BeanUtils.copyProperties(param, flashPromotion);
        flashPromotion.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(flashPromotion);
    }

    @Override
    public FlashPromotionVO getFlashPromotionDetailById(IdDTO param) {
        FlashPromotionVO flashPromotionVO = new FlashPromotionVO();
        FlashPromotion flashPromotion = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(flashPromotion, flashPromotionVO);
        return flashPromotionVO;
    }

    @Override
    public Integer updateStatus(StatusChangeDTO param) {
        FlashPromotion flashPromotion = new FlashPromotion();
        flashPromotion.setStatus(param.getStatus());
        flashPromotion.setId(param.getId());
        return baseMapper.updateById(flashPromotion);
    }
}
