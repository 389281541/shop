package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.api.entity.CouponItem;
import com.rainbow.admin.mapper.CouponItemMapper;
import com.rainbow.admin.service.ICouponItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券和商品分类关系表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Service
public class CouponItemServiceImpl extends ServiceImpl<CouponItemMapper, CouponItem> implements ICouponItemService {

    @Override
    public Integer removeByCouponId(Long couponId) {
        return baseMapper.removeByCouponId(couponId);
    }

    @Override
    public List<CouponItem> listByCouponId(Long couponId) {
        LambdaQueryWrapper<CouponItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CouponItem::getCouponId, couponId);
        return baseMapper.selectList(wrapper);
    }
}
