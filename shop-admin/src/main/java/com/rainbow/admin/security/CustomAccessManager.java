package com.rainbow.admin.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessManager implements AccessDecisionManager {


    /**
     * 判定是否拥有权限的决策方法
     * @param authentication CustomUserDetailsService类loadUserByUsername()方法中返回值
     * @param o 包含客户端发起的请求的request信息。
     * @param collection CustomFilterInvocationSecurityMetadataSource类的getAttribute()方法返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
