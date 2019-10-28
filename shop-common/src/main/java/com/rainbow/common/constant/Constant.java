package com.rainbow.common.constant;

/**
 * 常量
 *
 * @author lujinwei
 * @since 2019-06-14
 */
public interface Constant {
    //jwt常量
    String JWT_ID = "jwt";
    String JWT_SECRET = "son#2gs921@shssu~4H&pinsdf213*%pinwe8*koperation";
    long JWT_REFRESH_TTL = 2 * 365 * 24 * 60 * 60 * 1000L;

    //缓存常量
    int USER_SESSION_CACHE_TIME = 60 * 24;
    String CACHE_USER_ID_PREFIX = "admin_user_id_";
    String LOGIN_TOKEN_COOKIE_NAME = "loginToken";
    String AUTH_TOKEN_NAME = "Authorization";

}
