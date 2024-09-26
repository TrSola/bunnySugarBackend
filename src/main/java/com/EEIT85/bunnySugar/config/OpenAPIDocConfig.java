package com.EEIT85.bunnySugar.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition()
public class OpenAPIDocConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        OpenAPI openApi = new OpenAPI();
        Components components = new Components();
        Info info = new Info();
        info.title("BunnySugar API 文件").version("V.2024092401").description("test");
        openApi
                .info(info)
                .components(components);
        return openApi;
    }
}