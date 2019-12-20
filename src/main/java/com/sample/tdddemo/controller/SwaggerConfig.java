package com.sample.tdddemo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;

@Configuration
@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.tdddemo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    //We can exclude the apiInfo part in the Docket
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "TDD Demo REST API",
                "Demo REST API built on Spring boot",
                "API 1.0",
                "Terms of service - Use it only for a demo, the app is not intended to create any useful feature",
                new Contact("Developer", "www.example.com", "xyz@welcome.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
