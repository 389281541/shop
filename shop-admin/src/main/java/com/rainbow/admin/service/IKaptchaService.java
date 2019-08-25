package com.rainbow.admin.service;

import com.rainbow.admin.api.dto.VerifyCodeDTO;
import com.rainbow.common.vo.IdNameVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IKaptchaService {

    /**
     * 获取验证码
     * @param request
     * @param response
     * @return
     */
    Boolean getCaptcha(HttpServletRequest request, HttpServletResponse response);

    /**
     * 验证码校验
     * @param request
     * @param req
     * @return
     */
    IdNameVO verifyKaptcha(HttpServletRequest request, VerifyCodeDTO req);
}
