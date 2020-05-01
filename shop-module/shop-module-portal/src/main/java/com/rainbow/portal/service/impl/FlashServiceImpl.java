package com.rainbow.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.rainbow.api.dto.GoConfirmOrderDTO;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.entity.*;
import com.rainbow.api.enums.FlashProcessStatusEnum;
import com.rainbow.api.enums.PortalErrorCode;
import com.rainbow.api.enums.PromotionTypeEnum;
import com.rainbow.api.vo.*;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.RedisKeyEnums;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.model.HMS;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.PortalUtils;
import com.rainbow.portal.mapper.*;
import com.rainbow.portal.mq.FlashMessage;
import com.rainbow.portal.mq.FlashMessageSender;
import com.rainbow.portal.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 秒杀服务service
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Service
@Slf4j
public class FlashServiceImpl implements IFlashService, InitializingBean {

    @Resource
    private FlashPromotionMapper flashPromotionMapper;

    @Resource
    private FlashPromotionSessionMapper flashPromotionSessionMapper;

    @Resource
    private FlashPromotionSpuMapper flashPromotionSpuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuImgMapper spuImgMapper;

    @Resource
    private SkuMapper skuMapper;

    @Autowired
    AmqpTemplate amqpTemplate ;

    @Resource
    private CustomerMapper customerMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private IOrderService orderService;

    @Resource
    private ShopMapper shopMapper;

    @Autowired
    private IFlashService flashService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private FlashMessageSender flashMessageSender;

    /**
     * 秒杀完成标志map
     */
    private ConcurrentHashMap<Long, Boolean> flashOverMap = new ConcurrentHashMap<>();

    @Override
    public List<FlashThemeVO> listFlashThemes() {
        //1、获取秒杀状态和秒杀倒计时
        //判断当前时间是否在秒杀时间内 如果在秒杀时间内
        FlashCurrentSessionVO currentPromotionSession = getCurrentPromotionSession();
        if(currentPromotionSession == null) {
            return Lists.newArrayList();
        }
        //2、获取所有上线的秒杀活动
        List<FlashPromotion> flashPromotionList = flashPromotionMapper.listByStatus(BooleanEnum.YES.getValue());
        if (CollectionUtils.isEmpty(flashPromotionList)) {
            return Lists.newArrayList();
        }
        //3、获取各个上线状态的秒杀商品列表
        List<Long> flashPromotionIds = flashPromotionList.stream().map(FlashPromotion::getId).collect(Collectors.toList());
        List<FlashPromotionSpu> flashPromotionSpuList = flashPromotionSpuMapper.listBySessionId(currentPromotionSession.getId(), flashPromotionIds);
        if(CollectionUtils.isEmpty(flashPromotionSpuList)) {
            return Lists.newArrayList();
        }
        Set<Long> spuIds = flashPromotionSpuList.stream().map(FlashPromotionSpu::getSpuId).collect(Collectors.toSet());
        List<Spu> spuList = spuMapper.listBySpuIds(spuIds);
        final Map<Long, BigDecimal> flashPriceMap = flashPromotionSpuList.stream().collect(Collectors.toMap(FlashPromotionSpu::getSpuId, FlashPromotionSpu::getFlashDiscountPrice, (v1, v2)->v2));
        final Map<Long, String> spuNameMap = spuList.stream().collect(Collectors.toMap(Spu::getId, Spu::getName));
        List<SpuImg> spuImgList = spuImgMapper.listCoversBySpuIds(spuIds);
        final Map<Long, String> spuCoverMap = spuImgList.stream().collect(Collectors.toMap(SpuImg::getSpuId, SpuImg::getImgUrl));
        final Map<Long, List<FlashGoodsSimpleVO>> flashPromotionMap = flashPromotionSpuList.stream().map(x->{
            FlashGoodsSimpleVO flashGoodsSimpleVO = new FlashGoodsSimpleVO();
            flashGoodsSimpleVO.setId(x.getSpuId());
            flashGoodsSimpleVO.setFlashPromotionId(x.getFlashPromotionId());
            String spuName = spuNameMap.get(x.getSpuId());
            if(spuName.length() > 30) {
                spuName = spuName.substring(0, 30) + "...";
            }
            flashGoodsSimpleVO.setName(spuName);
            flashGoodsSimpleVO.setCoverImg(spuCoverMap.get(x.getSpuId()));
            Sku sku = skuMapper.getMinPriceSkuBySpuId(x.getSpuId());
            flashGoodsSimpleVO.setFlashPrice(sku.getOriginalPrice().subtract(flashPriceMap.get(x.getSpuId())));
            flashGoodsSimpleVO.setOriginalPrice(sku.getOriginalPrice());
            return flashGoodsSimpleVO;
        }).collect(Collectors.groupingBy(FlashGoodsSimpleVO::getFlashPromotionId));
        //4、返回秒杀活动列表
        final Integer lambdaFlashProcessStatus = currentPromotionSession.getFlashStatus();
        final HMS lambdaHms = currentPromotionSession.getHms();
        List<FlashThemeVO> flashThemeVOList = flashPromotionList.stream().map(x -> {
            FlashThemeVO flashThemeVO = new FlashThemeVO();
            flashThemeVO.setTheme(x.getTheme());
            flashThemeVO.setHms(lambdaHms);
            flashThemeVO.setFlashGoodsList(flashPromotionMap.get(x.getId()));
            flashThemeVO.setFlashStatus(lambdaFlashProcessStatus);
            return flashThemeVO;
        }).filter(x->CollectionUtils.isNotEmpty(x.getFlashGoodsList())).collect(Collectors.toList());
        return flashThemeVOList;
    }

