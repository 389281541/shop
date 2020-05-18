package com.rainbow.portal.service;

import com.rainbow.api.dto.AliPayNotifyDTO;
import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.vo.UploadImageVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 阿里支付service
 *
 * @author lujinwei
 * @since 2020/4/7
 */
public interface IAliPayService {

    /**
     * 跳转到支付二维码
     * @param param
     * @return
     */
    String goPay(ParentOrderNoDTO param);


    /**
     * 支付成功回调
     */
    Boolean paySucessCallback(Long customerId, AliPayNotifyDTO param);
}
