package com.openbootcamp.restdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuración Swagger para la generación de documentación de la API REST
 * HTML: http://localhost:8080/swagger-ui/
 * JSON: http://localhost:8080/v2/api-docs
 */
@Configuration
public class SwaggerConfig {

//    @Bean
//    public InternalResourceViewResolver defaultViewResolver() { return new InternalResourceViewResolver();}

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot API REST Laptop Inventary",
                "Laptop Inventory API REST docs",
                "1.0",
                "http://www.google.com",
                new Contact("Silvia", "https://github.com/fortythreesunsets", "ruvalcaba.s@outlook.com"),
                "MIT",
                "https://mit-license.org/",
                Collections.emptyList());
    }
}
