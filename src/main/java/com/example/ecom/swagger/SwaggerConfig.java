package com.example.ecom.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-api").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ecom"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("onTLL API").description("onTLL API reference for developers")
                // .termsOfServiceUrl("http://javainuse.com")
                // .contact("javainuse@gmail.com").license("JavaInUse License")
                .licenseUrl("ibm.ovanan@daimler.com").version("1.0").build();
    }
}