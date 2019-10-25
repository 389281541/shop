package com.rainbow.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collection;

/**
 *
 *
 * @author lujinwei
 * @since 2019-10-25
 */
@Service
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    @Override
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return filterInvocationSecurityMetadataSource();
    }

    /**
     * 访问权限管理器
     *
     * @author lujinwei
     * @since 2019-10-25
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManager() {

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
        };
    }

    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        return new FilterInvocationSecurityMetadataSource() {
            /**
             * 判定用户请求的url是否在权限表中，如果在权限表中，则返回给CustomAccessDecisionManager类的decide方法，用来判定用户是否有此权限。
             * 如果不在则返回null，跳过角色管理(decide方法)，直接访问。
             * 当然也可以在decide方法中判断该请求是否需要权限判定。
             *
             * 如果我们只有极个别的请求不需要鉴权，就不需要去查permission表了。如下所示
             * @param o 从该参数中能获取到请求的url，request对象
             * @return null 跳过decide方法
             * @throws IllegalArgumentException
             */
            @Override
            public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

                return null;
            }

            @Override
            public Collection<ConfigAttribute> getAllConfigAttributes() {
                return null;
            }

            @Override
            public boolean supports(Class<?> aClass) {
                return true;
            }

        };
    }


}
