package com.rainbow.admin.module;

import com.alibaba.fastjson.JSON;
import com.rainbow.common.dto.R;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当访问接口没有权限时，自定义当返回结果
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSON(new R(BaseErrorCode.PERMISSION_DENIED)));
        response.getWriter().flush();
    }
}
