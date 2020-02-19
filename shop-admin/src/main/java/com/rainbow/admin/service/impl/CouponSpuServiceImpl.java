package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.entity.CouponItem;
import com.rainbow.admin.entity.CouponSpu;
import com.rainbow.admin.mapper.CouponSpuMapper;
import com.rainbow.admin.service.ICouponSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券和商品的关系表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Service
public class CouponSpuServiceImpl extends ServiceImpl<CouponSpuMapper, CouponSpu> implements ICouponSpuService {


    @Override
    public Integer removeByCouponId(Long couponId) {
        return baseMapper.removeByCouponId(couponId);
    }

    @Override
    public List<CouponSpu> listByCouponId(Long couponId) {
        LambdaQueryWrapper<CouponSpu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CouponSpu::getCouponId, couponId);
        return baseMapper.selectList(wrapper);
    }
}
