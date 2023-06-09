package com.optimagrowth.license.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@ConfigurationProperties(prefix = "example") // will search in Spring Config server for the properties begin with 'example'
@Configuration
public class ServiceConfig {
    private String property; // then append attribure to the property prefix 'example.property'
}
