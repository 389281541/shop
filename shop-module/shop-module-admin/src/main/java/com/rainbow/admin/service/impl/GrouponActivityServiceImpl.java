package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.mapper.GrouponActivityMapper;
import com.rainbow.admin.mapper.GrouponOrderMapper;
import com.rainbow.admin.service.IGrouponActivityService;
import com.rainbow.api.dto.GrouponActivitySaveDTO;
import com.rainbow.api.dto.GrouponActivitySearchDTO;
import com.rainbow.api.dto.GrouponActivityUpdateDTO;
import com.rainbow.api.entity.GrouponActivity;
import com.rainbow.api.vo.GrouponActivityDetailVO;
import com.rainbow.api.vo.GrouponActivitySimpleVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.model.KV;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class GrouponActivityServiceImpl extends ServiceImpl<GrouponActivityMapper, GrouponActivity> implements IGrouponActivityService {

    @Resource
    private GrouponOrderMapper grouponOrderMapper;

    @Override
    public IPage<GrouponActivitySimpleVO> pageGrouponActivity(GrouponActivitySearchDTO param) {
        IPage<GrouponActivity> grouponActivityIPage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<GrouponActivity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrouponActivity::getDelStatus, DelFlagEnum.NO.getValue());
        if (Strings.isNotBlank(param.getKeyword())) {
            wrapper.like(GrouponActivity::getName, param.getKeyword());
        }

        IPage<GrouponActivity> grouponActivityPage = baseMapper.selectPage(grouponActivityIPage, wrapper);
        if (grouponActivityPage == null || CollectionUtils.isEmpty(grouponActivityPage.getRecords())) {
            IPage<GrouponActivitySimpleVO> grouponActivityPageInfo = new Page<>();
            grouponActivityPageInfo.setCurrent(param.getPageNum());
            grouponActivityPageInfo.setSize(param.getPageSize());
            grouponActivityPageInfo.setRecords(Lists.newArrayList());
            grouponActivityPageInfo.setTotal(0L);
            return grouponActivityPageInfo;
        }
        List<GrouponActivity> records = grouponActivityPage.getRecords();
        List<Long> activityIds = records.stream().map(GrouponActivity::getId).collect(Collectors.toList());
        List<KV<Long, Long>> joinMembersCountList = grouponOrderMapper.getJoinMembersCount(activityIds);
        Map<Long, Long> activityJoinMap = Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(joinMembersCountList)) {
            activityJoinMap = joinMembersCountList.stream().collect(Collectors.toMap(KV::getK, KV::getV));
        }
        final Map<Long, Long> laambdaActivityJoinMap = activityJoinMap;
        return grouponActivityPage.convert(x -> {
            GrouponActivitySimpleVO grouponActivitySimpleVO = new GrouponActivitySimpleVO();
            BeanUtils.copyProperties(x, grouponActivitySimpleVO);
            grouponActivitySimpleVO.setJoinMembersCount(laambdaActivityJoinMap.getOrDefault(x.getId(), 0L));
            return grouponActivitySimpleVO;
        });
    }

    @Override
    public Integer addGrouponActivity(GrouponActivitySaveDTO param) {
        GrouponActivity grouponActivity = new GrouponActivity();
        BeanUtils.copyProperties(param, grouponActivity);
        return baseMapper.insert(grouponActivity);
    }

    @Override
    public Integer removeGrouponActivity(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }

    @Override
    public Integer updateGrouponActivity(GrouponActivityUpdateDTO param) {
        GrouponActivity grouponActivity = new GrouponActivity();
        BeanUtils.copyProperties(param, grouponActivity);
        return baseMapper.updateById(grouponActivity);
    }

    @Override
    public GrouponActivityDetailVO getGrouponActivityDetailById(IdDTO param) {
        return null;
    }
}
