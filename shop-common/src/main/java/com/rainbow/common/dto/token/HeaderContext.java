package com.rainbow.common.dto.token;


import com.rainbow.common.enums.HeaderEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理请求header
 *
 * 使用：HeaderContext.get(HeaderEnum.VERSION)
 *
 * @author lujinwei
 * @date 2019/7/2 下午2:21
 */
public class HeaderContext {

    private static final ThreadLocal<Map<HeaderEnum, String>> headers = new ThreadLocal<>();

    public static void init(HttpServletRequest request) {
        Map<HeaderEnum, String> header = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            HeaderEnum headerEnum = HeaderEnum.get(headerName);
            if (!headerEnum.equals(HeaderEnum.UNKNOWN)) {
                header.put(headerEnum, request.getHeader(headerName));
            }
        }
        headers.set(header);
    }

    public static String get(HeaderEnum headerEnum) {
        return headers.get().get(headerEnum);
    }

}
