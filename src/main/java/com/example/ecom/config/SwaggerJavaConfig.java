package com.example.ecom.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerJavaConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application-description}") String appDescription,
            @Value("${application-version}") String appVersion
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title("User Tracking API")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public OperationCustomizer appDescription() {
        return (operation, handlerMethod) -> operation.addParametersItem(
                new Parameter()
                        .in("header")
                        .required(false)
                        .description("Enter language code")
                        .name("accept-language"));
    }
}
