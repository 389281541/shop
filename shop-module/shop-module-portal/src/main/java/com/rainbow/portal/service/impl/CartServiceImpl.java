package com.rainbow.portal.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.api.dto.CartQuantityUpdateDTO;
import com.rainbow.api.dto.CartSaveDTO;
import com.rainbow.api.entity.*;
import com.rainbow.api.enums.PortalErrorCode;
import com.rainbow.api.enums.PromotionTypeEnum;
import com.rainbow.api.vo.CartPromotionVO;
import com.rainbow.api.vo.SpuFullReductionSimpleVO;
import com.rainbow.api.vo.SpuPromotionVO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 购物车表 服务实现类
 *
 * @author lujinwei
 * @since 2020-03-14
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuImgMapper spuImgMapper;

    @Resource
    private SkuSpecMapper skuSpecMapper;

    @Resource
    private SpuFullReductionMapper spuFullReductionMapper;

    @Resource
    private ShopMapper shopMapper;


    @Override
    public Integer addCart(CartSaveDTO param) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getSkuId, param.getSkuId());
        wrapper.eq(Cart::getCustomerId, param.getCustomerId());
        wrapper.eq(Cart::getDelStatus, DelFlagEnum.NO.getValue());
        Cart cartInfo = getOne(wrapper);
        //查看是否限购
        checkPromotionLimit(param.getSkuId(), param.getQuantity(), BooleanEnum.NO.getValue());
        if (cartInfo == null) {
            return baseMapper.insert(buildCartInfo(param));
        } else {
            cartInfo.setUpdateTime(LocalDateTime.now());
            cartInfo.setQuantity(cartInfo.getQuantity() + param.getQuantity());
            return baseMapper.updateById(cartInfo);
        }
    }

    /**
     * 判断限购
     *
     * @param skuId
     * @param quantity model为0时新加入购物车的数量 为1时购物车更新后的数量
     * @param model    0-加入购物车时  1-更改购物车商品数量
     */
    private void checkPromotionLimit(Long skuId, Integer quantity, Integer model) {
        //查找spu促销类型
        Sku sku = skuMapper.selectById(skuId);
        Spu spu = spuMapper.selectById(sku.getSpuId());
        Integer promotionType = spu.getPromotionType();
        if (promotionType.equals(BooleanEnum.NO.getValue())) {
            return;
        }
        Integer promotionPerLimit = spu.getPromotionPerLimit();
        //计算购物车该spu现有购买数
        Integer existQuantity = baseMapper.sumSpu(spu.getId());
        if ((Objects.equals(model, BooleanEnum.NO.getValue()) && existQuantity + quantity > promotionPerLimit) ||
                ((Objects.equals(model, BooleanEnum.YES.getValue()) && quantity > promotionPerLimit))) {
            throw new BusinessException(PortalErrorCode.PROMOTION_LIMIT_EXCEED);
        }
    }


    /**
     * 构建cart
     *
     * @param param
     * @return
     */
    private Cart buildCartInfo(CartSaveDTO param) {
        //sku信息
        Sku sku = skuMapper.selectById(param.getSkuId());
        //spu信息
        Spu spu = spuMapper.selectById(sku.getSpuId());
        if (spu.getSaleStatus().equals(BooleanEnum.NO.getValue())) {
            throw new BusinessException(PortalErrorCode.SPU_PULL_OFF);
        }
        //sku属性信息
        List<SkuSpec> skuSpecList = skuSpecMapper.listBySkuId(param.getSkuId());
        LambdaQueryWrapper<SpuImg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SpuImg::getSpuId, spu.getId());
        wrapper.eq(SpuImg::getSkuId, param.getSkuId());
        //sku图片
        SpuImg spuImg = spuImgMapper.selectOne(wrapper);
        JSONArray jsonArray = new JSONArray();
        for (SkuSpec skuSpec : skuSpecList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", skuSpec.getSpecName());
            jsonObject.put("value", skuSpec.getSpecValue());
            jsonArray.add(jsonObject);
        }
        Cart cart = new Cart();
        cart.setCustomerId(param.getCustomerId());
        cart.setSkuId(param.getSkuId());
        cart.setQuantity(param.getQuantity());
        cart.setSpuId(sku.getSpuId());
        cart.setSkuSpec(jsonArray.toJSONString());
        cart.setSkuStock(sku.getStock());
        cart.setSpuName(spu.getName());
        cart.setShopId(spu.getShopId());
        cart.setItemId(spu.getItemId());
        cart.setCoverImg(spuImg.getImgUrl());
        cart.setPrice(sku.getPrice());
        cart.setOriginalPrice(sku.getOriginalPrice());
        return cart;
    }


    @Override
    public List<CartPromotionVO> list(Long customerId) {
        List<CartPromotionVO> cartPromotionVOList = Lists.newArrayList();
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getCustomerId, customerId);
        wrapper.eq(Cart::getDelStatus, DelFlagEnum.NO.getValue());
        wrapper.orderByAsc(Cart::getShopId);
        List<Cart> cartList = baseMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(cartList)) {
            //分摊优惠
            cartPromotionVOList = calculatePromotion(cartList);
        }
        return cartPromotionVOList;
    }


    private List<CartPromotionVO> calculatePromotion(List<Cart> cartList) {
        List<CartPromotionVO> cartPromotionVOList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(cartList)) {
            return cartPromotionVOList;
        }
        //根据spuId分组
        Map<Long, List<Cart>> spuIdCartMap = cartList.stream().collect(Collectors.groupingBy(Cart::getSpuId));
        //获取SPU促销信息
        Map<Long, SpuPromotionVO> spuPromotionVOMap = getSpuPromotionMap(cartList);
        //skuMap信息
        Set<Long> skuIds = cartList.stream().map(Cart::getSkuId).collect(Collectors.toSet());
        List<Sku> skuList = skuMapper.selectBatchIds(skuIds);
        Map<Long, Sku> skuMap = skuList.stream().collect(Collectors.toMap(Sku::getId, Function.identity()));
        Set<Long> shopIds = cartList.stream().map(Cart::getShopId).collect(Collectors.toSet());
        List<Shop> shopList = shopMapper.selectBatchIds(shopIds);
        Map<Long, Shop> shopMap = shopList.stream().collect(Collectors.toMap(Shop::getId, Function.identity()));
        //计算优惠情况
        for (Map.Entry<Long, List<Cart>> entry : spuIdCartMap.entrySet()) {
            Long spuId = entry.getKey();
            List<Cart> carts = entry.getValue();
            SpuPromotionVO spuPromotionVO = spuPromotionVOMap.get(spuId);
            Integer promotionType = spuPromotionVO.getPromotionType();
            if (promotionType.equals(PromotionTypeEnum.TIME_LIMIT.getValue())) {
                //限时促销
                handleReduce(carts, cartPromotionVOList, skuMap, shopMap, promotionType, BooleanEnum.YES.getValue());
            } else if (promotionType.equals(PromotionTypeEnum.FULL_REDUCTION.getValue())) {
                //满减促销  计算总金额
                BigDecimal totalAmount = getCartTotalAmount(carts, skuMap);
                //获取合适的满减策略
                SpuFullReductionSimpleVO fullReduction = getSpuFullReduction(totalAmount, spuPromotionVO.getSpuFullReductionList());
                if (fullReduction != null) {
                    for (Cart cart : carts) {
                        CartPromotionVO cartPromotionVO = new CartPromotionVO();
                        BeanUtils.copyProperties(cart, cartPromotionVO);
                        Sku sku = skuMap.get(cart.getSkuId());
                        Shop shop = shopMap.get(cart.getShopId());
                        BigDecimal perReduceAmount = sku.getPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(fullReduction.getReducePrice());
                        cartPromotionVO.setPerReduceAmount(perReduceAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
                        cartPromotionVO.setPromotionType(promotionType);
                        cartPromotionVO.setSkuName(sku.getSkuName());
                        cartPromotionVO.setSkuLockStock(sku.getLockStock());
                        cartPromotionVO.setShopName(shop.getName());
                        cartPromotionVOList.add(cartPromotionVO);
                    }
                } else {
                    //默认按限时促销规则  时间只是个营销方式
                    handleReduce(carts, cartPromotionVOList, skuMap, shopMap, promotionType, BooleanEnum.NO.getValue());
                }
            } else {
                //默认按限时促销规则  时间只是个营销方式
                handleReduce(carts, cartPromotionVOList, skuMap, shopMap, promotionType, BooleanEnum.NO.getValue());
            }
        }
        return cartPromotionVOList;
    }

    /**
     * 处理优惠扣减
     *
     * @param carts
     * @param cartPromotionVOList
     * @param skuMap
     * @param promotionType
     * @param type                0:无优惠  1-限时优惠
     */
    private void handleReduce(List<Cart> carts, List<CartPromotionVO> cartPromotionVOList, Map<Long, Sku> skuMap, Map<Long, Shop> shopMap, Integer promotionType, Integer type) {
        for (Cart cart : carts) {
            CartPromotionVO cartPromotionVO = new CartPromotionVO();
            BeanUtils.copyProperties(cart, cartPromotionVO);
            Sku sku = skuMap.get(cart.getSkuId());
            Shop shop = shopMap.get(cart.getShopId());
            if (type.equals(BooleanEnum.NO.getValue())) {
                cartPromotionVO.setPerReduceAmount(new BigDecimal(0));
            } else {
                cartPromotionVO.setPerReduceAmount(sku.getOriginalPrice().subtract(sku.getPrice()));
            }
            cartPromotionVO.setPromotionType(promotionType);
            cartPromotionVO.setSkuName(sku.getSkuName());
            cartPromotionVO.setSkuLockStock(sku.getLockStock());
            cartPromotionVO.setShopName(shop.getName());
            cartPromotionVOList.add(cartPromotionVO);
        }
    }

    /**
     * 获取合适的满减策略
     *
     * @param totalAmount
     * @param spuFullReductionSimpleVOList
     * @return
     */
    private SpuFullReductionSimpleVO getSpuFullReduction(BigDecimal totalAmount, List<SpuFullReductionSimpleVO> spuFullReductionSimpleVOList) {
        spuFullReductionSimpleVOList.sort(new Comparator<SpuFullReductionSimpleVO>() {
            @Override
            public int compare(SpuFullReductionSimpleVO o1, SpuFullReductionSimpleVO o2) {
                return o2.getFullPrice().subtract(o1.getFullPrice()).intValue();
            }
        });

        for (SpuFullReductionSimpleVO spuFullReductionSimpleVO : spuFullReductionSimpleVOList) {
            if (totalAmount.subtract(spuFullReductionSimpleVO.getFullPrice()).doubleValue() >= 0) {
                return spuFullReductionSimpleVO;
            }
        }
        return null;
    }

    /**
     * 获取购物车总金额
     */
    private BigDecimal getCartTotalAmount(List<Cart> carts, Map<Long, Sku> skuMap) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (Cart cart : carts) {
            Long skuId = cart.getSkuId();
            Sku sku = skuMap.get(skuId);
            totalAmount = totalAmount.add(sku.getPrice().multiply(new BigDecimal(cart.getQuantity())));
        }
        return totalAmount;
    }

    /**
     * 获取spuMap信息
     *
     * @param cartList
     * @return
     */
    private Map<Long, SpuPromotionVO> getSpuPromotionMap(List<Cart> cartList) {
        Map<Long, SpuPromotionVO> spuPromotionVOMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(cartList)) {
            return spuPromotionVOMap;
        }
        Set<Long> spuIdSet = cartList.stream().map(Cart::getSpuId).collect(Collectors.toSet());
        List<Spu> spuList = spuMapper.selectBatchIds(spuIdSet);
        List<SpuFullReduction> spuFullReductionList = spuFullReductionMapper.listBySpuIds(spuIdSet);
        Map<Long, List<SpuFullReductionSimpleVO>> spuFullReductionSimpleVOMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(spuFullReductionList)) {
            List<SpuFullReductionSimpleVO> fullReductionSimpleVOList = spuFullReductionList.stream().map(x -> {
                SpuFullReductionSimpleVO spuFullReductionSimpleVO = new SpuFullReductionSimpleVO();
                BeanUtils.copyProperties(x, spuFullReductionSimpleVO);
                return spuFullReductionSimpleVO;
            }).collect(Collectors.toList());
            spuFullReductionSimpleVOMap = fullReductionSimpleVOList.stream().collect(Collectors.groupingBy(SpuFullReductionSimpleVO::getSpuId));
        }
        final Map<Long, Spu> spuMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Function.identity()));
        final Map<Long, List<SpuFullReductionSimpleVO>> spuFullReductionMap = spuFullReductionSimpleVOMap;
        spuPromotionVOMap = cartList.stream().collect(Collectors.toMap(Cart::getSpuId, x -> {
            SpuPromotionVO spuPromotionVO = new SpuPromotionVO();
            Spu spu = spuMap.get(x.getSpuId());
            BeanUtils.copyProperties(spu, spuPromotionVO);
            spuPromotionVO.setSpuFullReductionList(spuFullReductionMap.get(x.getSpuId()) == null ? Lists.newArrayList() : spuFullReductionMap.get(x.getSpuId()));
            return spuPromotionVO;
        }, (key1, key2) -> key2));
        return spuPromotionVOMap;
    }


    @Override
    public Integer updateQuantity(CartQuantityUpdateDTO param) {
        Cart cart = baseMapper.selectById(param.getId());
        checkPromotionLimit(cart.getSkuId(), param.getQuantity(), BooleanEnum.YES.getValue());
        return baseMapper.updateQuantity(param);
    }


    @Override
    public Integer removeCart(Long customerId, Long id) {
        List<Long> ids = Lists.newArrayList();
        ids.add(id);
        return baseMapper.removeCartList(customerId, ids);
    }

    @Override
    public Integer removeCartList(Long customerId, Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new BusinessException(PortalErrorCode.CART_CAN_NOT_EMPTY);
        }
        return baseMapper.removeCartList(customerId, ids);
    }
}