    public FlashCurrentSessionVO getCurrentPromotionSession() {
        FlashCurrentSessionVO flashCurrentSessionVO = new FlashCurrentSessionVO();
        List<FlashPromotionSession> flashPromotionSessionList = flashPromotionSessionMapper.listFlashSession();
        if (CollectionUtils.isEmpty(flashPromotionSessionList)) {
            return null;
        }
        FlashProcessStatusEnum flashProcessStatusEnum = FlashProcessStatusEnum.WAIT_TO_FLASH;
        HMS hms = new HMS();
        LocalTime now = LocalTime.now();
        FlashPromotionSession currentFlashPromotionSession = null;
        for (FlashPromotionSession flashPromotionSession : flashPromotionSessionList) {
            LocalTime startTime = flashPromotionSession.getStartTime();
            LocalTime endTime = flashPromotionSession.getEndTime();
            if (now.isBefore(endTime) && now.isAfter(startTime)) {
                currentFlashPromotionSession = flashPromotionSession;
                Duration duration = Duration.between(now, endTime);
                hms = DateUtils.convertMills2HMS(duration.toMillis());
                flashProcessStatusEnum = FlashProcessStatusEnum.FLASHING;
                break;
            }
        }
        // 如果当前时间不是秒杀时间 获取秒杀倒计时
        if(flashProcessStatusEnum.equals(FlashProcessStatusEnum.WAIT_TO_FLASH)) {
            FlashPromotionSession recentSession = flashPromotionSessionMapper.getRecentSession(LocalTime.now(), LocalTime.of(23,59,59));
            LocalTime startTime = recentSession.getStartTime();
            Duration duration = Duration.between(now, startTime);
            hms = DateUtils.convertMills2HMS(duration.toMillis());
            currentFlashPromotionSession = recentSession;
        }
        BeanUtils.copyProperties(currentFlashPromotionSession, flashCurrentSessionVO);
        flashCurrentSessionVO.setHms(hms);
        flashCurrentSessionVO.setFlashStatus(flashProcessStatusEnum.getValue());
        return flashCurrentSessionVO;
    }

    @Override
    public ConfirmOrderVO generateFlashConfirmOrder(Long customerId) {
        ConfirmOrderVO confirmOrderVO = new ConfirmOrderVO();
        //读取秒杀商品信息
        JSONObject flashGood = getFlashGoodsInfo(customerId);

        //购物车列表带促销信息
        List<CartPromotionVO> cartPromotionVOList = getCartPromotionList(flashGood, customerId);
        confirmOrderVO.setCartPromotionList(cartPromotionVOList);
        confirmOrderVO.setOrderNum(1);
        //收货地址
        List<CustomerAddressVO> customerAddressVOList = addressService.listAddress(customerId);
        confirmOrderVO.setCustomerAddressList(customerAddressVOList);
        //计算订单能使用的最大积分
        confirmOrderVO.setRecommendIntegration(orderService.calculateMaxCanUseIntegration(cartPromotionVOList, customerId));
        //可以使用的优惠券列表
        List<CouponCustomerDetailVO> couponCustomerDetailVOList = couponService.listOrderAvailable(customerId, cartPromotionVOList, BooleanEnum.YES.getValue());
        confirmOrderVO.setCouponList(orderService.convertCoupon(couponCustomerDetailVOList));
        return confirmOrderVO;
    }

    /**
     * 获取当前秒杀商品
     * @param customerId
     * @return
     */
    private JSONObject getFlashGoodsInfo(Long customerId) {
        String key = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_FLASH_GOODS_KEY.getRedisKey() + "_" + customerId;
        String goodsInfo = redisTemplate.opsForValue().get(key);
        if(Strings.isBlank(goodsInfo)) {
            throw new BusinessException(PortalErrorCode.FLASH_CONFIRM_ORDER_EXPIRED);
        }
        return JSONObject.parseObject(goodsInfo);
    }

