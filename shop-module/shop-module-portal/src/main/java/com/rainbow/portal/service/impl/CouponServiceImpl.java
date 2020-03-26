package com.rainbow.portal.service.impl;

import com.google.common.collect.Lists;
import com.rainbow.api.entity.Coupon;
import com.rainbow.api.entity.CouponItem;
import com.rainbow.api.entity.CouponSpu;
import com.rainbow.api.enums.CouponScopeTypeEnum;
import com.rainbow.api.enums.CouponUseConditionTypeEnum;
import com.rainbow.api.vo.CartPromotionVO;
import com.rainbow.api.vo.CouponCustomerDetailVO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.portal.mapper.CouponCustomerMapper;
import com.rainbow.portal.service.ICouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 优惠券 servie实现
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Service
@Slf4j
public class CouponServiceImpl implements ICouponService {

    @Resource
    private CouponCustomerMapper couponCustomerMapper;

    @Override
    public List<CouponCustomerDetailVO> listOrderAvailable(Long customerId, List<CartPromotionVO> cartList, Integer type) {
        List<CouponCustomerDetailVO> availableList = Lists.newArrayList();
        List<CouponCustomerDetailVO> unAvailableList = Lists.newArrayList();
        //获取用户所有service
        List<CouponCustomerDetailVO> couponCustomerDetailVOList = couponCustomerMapper.listDetailByCustomerId(customerId);
        if (CollectionUtils.isEmpty(couponCustomerDetailVOList)) {
            return Lists.newArrayList();
        }
        for (CouponCustomerDetailVO couponCustomerDetailVO : couponCustomerDetailVOList) {
            Coupon coupon = couponCustomerDetailVO.getCoupon();
            Integer scopeType = coupon.getScopeType();
            Integer useConditionType = coupon.getUseConditionType();
            LocalDateTime endEffectTime = coupon.getEndEffectTime();
            Integer useConditionLimit = coupon.getUseConditionLimit();
            if (Objects.equals(scopeType, CouponScopeTypeEnum.ALL_SCENE.getValue())) {
                //所有场景
                if (Objects.equals(useConditionType, CouponUseConditionTypeEnum.NO_THRESHOLD.getValue())) {
                    availableList.add(couponCustomerDetailVO);
                } else if (Objects.equals(useConditionType, CouponUseConditionTypeEnum.MINIMUM_AMOUNT.getValue())) {
                    BigDecimal totalAmount = calculateTotalAmount(cartList);
                    if (LocalDateTime.now().isBefore(endEffectTime) && (totalAmount.subtract(BigDecimal.valueOf(useConditionLimit)).doubleValue() > 0)) {
                        availableList.add(couponCustomerDetailVO);
                    } else {
                        unAvailableList.add(couponCustomerDetailVO);
                    }
                }
            } else if (Objects.equals(scopeType, CouponScopeTypeEnum.SPECIFIED_ITEM.getValue())) {
                //指定分类
                List<Long> itemIdList = Lists.newArrayList();
                List<CouponItem> couponItemList = couponCustomerDetailVO.getCouponItemList();
                if (!CollectionUtils.isEmpty(couponItemList)) {
                    itemIdList = couponItemList.stream().map(CouponItem::getItemId).collect(Collectors.toList());
                }
                BigDecimal totalAmount = calculateTotalAmountByItemIds(cartList, itemIdList);
                loadCouponCustomerDetailListByFilterIds(totalAmount, couponCustomerDetailVO, availableList, unAvailableList);
            } else if (Objects.equals(scopeType, CouponScopeTypeEnum.SPECIFIED_SPU.getValue())) {
                //指定商品
                List<Long> spuIdList = Lists.newArrayList();
                List<CouponSpu> couponSpuList = couponCustomerDetailVO.getCouponSpuList();
                if (!CollectionUtils.isEmpty(couponSpuList)) {
                    spuIdList = couponSpuList.stream().map(CouponSpu::getSpuId).collect(Collectors.toList());
                }
                BigDecimal totalAmount = calculateTotalAmountBySpuIds(cartList, spuIdList);
                loadCouponCustomerDetailListByFilterIds(totalAmount, couponCustomerDetailVO, availableList, unAvailableList);
            }
        }
        if (type.equals(BooleanEnum.NO.getValue())) {
            return unAvailableList;
        } else {
            return availableList;
        }
    }


    private void loadCouponCustomerDetailListByFilterIds(BigDecimal totalAmount, CouponCustomerDetailVO couponCustomerDetailVO, List<CouponCustomerDetailVO> availableList, List<CouponCustomerDetailVO> unAvailableList) {
        Coupon coupon = couponCustomerDetailVO.getCoupon();
        if (Objects.equals(coupon.getUseConditionType(), CouponUseConditionTypeEnum.NO_THRESHOLD.getValue())) {
            availableList.add(couponCustomerDetailVO);
        } else if (Objects.equals(coupon.getUseConditionType(), CouponUseConditionTypeEnum.MINIMUM_AMOUNT.getValue())) {
            if (LocalDateTime.now().isBefore(coupon.getEndEffectTime()) && (totalAmount.subtract(BigDecimal.valueOf(coupon.getUseConditionLimit())).doubleValue() > 0)) {
                availableList.add(couponCustomerDetailVO);
            } else {
                unAvailableList.add(couponCustomerDetailVO);
            }
        }
    }

    private BigDecimal calculateTotalAmountBySpuIds(List<CartPromotionVO> cartList, List<Long> spuIds) {
        BigDecimal total = new BigDecimal(0);
        for (CartPromotionVO cartPromotionVO : cartList) {
            Long spuId = cartPromotionVO.getSpuId();
            if (spuIds.contains(spuId)) {
                BigDecimal realPrice = cartPromotionVO.getPrice().subtract(cartPromotionVO.getPerReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(cartPromotionVO.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calculateTotalAmountByItemIds(List<CartPromotionVO> cartList, List<Long> itemIds) {
        BigDecimal total = new BigDecimal(0);
        for (CartPromotionVO cartPromotionVO : cartList) {
            Long itemId = cartPromotionVO.getItemId();
            if (itemIds.contains(itemId)) {
                BigDecimal realPrice = cartPromotionVO.getPrice().subtract(cartPromotionVO.getPerReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(cartPromotionVO.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calculateTotalAmount(List<CartPromotionVO> cartList) {
        BigDecimal total = new BigDecimal(0);
        for (CartPromotionVO item : cartList) {
            BigDecimal realPrice = item.getOriginalPrice().subtract(item.getPerReduceAmount());
            total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    @Override
    public Integer updateCouponStatus(Long customerId, Long couponId, Integer useStatus) {
        return couponCustomerMapper.updateCouponStatus(customerId, couponId, useStatus);
    }
}
