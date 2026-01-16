package com.example.ImageConversion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI imageConversionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Image Conversion API")
                        .description("API для конвертации изображений в ASCII арт")
                        .version("1.0.0"));
                       /* .contact(new Contact()
                                .name("Image Conversion Team")
                                .email("support@example.com")));*/
    }
}
