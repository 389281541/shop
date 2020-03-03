package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.api.dto.ShopSaveDTO;
import com.rainbow.api.dto.ShopSearchDTO;
import com.rainbow.api.dto.ShopUpdateDTO;
import com.rainbow.api.vo.ShopDetailVO;
import com.rainbow.api.vo.ShopSimpleVO;
import com.rainbow.api.entity.Shop;
import com.rainbow.api.enums.AuditStatusEnum;
import com.rainbow.admin.mapper.ShopMapper;
import com.rainbow.admin.service.IShopService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.StatusChangeDTO;
import com.rainbow.common.enums.DelFlagEnum;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 店铺表 服务实现类
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public IPage<ShopSimpleVO> pageShop(ShopSearchDTO param) {
        Page<Shop> shopPage = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        if(Strings.isNotBlank(param.getName())) {
            wrapper.like(Shop::getName, param.getName());
        }
        if(Strings.isNotBlank(param.getKeeperName())) {
            wrapper.like(Shop::getKeeperName, param.getKeeperName());
        }
        if(Strings.isNotBlank(param.getPhoneNumber())) {
            wrapper.like(Shop::getPhoneNumber, param.getPhoneNumber());
        }
        if(param.getAuditStatus() != null) {
            wrapper.eq(Shop::getAuditStatus, param.getAuditStatus());
        }
        if(param.getType() != null) {
            wrapper.eq(Shop::getType, param.getType());
        }
        wrapper.eq(Shop::getDelStatus, DelFlagEnum.NO.getValue());
        IPage<Shop> shopList = page(shopPage, wrapper);
        //分页数据
        return shopList.convert(x -> {
            ShopSimpleVO shopSimpleVO = new ShopSimpleVO();
            BeanUtils.copyProperties(x, shopSimpleVO);
            return shopSimpleVO;
        });
    }

    @Override
    public Integer saveShop(ShopSaveDTO param) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(param, shop);
        shop.setAuditStatus(AuditStatusEnum.AUDITING.getValue());
        shop.setCreateTime(LocalDateTime.now());
        shop.setDelStatus(DelFlagEnum.NO.getValue());
        return baseMapper.insert(shop);
    }

    @Override
    public Integer updateShop(ShopUpdateDTO param) {
        return baseMapper.updateShop(param);
    }

    @Override
    public ShopDetailVO getShopDetailById(IdDTO param) {
        ShopDetailVO shopDetailVO = new ShopDetailVO();
        Shop shop = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(shop, shopDetailVO);
        return shopDetailVO;
    }

    @Override
    public Integer removeShop(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }


    @Override
    public Integer audit(StatusChangeDTO param) {
        return baseMapper.updateAuditStatus(param);
    }
}
