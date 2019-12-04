package com.rainbow.admin.service.impl;

import com.rainbow.admin.entity.Shop;
import com.rainbow.admin.mapper.ShopMapper;
import com.rainbow.admin.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 店铺表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

}
