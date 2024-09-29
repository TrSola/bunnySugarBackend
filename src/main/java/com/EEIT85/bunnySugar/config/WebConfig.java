package com.EEIT85.bunnySugar.config;

import com.EEIT85.bunnySugar.interceptors.LoginInterceptor; // 引入 LoginInterceptor
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor; // 宣告 LoginInterceptor

    @Autowired
    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor; // 通過建構子注入 LoginInterceptor
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 設定應用於所有路徑
                .allowedOrigins("http://localhost:5173", "http://localhost:5174") // 允許的前端 URL，根據實際情況修改
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的方法
                .allowedHeaders("*") // 允許的頭部信息
                .allowCredentials(true); // 是否允許憑證
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 註冊 LoginInterceptor
                .addPathPatterns("/api/**")
                .excludePathPatterns("/user/login", "user/registerVerify", "user/verify", "user/completeDetails"); // 可選：排除不需要攔截的路徑，例如登錄和註冊
    }
}
