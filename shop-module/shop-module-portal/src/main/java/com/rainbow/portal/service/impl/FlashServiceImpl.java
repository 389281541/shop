package com.rainbow.portal.service.impl;

import com.google.common.collect.Lists;
import com.rainbow.api.entity.*;
import com.rainbow.api.enums.FlashProcessStatusEnum;
import com.rainbow.api.vo.FlashGoodsSimpleVO;
import com.rainbow.api.vo.FlashThemeVO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.model.HMS;
import com.rainbow.common.util.DateUtils;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.service.IFlashService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 秒杀服务service
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Service
@Slf4j
public class FlashServiceImpl implements IFlashService {

    private static final Integer FLASH_PERTHEME_GOODS_SIZE = 5;

    @Resource
    private FlashPromotionMapper flashPromotionMapper;

    @Resource
    private FlashPromotionSessionMapper flashPromotionSessionMapper;

    @Resource
    private FlashPromotionSpuMapper flashPromotionSpuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuImgMapper spuImgMapper;

    @Resource
    private SkuMapper skuMapper;

    @Override
    public List<FlashThemeVO> listFlashThemes() {
        //1、获取所有上线的秒杀活动
        List<FlashPromotion> flashPromotionList = flashPromotionMapper.listByStatus(BooleanEnum.YES.getValue());
        if (CollectionUtils.isEmpty(flashPromotionList)) {
            return Lists.newArrayList();
        }
        //2、获取秒杀状态和秒杀倒计时
        //判断当前时间是否在秒杀时间内 如果在秒杀时间内
        List<FlashPromotionSession> flashPromotionSessionList = flashPromotionSessionMapper.listFlashSession();
        if (CollectionUtils.isEmpty(flashPromotionSessionList)) {
            return Lists.newArrayList();
        }
        FlashProcessStatusEnum flashProcessStatusEnum = FlashProcessStatusEnum.WAIT_TO_FLASH;
        HMS hms = new HMS();
        LocalTime now = LocalTime.now();
        FlashPromotionSession currentFlashPromotionSession = null;
        for (FlashPromotionSession flashPromotionSession : flashPromotionSessionList) {
            LocalTime startTime = flashPromotionSession.getStartTime();
            LocalTime endTime = flashPromotionSession.getEndTime();
            if (now.isBefore(endTime) && now.isAfter(startTime)) {
                currentFlashPromotionSession = flashPromotionSession;
                Duration duration = Duration.between(now, endTime);
                hms = DateUtils.convertMills2HMS(duration.toMillis());
                flashProcessStatusEnum = FlashProcessStatusEnum.FLASHING;
                break;
            }
        }
        // 如果当前时间不是秒杀时间 获取秒杀倒计时
        if(flashProcessStatusEnum.equals(FlashProcessStatusEnum.WAIT_TO_FLASH)) {
            FlashPromotionSession recentSession = flashPromotionSessionMapper.getRecentSession(LocalTime.now(), LocalTime.of(23,59,59));
            if(recentSession == null) {
                return Lists.newArrayList();
            }
            LocalTime startTime = recentSession.getStartTime();
            Duration duration = Duration.between(now, startTime);
            hms = DateUtils.convertMills2HMS(duration.toMillis());
            currentFlashPromotionSession = recentSession;
        }
        //3、获取各个上线状态的秒杀商品列表
        List<Long> flashPromotionIds = flashPromotionList.stream().map(FlashPromotion::getId).collect(Collectors.toList());
        List<FlashPromotionSpu> flashPromotionSpuList = flashPromotionSpuMapper.listBySessionId(currentFlashPromotionSession.getId(), flashPromotionIds);
        if(CollectionUtils.isEmpty(flashPromotionSpuList)) {
            return Lists.newArrayList();
        }
        if(flashPromotionSpuList.size() > FLASH_PERTHEME_GOODS_SIZE) {
            flashPromotionSpuList.subList(0, FLASH_PERTHEME_GOODS_SIZE);
        }
        Set<Long> spuIds = flashPromotionSpuList.stream().map(FlashPromotionSpu::getSpuId).collect(Collectors.toSet());
        List<Spu> spuList = spuMapper.listBySpuIds(spuIds);
        final Map<Long, String> spuNameMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Spu::getName));
        List<SpuImg> spuImgList = spuImgMapper.listCoversBySpuIds(spuIds);
        final Map<Long, String> spuCoverMap = spuImgList.stream().collect(Collectors.toMap(SpuImg::getSpuId, SpuImg::getImgUrl));
        final Map<Long, List<FlashGoodsSimpleVO>> flashPromotionMap = flashPromotionSpuList.stream().map(x->{
            FlashGoodsSimpleVO flashGoodsSimpleVO = new FlashGoodsSimpleVO();
            flashGoodsSimpleVO.setId(x.getSpuId());
            flashGoodsSimpleVO.setFlashPromotionId(x.getFlashPromotionId());
            String spuName = spuNameMap.get(x.getSpuId());
            if(spuName.length() > 30) {
                spuName = spuName.substring(0, 30) + "...";
            }
            flashGoodsSimpleVO.setName(spuName);
            flashGoodsSimpleVO.setCoverImg(spuCoverMap.get(x.getSpuId()));
            Sku sku = skuMapper.getMinPriceSkuBySpuId(x.getSpuId());
            flashGoodsSimpleVO.setFlashPrice(sku.getPrice());
            flashGoodsSimpleVO.setOriginalPrice(sku.getOriginalPrice());
            return flashGoodsSimpleVO;
        }).collect(Collectors.groupingBy(FlashGoodsSimpleVO::getFlashPromotionId));
        //4、返回秒杀活动列表
        final FlashProcessStatusEnum lambdaFlashProcessStatusEnum = flashProcessStatusEnum;
        final HMS lambdaHms = hms;
        List<FlashThemeVO> flashThemeVOList = flashPromotionList.stream().map(x -> {
            FlashThemeVO flashThemeVO = new FlashThemeVO();
            flashThemeVO.setTheme(x.getTheme());
            flashThemeVO.setHms(lambdaHms);
            flashThemeVO.setFlashGoodsList(flashPromotionMap.get(x.getId()));
            flashThemeVO.setFlashStatus(lambdaFlashProcessStatusEnum.getValue());
            return flashThemeVO;
        }).collect(Collectors.toList());
        return flashThemeVOList;
    }
}
