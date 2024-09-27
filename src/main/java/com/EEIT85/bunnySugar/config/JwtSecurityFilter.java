package com.EEIT85.bunnySugar.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;
import com.EEIT85.bunnySugar.utils.JwtUtil;

@Component
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/user/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果是 OPTIONS 请求，直接放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);
        if (token != null && jwtUtil.isTokenValid(token)) {
            Map<String, Object> claims = jwtUtil.parseJwtToken(token);
            if (claims != null) {
                List<SimpleGrantedAuthority> authorities = getAuthoritiesFromClaims(claims);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(claims.get("account"), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("Token authentication successful, setting context | authenticationToken: {}", authenticationToken);
            } else {
                log.info("Token authentication failed, clearing context | claims: {}", claims);
                SecurityContextHolder.clearContext();
            }
        } else {
            log.info("Token authentication failed, clearing context | Token is missing or invalid");
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }

    private List<SimpleGrantedAuthority> getAuthoritiesFromClaims(Map<String, Object> claims) {

        return Collections.emptyList();
    }

}
