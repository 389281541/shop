package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.Brand;
import com.rainbow.admin.mapper.BrandMapper;
import com.rainbow.admin.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 品牌表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
