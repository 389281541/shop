package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.api.entity.GrouponOrder;
import com.rainbow.common.model.KV;

import java.util.Collection;
import java.util.List;

/**
 * 团购订单表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-04
 */
@DS("goods")
public interface GrouponOrderMapper extends BaseMapper<GrouponOrder> {

    /**
     * 获取每个活动参与人的人数
     * @return
     */
    List<KV<Long, Long>> getJoinMembersCount(Collection<Long> activityIds);

}
