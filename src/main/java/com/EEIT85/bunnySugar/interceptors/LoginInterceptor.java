package com.EEIT85.bunnySugar.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginInterceptor(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor: preHandle executed");

        // 允许所有 OPTIONS 请求
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true; // 放行 OPTIONS 请求
        }

        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/user/login") && request.getMethod().equalsIgnoreCase("POST")) {
            return true; // 放行，继续处理请求
        }
        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/user/registerVerify") && request.getMethod().equalsIgnoreCase("POST")) {
            return true; // 放行，继续处理请求
        }
        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/user/verify") && request.getMethod().equalsIgnoreCase("POST")) {
            return true; // 放行，继续处理请求
        }
        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/user/completeDetails") && request.getMethod().equalsIgnoreCase("POST")) {
            return true; // 放行，继续处理请求
        }

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        token = token.substring(7); // 去掉Bearer前綴

        try {
            Map<String, Object> claims = jwtUtil.parseJwtToken(token);
            String userAccount = claims.get("account").toString();

            // 直接创建一个未授权的认证信息
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userAccount, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication); // 设置认证信息

            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            e.printStackTrace();
            System.out.println("JWT parsing failed: " + e.getMessage());
            return false;
        }
    }
}
