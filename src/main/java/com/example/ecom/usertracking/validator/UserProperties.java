package com.example.ecom.usertracking.validator;

import com.example.ecom.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "user.attribute")
@Setter
@Getter
@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = "classpath:user.yml",
        ignoreResourceNotFound = true
)
public class UserProperties {

    private Map<String, String> contactInfo = new HashMap<>();
}
