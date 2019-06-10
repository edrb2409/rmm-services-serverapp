package com.example.rmmservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket rmmDevicesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("device-api")
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rmmservices.controller.device"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public Docket rmmBillingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("billing-api")
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rmmservices.controller.billing"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public Docket rmmServicesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("services-api")
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rmmservices.controller.customerservice"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RMM Services REST API")
                .version("1.0.0")
                .build();
    }

}
