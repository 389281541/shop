package com.rainbow.admin.config;

import com.alibaba.fastjson.JSON;
import com.rainbow.admin.security.*;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.common.dto.R;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

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
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())           //登录成功处理
                .failureHandler(authenticationFailureHandler())           //登录失败处理
                .usernameParameter("userName")  //默认的用户名参数
                .passwordParameter("passWord")  //默认的密码参数
                .permitAll();
        httpSecurity
            .headers()                   // 禁用缓存
            .cacheControl();
        httpSecurity
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())             //退出登陆成功处理
            .and()
                .exceptionHandling()            //异常处理
                .accessDeniedHandler(accessDeniedHandler())               //当访问接口没有权限时，自定义返回结果
                .authenticationEntryPoint(authenticationEntryPoint())    //当未登录或者token失效访问接口时，自定义的返回结果
            .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 登陆成功处理器
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

            }
        };
    }

    /**
     * 登陆失败处理器
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

            }
        };
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
      return new LogoutSuccessHandler() {
          @Override
          public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

          }
      }  ;
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(JSON.toJSON(new R(BaseErrorCode.PERMISSION_DENIED)));
                response.getWriter().flush();
            }
        };

    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

            }
        };
    }







}
