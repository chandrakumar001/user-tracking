package com.example.ecom.hardware.validator;

import com.example.ecom.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "hardware.attribute")
@Setter
@Getter
@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = "classpath:hardware.yml",
        ignoreResourceNotFound = true
)
public class HardwareProperties {

    private Map<String, String> hardwareInfo = new HashMap<>();
}
