package com.rainbow.admin.security;

import com.rainbow.admin.module.TokenModel;
import com.rainbow.admin.util.JwtManager;
import com.rainbow.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登陆授权过滤器
 *
 * @author lujinwei
 * @since 2019-10-24
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String authHeader = request.getHeader(this.tokenHeader);
//        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
//            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
//            String username = jwtTokenUtil.getUserNameFromToken(authToken);
//            LOGGER.info("checking username:{}", username);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    LOGGER.info("authenticated user:{}", username);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
        String authToken = request.getHeader(Constant.AUTH_TOKEN_NAME);
        if(authToken != null) {
            TokenModel tokenModel = jwtManager.getTokenModel(authToken);
            if(tokenModel != null) {
                String username = tokenModel.getUserName();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            }
        }
        chain.doFilter(request, response);
    }

}
