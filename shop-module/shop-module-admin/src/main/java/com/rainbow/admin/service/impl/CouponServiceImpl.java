package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.CouponSaveDTO;
import com.rainbow.admin.api.dto.CouponSearchDTO;
import com.rainbow.admin.api.dto.CouponUpdateDTO;
import com.rainbow.admin.api.vo.CouponDetailVO;
import com.rainbow.admin.api.vo.CouponSimpleVO;
import com.rainbow.admin.api.entity.Coupon;
import com.rainbow.admin.api.entity.CouponItem;
import com.rainbow.admin.api.entity.CouponSpu;
import com.rainbow.admin.api.enums.ScopeTypeEnum;
import com.rainbow.admin.mapper.CouponMapper;
import com.rainbow.admin.service.ICouponItemService;
import com.rainbow.admin.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.service.ICouponSpuService;
import com.rainbow.common.dto.IdDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 优惠券表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

    @Autowired
    private ICouponSpuService couponSpuService;

    @Autowired
    private ICouponItemService couponItemService;

    @Override
    public IPage<CouponSimpleVO> pageCoupon(CouponSearchDTO param) {
        IPage<Coupon> couponIPage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Coupon> wrapper = new LambdaQueryWrapper<>();
        if (Strings.isNotBlank(param.getName())) {
            wrapper.like(Coupon::getName, param.getName());
        }
        if (param.getType() != null) {
            wrapper.eq(Coupon::getType, param.getType());
        }

        IPage<Coupon> couponPage = baseMapper.selectPage(couponIPage, wrapper);
        if (couponPage == null || CollectionUtils.isEmpty(couponPage.getRecords())) {
            IPage<CouponSimpleVO> couponSimpleVOIPage = new Page<>();
            couponSimpleVOIPage.setCurrent(param.getPageNum());
            couponSimpleVOIPage.setSize(param.getPageSize());
            couponSimpleVOIPage.setRecords(Lists.newArrayList());
            couponSimpleVOIPage.setTotal(0L);
            return couponSimpleVOIPage;
        }
        return couponPage.convert(x -> {
            CouponSimpleVO couponSimpleVO = new CouponSimpleVO();
            BeanUtils.copyProperties(x, couponSimpleVO);
            return couponSimpleVO;
        });
    }

    @Override
    public Integer addCoupon(CouponSaveDTO param) {
        param.setUseNum(0);
        param.setReceiveNum(0);
        //插入优惠券表
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(param, coupon);
        int count = baseMapper.insert(coupon);
        //插入优惠券和商品关系表
        if (param.getScopeType().equals(ScopeTypeEnum.DESIGNATED_GOODS.getValue())) {
            for (CouponSpu couponSpu : param.getCouponSpuList()) {
                couponSpu.setCouponId(coupon.getId());
            }
            couponSpuService.saveBatch(param.getCouponSpuList());
        }
        //插入优惠券和商品分类关系表
        if (param.getScopeType().equals(ScopeTypeEnum.DESIGNATED_ITEM.getValue())) {
            for (CouponItem couponItem : param.getCouponItemList()) {
                couponItem.setCouponId(coupon.getId());
            }
            couponItemService.saveBatch(param.getCouponItemList());
        }
        return count;
    }

    @Override
    public Integer removeCoupon(IdDTO param) {
        //删除优惠券
        Integer count = baseMapper.deleteById(param.getId());
        //删除优惠券商品关联
        couponSpuService.removeByCouponId(param.getId());
        //删除优惠券商品类别关联
        couponItemService.removeByCouponId(param.getId());
        return count;
    }

    @Override
    public Integer updateCoupon(CouponUpdateDTO param) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(param, coupon);
        int count = baseMapper.updateById(coupon);
        //删除后插入优惠券和商品关系表
        if (param.getScopeType().equals(ScopeTypeEnum.DESIGNATED_GOODS.getValue())) {
            for (CouponSpu couponSpu : param.getCouponSpuList()) {
                couponSpu.setCouponId(param.getId());
            }
            couponSpuService.removeByCouponId(param.getId());
            couponSpuService.saveBatch(param.getCouponSpuList());
        }
        //删除后插入优惠券和商品分类关系表
        if (param.getScopeType().equals(ScopeTypeEnum.DESIGNATED_ITEM.getValue())) {
            for (CouponItem couponItem : param.getCouponItemList()) {
                couponItem.setCouponId(param.getId());
            }
            couponItemService.removeByCouponId(param.getId());
            couponItemService.saveBatch(param.getCouponItemList());
        }
        return count;
    }


    @Override
    public CouponDetailVO getCouponDetailById(IdDTO param) {
        Long couponId = param.getId();
        CouponDetailVO couponDetailVO = new CouponDetailVO();
        Coupon coupon = getById(couponId);
        BeanUtils.copyProperties(coupon, couponDetailVO);
        List<CouponItem> couponItemList = couponItemService.listByCouponId(couponId);
        List<CouponSpu> couponSpuList = couponSpuService.listByCouponId(couponId);
        couponDetailVO.setCouponItemList(couponItemList);
        couponDetailVO.setCouponSpuList(couponSpuList);
        return couponDetailVO;
    }
}
