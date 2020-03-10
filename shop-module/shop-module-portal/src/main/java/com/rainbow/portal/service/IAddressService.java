package com.rainbow.portal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.CustomerAddressSaveDTO;
import com.rainbow.api.dto.CustomerAddressUpdateDTO;
import com.rainbow.api.entity.CustomerAddress;
import com.rainbow.api.vo.CustomerAddressVO;
import com.rainbow.common.dto.IdDTO;

import java.util.List;

public interface IAddressService  extends IService<CustomerAddress> {

    /**
     * 更新收货地址
     * @param param
     * @return
     */
    Integer updateAddress(CustomerAddressUpdateDTO param);


    /**
     * 添加收货地址
     * @param param
     * @return
     */
    Integer addAddress(CustomerAddressSaveDTO param);


    /**
     * 获取收货地址详情
     * @param param
     * @return
     */
    CustomerAddressVO getAddressDetail(IdDTO param);


    /**
     * 移除收货地址详情
     * @param param
     * @return
     */
    Integer removeAddress(IdDTO param);


    /**
     * 地址列表
     * @return
     */
    List<CustomerAddressVO> listAddress(Long customerId);


    /**
     * 将某个地址设为默认
     * @param customerId
     * @param addressId
     * @return
     */
    Boolean setDefault(Long customerId, Long addressId);
}
