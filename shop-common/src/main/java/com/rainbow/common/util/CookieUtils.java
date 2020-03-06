package com.rainbow.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 *
 * @author lujinwei
 * @Date 2018/11/14 11:34
 */
@Slf4j
public class CookieUtils {

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equalsIgnoreCase(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String value, String domain, String path,
                                 int expires) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(expires);
        response.addCookie(cookie);
        log.debug("addCookie - name:{}, value:{}, domain:{}, path:{}, expires:{}", name, value, domain, path,
                expires);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                cookie.setDomain(domain);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                return;
            }
        }
    }

}