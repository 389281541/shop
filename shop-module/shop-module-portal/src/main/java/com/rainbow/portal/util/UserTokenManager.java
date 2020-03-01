package com.rainbow.portal.util;

/**
 * 维护用户token
 */
public class UserTokenManager {
    /**
     * 生成Token
     * @param id
     * @return
     */
	public static String generateToken(Long id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }

    /**
     * 获取userId
     * @param token
     * @return
     */
    public static Integer getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId == 0){
    		return null;
    	}
        return userId;
    }
}
