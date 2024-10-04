package com.EEIT85.bunnySugar.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

        // 把JWT設置進token做解析
        String token = extractToken(request);
        if (token != null && jwtUtil.isTokenValid(token)) {
            Map<String, Object> claims = jwtUtil.parseJwtToken(token);
            if (claims != null) {
                // 解析成功，設置用戶認證資訊
                List<SimpleGrantedAuthority> authorities = getAuthoritiesFromClaims(claims);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(claims.get("account"), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // 將解析出的 `claims` 存入 `request` 屬性中
                request.setAttribute("claims", claims);

//                log.info("Token 驗證成功，設置認證上下文 | authenticationToken: {}", authenticationToken);
            } else {
                // 解析失敗，清除上下文
//                log.info("Token 驗證失敗，清除上下文 | claims: {}", claims);
                SecurityContextHolder.clearContext();
            }
        } else {
            // 如果 token 缺失或無效，清除上下文
//            log.info("Token 驗證失敗，清除上下文 | Token 缺失或無效");
            SecurityContextHolder.clearContext();
        }

        // 繼續執行後續的過濾鏈
        filterChain.doFilter(request, response);
    }

    // 從請求中提取 JWT
    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // 移除 "Bearer " 前綴
        }
        return null;
    }

    // 這裡可以擴展權限解析邏輯，目前返回空權限列表
    private List<SimpleGrantedAuthority> getAuthoritiesFromClaims(Map<String, Object> claims) {
        Long userId = Long.valueOf(claims.get("id").toString());
        List<SimpleGrantedAuthority> authorities;

        if (userId <= 13) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")); // 分配管理者角色
            log.info("用戶 {} 是管理者", claims.get("account")); //
            System.out.println("userId = " + userId);
        } else {
            // return空列表，只要確定用戶不是匿名就好
            return Collections.emptyList();
        }

        return authorities;
    }
}
