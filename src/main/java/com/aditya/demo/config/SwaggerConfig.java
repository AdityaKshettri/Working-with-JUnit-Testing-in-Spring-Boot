package com.aditya.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket todoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST-APIs")
                .apiInfo(apiInfo())
                .select()
                .paths(postPaths()::test)
                .build();
    }

    private Predicate<String> postPaths() {
        return regex("/users.*")::apply;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Rest APIs")
                .description("API reference for User Service")
                .contact(new Contact("Aditya Kshettri", "https://github.com/AdityaKshettri", "adikshettri1623@gmail.com"))
                .version("1.0")
                .build();
    }
}
