package com.EEIT85.bunnySugar.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityWhiteList {

    // 定義白名單路徑，可以根據需要擴展更多路徑
    private static final List<String> WHITELIST_PATHS = List.of(
            "/user/login",
            "/user/registerVerify",
            "/user/verify",
            "/user/completeDetails",
            "/user/googleLogin",
            "/api/products/**"
    );

    // 提供方法來返回白名單路徑（作為 List）
    public List<String> getWhitelistPaths() {
        return WHITELIST_PATHS;
    }

    // 提供方法來返回白名單路徑（作為 String[]）
    public String[] getWhitelistPathsAsArray() {
        return WHITELIST_PATHS.toArray(new String[0]);
    }
}
