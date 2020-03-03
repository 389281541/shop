package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.api.entity.HomeAdvertise;
import com.rainbow.api.entity.Item;
import com.rainbow.api.enums.HomeAdvertiseTypeEnum;
import com.rainbow.api.vo.AdvertiseVO;
import com.rainbow.api.vo.HomeAdvertiseVO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.vo.FatherChildrenVO;
import com.rainbow.common.vo.IdNameVO;
import com.rainbow.portal.mapper.HomeAdvertiseMapper;
import com.rainbow.portal.mapper.ItemMapper;
import com.rainbow.portal.service.INavgationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 导航service
 */
@Service
@Slf4j
public class NavgationServiceImpl implements INavgationService {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private HomeAdvertiseMapper homeAdvertiseMapper;

    @Override
    public List<FatherChildrenVO> listItem() {
        LambdaQueryWrapper<Item> condition = new LambdaQueryWrapper<>();
        condition.eq(Item::getParent, BooleanEnum.YES.getValue());
        condition.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        List<Item> parentItemList = itemMapper.selectList(condition);
        Map<Long, List<IdNameVO>> groupMap = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(parentItemList)) {
            List<Long> parentIds = parentItemList.stream().map(Item::getId).collect(Collectors.toList());
            LambdaQueryWrapper<Item> itemWrapper = new LambdaQueryWrapper<>();
            itemWrapper.in(Item::getParentId, parentIds);
            itemWrapper.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
            List<Item> itemList = itemMapper.selectList(itemWrapper);
            groupMap = itemList.stream().collect(Collectors.toMap(Item::getParentId, x->{
                IdNameVO idNameVO = new IdNameVO();
                idNameVO.setId(x.getId());
                idNameVO.setName(x.getName());
                List<IdNameVO> idNameVOList = Lists.newArrayList();
                idNameVOList.add(idNameVO);
                return idNameVOList;
            }, (List<IdNameVO> newList, List<IdNameVO> oldList) -> {
                newList.addAll(oldList);
                return newList;
            }));

        }
        final  Map<Long, List<IdNameVO>> childrenMap = groupMap;
        List<FatherChildrenVO> fatherChildrenVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(parentItemList)) {
            fatherChildrenVOList = parentItemList.stream().map(x -> {
                FatherChildrenVO itemVO = new FatherChildrenVO();
                itemVO.setId(x.getId());
                itemVO.setName(x.getName());
                itemVO.setChildren(childrenMap.get(x.getId()));
                return itemVO;
            }).collect(Collectors.toList());
        }
        return fatherChildrenVOList;
    }


    @Override
    public AdvertiseVO listAdvertise() {
        AdvertiseVO advertiseVO = new AdvertiseVO();
        LambdaQueryWrapper<HomeAdvertise> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HomeAdvertise::getStatus, BooleanEnum.YES.getValue());
        wrapper.orderByAsc(HomeAdvertise::getSortId);
        List<HomeAdvertise> homeAdvertises = homeAdvertiseMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(homeAdvertises)) {
            advertiseVO.setActivityList(Lists.newArrayList());
            advertiseVO.setRotateChartList(Lists.newArrayList());
            return advertiseVO;
        }

        Map<Integer, List<HomeAdvertiseVO>> homeAdvertiseMap = homeAdvertises.stream().map(x -> {
            HomeAdvertiseVO homeAdvertiseVO = new HomeAdvertiseVO();
            BeanUtils.copyProperties(x, homeAdvertiseVO);
            return homeAdvertiseVO;
        }).collect(Collectors.groupingBy(HomeAdvertiseVO::getType));
        advertiseVO.setRotateChartList(homeAdvertiseMap.get(HomeAdvertiseTypeEnum.ROTATE_CHART.getValue()));
        advertiseVO.setActivityList(homeAdvertiseMap.get(HomeAdvertiseTypeEnum.ACTIVITY.getValue()));
        return advertiseVO;
    }
}
