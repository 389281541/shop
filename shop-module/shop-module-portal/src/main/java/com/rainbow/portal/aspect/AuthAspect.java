package com.rainbow.portal.aspect;

import com.rainbow.api.enums.AdminErrorCode;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.annotation.NeedLoginLevelEnum;
import com.rainbow.common.dto.token.AuthToken;
import com.rainbow.common.dto.token.HeaderContext;
import com.rainbow.common.dto.token.RB;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.util.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 授权访问解析类
 */
@Component
@Aspect
@Order(-100)
@Slf4j
public class AuthAspect {

    //放行的地址
    private final String[] excludes = new String[] {
    };

    @Pointcut("execution(public * com.rainbow.portal..controller..*(..))")
    public void addAdvice() {
    }

    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 请求路径
        String requestUrl = request.getRequestURI();

        // 记录需要的 header 属性
        initHeaderContext(request);

        // 获取header中的授权信息,cookie区分环境，header默认都是authtoken
        String authTokenStr = request.getHeader("Authorization");
        log.debug("AuthAspect==> authtoken = {}", authTokenStr);

        AuthToken authToken = null;
        if (!StringUtils.isEmpty(authTokenStr) && !authTokenStr.equals("undefined")) {
            // 授权解析异常
            try {
                authToken = EncryptUtils.strDecode(authTokenStr);
            } catch (Exception e) {
                throw new BusinessException(AdminErrorCode.TOKEN_FORMAT_ERROR);
            }

            // 将来可利用该方法做权限控制，比如判断 登录用户是否有权限访问该接口。。。
            initTianxiaoContext(authToken);
        }

        boolean pass = false;
        for (String exclude : excludes) {
            PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            pass = resourceLoader.getPathMatcher().match(exclude, requestUrl);
            if (pass == true) {
                break;
            }
        }

        Class<?> aClass = pjp.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        if (pass == false) {
            NeedLogin needLogin = method.getAnnotation(NeedLogin.class) != null ? method.getAnnotation(NeedLogin.class) : aClass.getAnnotation(NeedLogin.class);
            if (needLogin != null && needLogin.value().equals(NeedLoginLevelEnum.YES)) {
                /**
                 * 接口需要登录
                 */
                if (!isLogin(authToken)) {
                    throw new BusinessException(BaseErrorCode.NO_LOGIN);
                }
            }
        }
        return pjp.proceed();
    }

    /**
     * 初始化TianxiaoContext
     *
     * @param authToken
     */
    private void initTianxiaoContext(AuthToken authToken) {
        RB.setUserId(authToken.getUserId());
        RB.setUserName(authToken.getUserName());
        RB.setMobile(authToken.getMobile());
        RB.setOpenId(authToken.getOpenId());
        RB.setCt(authToken.getCt());
        RB.setSalt(authToken.getSalt());
    }


    private boolean isLogin(AuthToken authToken) {
        if (authToken == null) {
            return false;
        }
        return authToken.getUserId() != null && authToken.getUserId().compareTo(0L) > 0;
    }

    public void initHeaderContext(HttpServletRequest request) {
        HeaderContext.init(request);
    }

}
