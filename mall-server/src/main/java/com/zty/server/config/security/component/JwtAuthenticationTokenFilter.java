package com.zty.server.config.security.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt登录授权过滤器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * jwt负载拿到的开头
     */
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    /**
     * jwt存在请求头
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取请求头：Bearer
        String authHeader = request.getHeader(tokenHeader);
        //判断请求头是否为空与是以tokenHead：Bearer开头
        if (null != authHeader && authHeader.startsWith(tokenHead)) {
            //截取token中的tokenHead：Bearer请求头
            String authToken = authHeader.substring(tokenHead.length());
            //获取token里面的用户名
            String username = jwtTokenUtil.getUserNameFromToken(authToken);//获取以截取请求头的token
            //token存在用户名但未登录 没在spring security全局对面里面
            if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
                //登录 UserDetails来存储用户信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //验证token是否有效，获取UserDetails重新设置回Spring Security全局对象里面
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //将传入过来的用户信息重新设置
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}
