package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.FlashPromotionSessionSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionSessionUpdateDTO;
import com.rainbow.admin.api.vo.FlashPromotionSessionVO;
import com.rainbow.admin.api.entity.FlashPromotionSession;
import com.rainbow.admin.mapper.FlashPromotionSessionMapper;
import com.rainbow.admin.service.IFlashPromotionSessionService;
import com.rainbow.admin.service.IFlashPromotionSpuService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.StatusChangeDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 限时购场次表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@Service
public class FlashPromotionSessionServiceImpl extends ServiceImpl<FlashPromotionSessionMapper, FlashPromotionSession> implements IFlashPromotionSessionService {

    @Autowired
    private IFlashPromotionSpuService flashPromotionSpuService;

    @Override
    public List<FlashPromotionSessionVO> listFlashPromotionSession() {
        LambdaQueryWrapper<FlashPromotionSession> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FlashPromotionSession::getDelStatus, DelFlagEnum.NO.getValue());
        List<FlashPromotionSession> flashPromotionSessionList = baseMapper.selectList(wrapper);
        List<FlashPromotionSessionVO> flashPromotionSessionVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(flashPromotionSessionList)) {
            flashPromotionSessionVOList = flashPromotionSessionList.stream().map(x -> {
                FlashPromotionSessionVO flashPromotionSessionVO = new FlashPromotionSessionVO();
                BeanUtils.copyProperties(x, flashPromotionSessionVO);
                return flashPromotionSessionVO;
            }).collect(Collectors.toList());
        }
        return flashPromotionSessionVOList;
    }


    @Override
    public List<FlashPromotionSessionVO> listAvailable(IdDTO param) {
        List<FlashPromotionSessionVO> flashPromotionSessionVOList = Lists.newArrayList();
        LambdaQueryWrapper<FlashPromotionSession> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FlashPromotionSession::getDelStatus, DelFlagEnum.NO.getValue());
        wrapper.eq(FlashPromotionSession::getStatus, BooleanEnum.YES.getValue());
        List<FlashPromotionSession> flashPromotionSessionList = baseMapper.selectList(wrapper);
        if (flashPromotionSessionList == null) {
            return flashPromotionSessionVOList;
        }
        Map<Long, Long> countMap = flashPromotionSpuService.getCountMap(param.getId());

        flashPromotionSessionVOList = flashPromotionSessionList.stream().map(x -> {
            FlashPromotionSessionVO flashPromotionSessionVO = new FlashPromotionSessionVO();
            BeanUtils.copyProperties(x, flashPromotionSessionVO);
            flashPromotionSessionVO.setCount(countMap.get(x.getId()) == null ? 0L : countMap.get(x.getId()));
            return flashPromotionSessionVO;
        }).collect(Collectors.toList());
        return flashPromotionSessionVOList;
    }

    @Override
    public Integer addFlashPromotionSession(FlashPromotionSessionSaveDTO param) {
        FlashPromotionSession flashPromotionSession = new FlashPromotionSession();
        BeanUtils.copyProperties(param, flashPromotionSession);
        flashPromotionSession.setCreateTime(LocalDateTime.now());
        return baseMapper.insert(flashPromotionSession);
    }

    @Override
    public Integer removeFlashPromotionSession(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }

    @Override
    public Integer updateFlashPromotionSession(FlashPromotionSessionUpdateDTO param) {
        FlashPromotionSession flashPromotionSession = new FlashPromotionSession();
        BeanUtils.copyProperties(param, flashPromotionSession);
        flashPromotionSession.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(flashPromotionSession);
    }

    @Override
    public FlashPromotionSessionVO getFlashPromotionSessionDetailById(IdDTO param) {
        FlashPromotionSessionVO flashPromotionSessionVO = new FlashPromotionSessionVO();
        FlashPromotionSession flashPromotionSession = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(flashPromotionSession, flashPromotionSessionVO);
        return flashPromotionSessionVO;
    }


    @Override
    public Integer updateStatus(StatusChangeDTO param) {
        FlashPromotionSession flashPromotionSession = new FlashPromotionSession();
        flashPromotionSession.setId(param.getId());
        flashPromotionSession.setStatus(param.getStatus());
        return baseMapper.updateById(flashPromotionSession);
    }
}
