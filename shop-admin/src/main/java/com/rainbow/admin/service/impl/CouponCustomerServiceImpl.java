package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.CouponCustomerSearchDTO;
import com.rainbow.admin.api.vo.CouponCustomerSimpleVO;
import com.rainbow.admin.entity.CouponCustomer;
import com.rainbow.admin.mapper.CouponCustomerMapper;
import com.rainbow.admin.service.ICouponCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 优惠券使用、领取历史表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Service
public class CouponCustomerServiceImpl extends ServiceImpl<CouponCustomerMapper, CouponCustomer> implements ICouponCustomerService {

    @Override
    public IPage<CouponCustomerSimpleVO> pageCouponCustomer(CouponCustomerSearchDTO param) {
        IPage<CouponCustomer> couponCustomerPageInfo = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<CouponCustomer> wrapper = new LambdaQueryWrapper<>();
        if (param.getUseStatus() != null) {
            wrapper.eq(CouponCustomer::getUseStatus, param.getUseStatus());
        }

        if (Strings.isNotBlank(param.getOrderNo())) {
            wrapper.eq(CouponCustomer::getOrderNo, param.getOrderNo());
        }

        IPage<CouponCustomer> couponCustomerPage = baseMapper.selectPage(couponCustomerPageInfo, wrapper);

        if (couponCustomerPage == null || CollectionUtils.isEmpty(couponCustomerPage.getRecords())) {
            IPage<CouponCustomerSimpleVO> couponCustomerSimpleVOIPage = new Page<>();
            couponCustomerSimpleVOIPage.setCurrent(param.getPageNum());
            couponCustomerSimpleVOIPage.setSize(param.getPageSize());
            couponCustomerSimpleVOIPage.setRecords(Lists.newArrayList());
            couponCustomerSimpleVOIPage.setTotal(0L);
            return couponCustomerSimpleVOIPage;
        }
        return couponCustomerPage.convert(x->{
            CouponCustomerSimpleVO couponCustomerSimpleVO = new CouponCustomerSimpleVO();
            BeanUtils.copyProperties(x, couponCustomerSimpleVO);
            return couponCustomerSimpleVO;
        });
    }
}
