package com.rainbow.admin.config;

import com.rainbow.admin.module.JwtAuthenticationTokenFilter;
import com.rainbow.admin.module.RestAuthenticationEntryPoint;
import com.rainbow.admin.module.RestfulAccessDeniedHandler;
import com.rainbow.admin.module.RestfulAuthenticationEntryPoint;
import com.rainbow.admin.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

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
                .successHandler()           //登录成功处理
                .failureHandler()           //登录失败处理
                .authenticationDetailsSource()     //自定义验证逻辑，增加验证码信息
                .usernameParameter("userName")  //默认的用户名参数
                .passwordParameter("passWord")  //默认的密码参数
                .permitAll()
            .and()
                .headers()
                .cacheControl()
            .and()
                .logout()
                .logoutSuccessHandler()         //
            .and()
                .exceptionHandling()            //异常处理
                .accessDeniedHandler()          //当访问接口没有权限时，自定义返回结果
                .authenticationEntryPoint()    //当未登录或者token失效访问接口时，自定义的返回结果
            .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }







}
