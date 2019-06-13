package com.rainbow.common.dto.token;


/**
 * 天校ThreadLocal authToken存储
 *
 * @author lujinwei
 * @since 2019-01-07 
 */
public class RB extends AbstractTXContext {

    /**
     * 基础用户ID
     */
    private static final String USER_ID = "user_id";
    /**
     * 用户名
     */
    private static final String USER_NAME = "user_name";
    /**
     * 登录帐号手机号
     */
    private static final String MOBILE = "mobile";

    @Override
    protected void init() {
        // nothing to do
    }


    public static void setUserId(Long userId) {
        set(USER_ID, userId);
    }

    public static Long getUserId() {
        return get(USER_ID, Long.class);
    }

    public static void setUserName(String userName) {
        set(USER_NAME, userName);
    }

    public static String getUserName() {
        return get(USER_NAME, String.class);
    }

    public static void setMobile(String mobile) {
        set(MOBILE, mobile);
    }

    public static String getMobile() {
        return get(MOBILE, String.class);
    }
}