    private List<CartPromotionVO> getCartPromotionList(JSONObject flashGood, Long customerId) {
        Long skuId = flashGood.getLong("skuId");
        Integer quantity = flashGood.getInteger("quantity");
        BigDecimal flashPrice = flashGood.getBigDecimal("flashPrice");
        List<CartPromotionVO> cartPromotionVOList = Lists.newArrayList();
        CartPromotionVO cartPromotionVO = new CartPromotionVO();
        Cart cart = cartService.buildCartInfo(skuId, quantity, customerId);
        BeanUtils.copyProperties(cart, cartPromotionVO);
        cartPromotionVO.setPromotionType(PromotionTypeEnum.TIME_LIMIT.getValue());
        cartPromotionVO.setPerReduceAmount(cart.getOriginalPrice().subtract(flashPrice));
        Shop shop = shopMapper.selectById(cart.getShopId());
        cartPromotionVO.setShopName(shop.getName());
        cartPromotionVOList.add(cartPromotionVO);
        return cartPromotionVOList;
    }


    @Override
    public Boolean goConfirmOrder(GoConfirmOrderDTO param) {

        JSONObject flashGoodsInfo = new JSONObject();
        flashGoodsInfo.put("skuId", param.getSkuId());
        flashGoodsInfo.put("quantity", param.getQuantity());
        flashGoodsInfo.put("flashPrice", param.getFlashPrice());
        String key = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_FLASH_GOODS_KEY.getRedisKey() + "_" + param.getCustomerId();
        redisTemplate.opsForValue().set(key, flashGoodsInfo.toJSONString(), 1, TimeUnit.DAYS);
        return Boolean.TRUE;
    }

    /**
     * 系统初始化，将所有秒杀商品库存放入redis中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LambdaQueryWrapper<FlashPromotionSpu> wrapper = new LambdaQueryWrapper<>();
        List<FlashPromotionSpu> flashPromotionSpuList = flashPromotionSpuMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(flashPromotionSpuList)) {
            return;
        }
        for(FlashPromotionSpu flashPromotionSpu: flashPromotionSpuList) {
            String key =  RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_STOCK_KEY.getRedisKey() + "_" + flashPromotionSpu.getFlashPromotionSessionId() + "_" + flashPromotionSpu.getSpuId();
            redisTemplate.opsForValue().set(key, String.valueOf(flashPromotionSpu.getFlashPromotionNum()));
        }
    }

    @Override
    public String generateFlashOrder(OrderGenerateDTO param) {
        //查询秒杀商品
        JSONObject flashGoodsInfo = getFlashGoodsInfo(param.getCustomerId());
        Long skuId = flashGoodsInfo.getLong("skuId");
        Sku sku = skuMapper.selectById(skuId);
        if(sku == null) {
            throw new BusinessException(PortalErrorCode.EMPTY_ORDER_SKU);
        }
        //查看是否秒杀到
        String key = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_FLASH_ORDER_KEY.getRedisKey() + "_" + param.getCustomerId() + "_" + skuId;

        String s = redisTemplate.opsForValue().get(key);
        if(Strings.isNotBlank(s)) {
            throw new BusinessException(PortalErrorCode.FLASH_REPEAT_ERROR);
        }
        //预减库存
        boolean over = flashOverMap.get(sku.getSpuId());
        if (over) {
            throw new BusinessException(PortalErrorCode.FLASH_IS_OVER);
        }
        //获取当前时间段
        FlashCurrentSessionVO currentPromotionSession = flashService.getCurrentPromotionSession();
        String stockKey = RedisKeyEnums.PORTAL.REDIS_KEY_PREFIX_STOCK_KEY.getRedisKey() + "_" + currentPromotionSession.getId() + "_" + sku.getSpuId();

        String stock = redisTemplate.opsForValue().get(stockKey);
        if(Strings.isBlank(stock)) {
            throw new BusinessException(PortalErrorCode.FLASH_CONFIRM_ORDER_EXPIRED);
        }
        Long reducedStock = redisTemplate.opsForValue().increment(stockKey, -1);
        if (reducedStock < 0) {
            flashOverMap.put(sku.getSpuId(), true);
            throw new BusinessException(PortalErrorCode.FLASH_IS_OVER);
        }
        //发送秒杀信息
        String parentOrderNO = PortalUtils.generateParentOrderNO(param.getCustomerId());
        FlashMessage flashMessage = new FlashMessage();
        BeanUtils.copyProperties(param, flashMessage);
        flashMessage.setCartPromotionVOList(getCartPromotionList(flashGoodsInfo, param.getCustomerId()));
        flashMessage.setParentOrderNO(parentOrderNO);
        flashMessageSender.sendFlashMessage(flashMessage);
        return parentOrderNO;
    }
}
