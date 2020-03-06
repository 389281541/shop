package com.rainbow.common.dto.token;


import io.swagger.annotations.ApiModelProperty;

/**
 * 天校ThreadLocal authToken存储
 *
 * @author lujinwei
 * @since 2019-01-07
 */
public class RB extends AbstractContext {

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

    /**
     *登录账号OpenId
     */
    private static final String OPENID = "open_id";


    /**
     *当前时间
     */
    private static final String CT = "ct";


    /**
     *盐
     */
    private static final String SALT = "salt";



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

    public static void setOpenId(String openId) {
        set(OPENID, openId);
    }

    public static String getOpenId() {
        return get(OPENID, String.class);
    }

    public static void setCt(Long ct) {
        set(CT, ct);
    }

    public static Long getCt() {
        return get(CT, Long.class);
    }

    public static void setSalt(String salt) {
        set(SALT, salt);
    }

    public static String getSalt() {
        return get(SALT, String.class);
    }

}
