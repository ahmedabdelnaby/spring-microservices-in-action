package com.optimagrowth.license.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@ConfigurationProperties(prefix = "example") // will search in Spring Config server for the properties begin with 'example'
@Component
public class ServiceConfig {
    private String property; // then append attribure to the property prefix 'example.property'
}
