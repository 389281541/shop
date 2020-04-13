package com.rainbow.portal.service;

import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.vo.UploadImageVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 阿里支付service
 *
 * @author lujinwei
 * @since 2020/4/7
 */
public interface IPaymentService {

    /**
     * 创建支付二维码
     * @param param
     * @return
     */
    UploadImageVO createPayQrCodeImage(ParentOrderNoDTO param);


    /**
     * 支付成功回调
     */
    Boolean paySucessCallback(Long customerId, HttpServletRequest request);
}
