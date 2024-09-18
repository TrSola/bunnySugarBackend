package com.EEIT85.bunnySugar.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 設定應用於所有路徑
                .allowedOrigins("http://localhost:5173") // 允許的前端 URL，根據實際情況修改
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的方法
                .allowedHeaders("*") // 允許的頭部信息
                .allowCredentials(true); // 是否允許憑證
    }

}

