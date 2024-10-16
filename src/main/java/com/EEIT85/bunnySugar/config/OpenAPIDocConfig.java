package com.EEIT85.bunnySugar.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "My API",
                version = "1.0",
                description = "This is a sample API with Bearer Token authentication",
                termsOfService = "https://example.com/terms",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "API Support",
                        email = "support@example.com",
                        url = "https://example.com/support"
                ),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth") // 使用 bearer token 的安全需求
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT" // 声明使用 JWT 格式
)
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