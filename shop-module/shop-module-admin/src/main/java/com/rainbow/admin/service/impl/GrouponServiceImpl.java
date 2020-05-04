package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.mapper.GrouponMapper;
import com.rainbow.admin.service.IGrouponService;
import com.rainbow.api.entity.Groupon;
import org.springframework.stereotype.Service;

/**
 * 团购 service
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@Service
public class GrouponServiceImpl  extends ServiceImpl<GrouponMapper, Groupon> implements IGrouponService {
}
