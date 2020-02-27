package com.rainbow.admin.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.api.entity.Administrator;
import com.rainbow.admin.api.entity.Permission;
import com.rainbow.admin.model.AdminUserDetails;
import com.rainbow.admin.model.JwtAuthenticationTokenFilter;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.common.dto.R;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * spring security配置
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdministratorService administratorService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()             //开启跨域
            .and()
                .csrf().disable()   //由于使用的是JWT，我们这里不需要csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 基于token，所以不需要session
            .and()
                .authorizeRequests()    //允许匿名访问的url
                .antMatchers("/administrator/login","/administrator/register")
                .permitAll()
                .antMatchers("/kaptcha/get","/kaptcha/verify")       //验证码
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers("/**") //测试时全部运行访问
                .permitAll()
                .anyRequest()                   // 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        httpSecurity
            .headers()                          // 禁用缓存
            .cacheControl();
        httpSecurity
                .exceptionHandling()            //异常处理
                .accessDeniedHandler(accessDeniedHandler())               //当访问接口没有权限时，自定义返回结果
                .authenticationEntryPoint(authenticationEntryPoint());    //当未登录或者token失效访问接口时，自定义的返回结果
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userName -> {
            LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Administrator::getUserName, userName);
            //获取用户信息
            Administrator administrator = administratorService.getOne(queryWrapper);
            //用户不存在
            if (administrator == null) {
                throw new BusinessException(BaseErrorCode.NO_USER);
            }
            //获取权限
            List<Permission> permissionList = administratorService.getPermissionByUserId(administrator.getId());
            return new AdminUserDetails(administrator,permissionList);
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


    /**
     * 无访问权限处理
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) -> {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(JSON.toJSON(new R(BaseErrorCode.PERMISSION_DENIED)));
                response.getWriter().flush();
        };
    }


    /**
     * 未登录处理器
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException e) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSON.toJSON(new R(BaseErrorCode.NO_LOGIN)));
            response.getWriter().flush();
        };
    }


}
