package com.rainbow.portal.service;

import com.rainbow.api.dto.ReturnOrderDTO;

/**
 * 退单service
 *
 * @author lujinwei
 * @since 2020/3/28
 */
public interface IReturnOrderService {



    /**
     * 退单函数
     * @param param
     * @return
     */
    Integer create(ReturnOrderDTO param);
}
