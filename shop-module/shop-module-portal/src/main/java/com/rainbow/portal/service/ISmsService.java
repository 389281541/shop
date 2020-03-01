package com.rainbow.portal.service;

import com.rainbow.common.dto.SmsVerifyDTO;

/**
 *
 */
public interface ISmsService {

    /**
     * 发送短信
     * @param mobile
     * @return
     */
    boolean sendVerifyCode(String mobile);

    /**
     * 短信验证
     * @param param
     * @return
     */
    Boolean verify(SmsVerifyDTO param);


}
