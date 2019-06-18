package com.rainbow.admin.util;


import com.alibaba.fastjson.JSONObject;
import com.rainbow.admin.module.TokenModel;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.MD5Utils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhongquanliang
 * @version V1.0
 * @createTime 2017年5月8日 下午8:09:52 类说明
 */

@Component
public class JwtManager {
    private static final Logger logger = LoggerFactory.getLogger(JwtManager.class);
    private static final String NO_EXPIRE = "0";    // 未过期
    private static final String OVER_EXPIRE = "1";  // 已过期
    private static final String GOING_EXPIRE = "2"; // 即将过期
    private static final String ERROR_TOKEN = "4";  // 错误token

    // 创建token
    public TokenModel createToken(TokenModel user) {
        //String userId = user.getUserId().toString();
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

    // 创建token
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

    // 检查jwt
    public String checkToken(String jwt) {
        if (StringUtils.isBlank(jwt)) {
            return ERROR_TOKEN;
        }
        JwtUtil jwtUtil = new JwtUtil();
        try {
            // 校验jwt信息
            Claims claims = jwtUtil.parseJWT(jwt);
            Date exp = claims.getExpiration(); // 获取TOKEN过期时间
            //System.out.println("用户jwt中的subject----------" + claims.getSubject());
            JSONObject subject = JSONObject.parseObject(claims.getSubject());
           // String expire = subject.getString("allow_exp"); // sub中的过期时间
          //  String allow_exp = subject.getString("exp"); // sub中的允许过期间隔时间
          //  String iat = subject.getString("iat"); // sub中的签发时间
            // 1 首先检查token是否在有效期内
            if (DateUtils.someDateExpireNow(exp)) {
                return OVER_EXPIRE;
            } else {
                // 如果token本身没过期，继续判断是否是已经注销的token
                boolean flag = false;
                try {
                    //System.out.println("TOKEN失效判断原始：" + jwt);
                    jwt = MD5Utils.encrypByMd5(jwt);
                  //  System.out.println("TOKEN失效判断：" + jwt);
                    //flag = RedisUtil.existString("logout_token_" + jwt);
                } catch (Exception e) {
                    System.out.println("redis 连接失败");
                }
                if (!flag)
                    return NO_EXPIRE;
                else
                    return OVER_EXPIRE;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR_TOKEN;
        }
    }

    /**
     * @Description: 获取token中的用户信息
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

    // 注销登录-token
    public boolean logoutToken(String token) {
        JwtUtil jwtUtil = new JwtUtil();
        Claims claims;
        try {
            claims = jwtUtil.parseJWT(token);
            Date exp = claims.getExpiration(); // 获取TOKEN过期时间
            Long lastMillis = 0L;
            if (exp.getTime() > System.currentTimeMillis()) {
                lastMillis = exp.getTime() - System.currentTimeMillis();
                logger.info("logout_token_" + token + "剩余过期时间-" + lastMillis + "毫秒");
                System.out.println("logout_token_" + token + "剩余过期时间-" + lastMillis + "毫秒");
            }
            // 存储到redis并设置过期时间
            if (lastMillis > 0) {
                logger.info("TOKEN失效原始：" + token);
                token = MD5Utils.encrypByMd5(token);
                //RedisUtil.setString("logout_token_" + token, (int) (lastMillis / 1000), "");
                logger.info("TOKEN失效：" + token);
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//		JwtManager jwt = new JwtManager();
//		//String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDE1NzY2MjcsInN1YiI6IntcImFsbG93X2V4cFwiOjE0OTg4Mzg0MDAwMDAsXCJtb2JpbGVcIjpcIjE4NTExNzYxMzQ4XCIsXCJpc3NcIjpcIm91ZGluZy5jb21cIixcInNlc3Npb25JZFwiOlwiYzkzNjU1NTMtYTUzMC00OWU2LTgxYWUtYjJkZDBmN2EzOTcwXCIsXCJleHBcIjoxNDk4ODM4NDAwMDAwLFwidXNlcklkXCI6XCIxOFwiLFwiaWF0XCI6MTUwMTU3NjYyNDgyOX0iLCJleHAiOjE1MDQxNjg2Mjd9.ZUoZfjCXPc3x-DZ961FUhG3SmGpQIS3nJJsWt0tvb4M";
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDIxNzM0NjIsInN1YiI6IntcImFsbG93X2V4cFwiOjE0OTkzNTY4MDUwMDAsXCJtb2JpbGVcIjpcIjE4NTE4MzE4MjY1XCIsXCJpc3NcIjpcIm91ZGluZy5jb21cIixcInNlc3Npb25JZFwiOlwiMDg2MGQ4ZTMtODMwZS00NjNiLTg0YzYtYzljMjEzODQ2YTY5XCIsXCJleHBcIjoxNDk5MzU2ODAwMDAwLFwidXNlcklkXCI6XCI2NFwiLFwiaWF0XCI6MTUwMjE3MzQ2MTU2MH0iLCJleHAiOjE1MDQ3NjU0NjJ9.O9q2ctcCUufr9HQvXR2oZCPCFTcyc77tayR1986NXzw";
//		//System.out.println(jwt.getSubject(token));
//
//		TokenModel user = new TokenModel();
//		/*user.setUserId("18");
//		user.setMobile("18511761348");*/
//		user.setUserId("64");
//		user.setMobile("18518318265");
//		//System.out.println(jwt.createTokenStr(user));
//		token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDIyNzY0MTUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1MDQ4Njg0MjA2NDMsXCJtb2JpbGVcIjpcIjE4NTE4MzE4MjY1XCIsXCJpc3NcIjpcIm91ZGluZy5jb21cIixcInNlc3Npb25JZFwiOlwiYmJiODI3YWYtZDc1Mi00MmQ1LTkzNGItZjg1OTk1MDMxY2ZlXCIsXCJleHBcIjoxNTA0ODY4NDE1NjQzLFwidXNlcklkXCI6XCI3N1wiLFwiaWF0XCI6MTUwMjI3NjQxNTY0M30iLCJleHAiOjE1MDQ4Njg0MTV9.7EaBtRllPu6iZyWDbCP5-ZisdoDW_fs4B7j3rLy70Lg";
//
//		System.out.println(MD5Utils.encrypByMd5(token));
//		System.out.println(MD5Utils.encrypByMd5(token));
        String userId = "738042";
         TokenModel tokenModel = new TokenModel();
       // tokenModel.setUserId(userId);
      //  tokenModel.setMobile("13416192055");
        tokenModel = new JwtManager().createToken(tokenModel);
        //	RedisUtil.setString( cacheKey, tokenModel.getToken());
        System.out.println(tokenModel.getToken());
        System.out.println("==================================");
        //System.out.println(RedisUtil.getString(cacheKey));

    }
}
