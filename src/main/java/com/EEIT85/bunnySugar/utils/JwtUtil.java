package com.EEIT85.bunnySugar.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = System.getenv("JWT_SECRET_KEY"); // 从环境变量中读取密钥

    // 生成token，接收参数并返回
    public static String genToken(Map<String, Object> claims) {
        try {
            return JWT.create()
                    .withClaim("claims", claims)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                    .sign(Algorithm.HMAC256(KEY));
        } catch (Exception e) {
            // 处理生成 token 过程中可能出现的异常
            throw new RuntimeException("Error generating token", e);
        }
    }

    // 解析token，验证token，并返回数据
    public static Map<String, Object> parseToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(KEY))
                    .build()
                    .verify(token);
            return decodedJWT.getClaim("claims").asMap();
        } catch (JWTVerificationException e) {
            // 处理解析 token 过程中可能出现的异常
            throw new RuntimeException("Invalid token", e);
        }
    }
}
