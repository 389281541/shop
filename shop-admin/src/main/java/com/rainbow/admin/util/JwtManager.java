package com.rainbow.admin.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rainbow.admin.module.TokenModel;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.util.MD5Utils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * json web token管理类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Component
@Slf4j
public class JwtManager {

    /**
     * 创建token
     *
     * @param user
     * @return
     */
    public TokenModel createToken(TokenModel user) {
        // 创建jwt
        JwtUtil jwtUtil = new JwtUtil();
        String subject = JwtUtil.generalSubject(user);
        String token = "";
        try {
            token = jwtUtil.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        user.setToken(token);
        return user;
    }

    /**
     * 创建token字符
     *
     * @param user
     * @return
     */
    public String createTokenStr(TokenModel user) {
        // 创建jwt
        JwtUtil jwtUtil = new JwtUtil();
        String subject = JwtUtil.generalSubject(user);
        String token = "";
        try {
            token = jwtUtil.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 获取token中的用户信息
     *
     * @param jwt
     * @return
     */
    public JSONObject getSubject(String jwt) {
        if (StringUtils.isBlank(jwt)) {
            return null;
        }
        JwtUtil jwtUtil = new JwtUtil();
        JSONObject subject;
        try {
            Claims claims = jwtUtil.parseJWT(jwt);
            subject = JSONObject.parseObject(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
        return subject;
    }

    /**
     * 注销登录-token
     *
     * @param token
     * @return
     */
    public boolean logoutToken(String token) {
        JwtUtil jwtUtil = new JwtUtil();
        Claims claims;
        try {
            claims = jwtUtil.parseJWT(token);
            Date exp = claims.getExpiration(); // 获取TOKEN过期时间
            Long lastMillis = 0L;
            if (exp.getTime() > System.currentTimeMillis()) {
                lastMillis = exp.getTime() - System.currentTimeMillis();
                log.info("logout_token_" + token + "剩余过期时间-" + lastMillis + "毫秒");
                System.out.println("logout_token_" + token + "剩余过期时间-" + lastMillis + "毫秒");
            }
            // 存储到redis并设置过期时间
            if (lastMillis > 0) {
                log.info("TOKEN失效原始：" + token);
                token = MD5Utils.encrypByMd5(token);
                log.info("TOKEN失效：" + token);
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public TokenModel getTokenModel(String authToken) throws BusinessException {
        if (StringUtils.isBlank(authToken)) {
            throw new BusinessException(BaseErrorCode.TOKEN_ERROR);
        }

        JSONObject jsonObject = getSubject(authToken);
        if (jsonObject == null) {
            throw new BusinessException(BaseErrorCode.TOKEN_ERROR);
        }
        return JSON.toJavaObject(jsonObject, TokenModel.class);
    }

}
