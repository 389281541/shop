package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.BrandItem;
import com.rainbow.admin.mapper.BrandItemMapper;
import com.rainbow.admin.service.IBrandItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 品牌种类关联表 服务实现类
 *
 * @author lujinwei
 * @since 2019-09-22
 */
@Service
public class BrandItemServiceImpl extends ServiceImpl<BrandItemMapper, BrandItem> implements IBrandItemService {

}
