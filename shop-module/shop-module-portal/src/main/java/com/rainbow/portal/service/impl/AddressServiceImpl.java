package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.api.dto.CustomerAddressSaveDTO;
import com.rainbow.api.dto.CustomerAddressUpdateDTO;
import com.rainbow.api.entity.CustomerAddress;
import com.rainbow.api.vo.CustomerAddressVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.model.KV;
import com.rainbow.portal.mapper.CustomerAddressMapper;
import com.rainbow.portal.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 收货地址实现类
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<CustomerAddressMapper, CustomerAddress> implements IAddressService {

    @Override
    public Integer updateAddress(CustomerAddressUpdateDTO param) {
        CustomerAddress customerAddress = new CustomerAddress();
        BeanUtils.copyProperties(param, customerAddress);
        return baseMapper.updateById(customerAddress);
    }


    @Override
    public Integer addAddress(CustomerAddressSaveDTO param) {
        CustomerAddress customerAddress = new CustomerAddress();
        BeanUtils.copyProperties(param, customerAddress);
        return baseMapper.insert(customerAddress);
    }

    @Override
    public CustomerAddressVO getAddressDetail(IdDTO param) {
        CustomerAddress customerAddress = baseMapper.selectById(param.getId());
        CustomerAddressVO customerAddressVO = new CustomerAddressVO();
        BeanUtils.copyProperties(customerAddress, customerAddressVO);
        return customerAddressVO;
    }


    @Override
    public Integer removeAddress(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }

    @Override
    public List<CustomerAddressVO> listAddress(Long customerId) {
        LambdaQueryWrapper<CustomerAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerAddress::getDelStatus, DelFlagEnum.NO.getValue());
        wrapper.eq(CustomerAddress::getCustomerId, customerId);
        wrapper.orderByDesc(CustomerAddress::getIsDefault);
        List<CustomerAddress> customerAddressList = baseMapper.selectList(wrapper);
        List<CustomerAddressVO> customerAddressVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(customerAddressList)) {
            customerAddressVOList = customerAddressList.stream().map(x -> {
                CustomerAddressVO customerAddressVO = new CustomerAddressVO();
                BeanUtils.copyProperties(x, customerAddressVO);
                return customerAddressVO;
            }).collect(Collectors.toList());
        }
        return customerAddressVOList;
    }


    @Override
    public Boolean setDefault(Long customerId, Long addressId) {
        List<CustomerAddressVO> customerAddressList = listAddress(customerId);
        if(CollectionUtils.isEmpty(customerAddressList)) {
            return Boolean.FALSE;
        }
        List<KV<Long, Integer>> kvList = customerAddressList.stream().map(x -> {
            KV<Long, Integer> kv = new KV<>();
            kv.setK(x.getId());
            kv.setV(x.getId().equals(addressId)? BooleanEnum.YES.getValue() : BooleanEnum.NO.getValue());
            return kv;
        }).collect(Collectors.toList());
        Integer res = baseMapper.updateDefault(kvList);
        return res > 0;
    }
}
