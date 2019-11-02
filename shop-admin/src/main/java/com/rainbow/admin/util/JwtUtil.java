package com.rainbow.admin.util;


import com.rainbow.admin.model.TokenModel;
import com.rainbow.common.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * json web token工具类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public class JwtUtil {


    /**
     * 由字符串生成加密key
     *
     * @return
     * @throws Exception
     */
    public SecretKey generalKey() throws Exception {
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now) //签发时间
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        System.out.println(builder.compact());
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成subject信息
     *
     * @param user
     * @return
     */
    public static String generalSubject(TokenModel user) {
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getUserId());
        jo.put("userName", user.getUserName());
        jo.put("mobile", user.getMobile());
        jo.put("sessionId", UUID.randomUUID().toString());
        jo.put("sessionTime", user.getSessionTime());
        jo.put("iss", "shuidaoxianjin.cn"); //签发结构
        jo.put("iat", System.currentTimeMillis()); //token签发时间
        jo.put("exp", System.currentTimeMillis() + Constant.JWT_REFRESH_TTL); //token过期时间
        jo.put("allow_exp", System.currentTimeMillis() + Constant.JWT_REFRESH_TTL + 5000); //允许token过期重发的时间，+5秒
        return jo.toJSONString();
    }
}
