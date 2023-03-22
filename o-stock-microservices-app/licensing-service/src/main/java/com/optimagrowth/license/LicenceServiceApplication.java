package com.optimagrowth.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

@SpringBootApplication
@RefreshScope // refresh the custom properties from Spring Config server (not the built-in properties like DB properties)
@EnableDiscoveryClient		// activates the Eureka Discovery Client
@EnableFeignClients		// this annotation is needed to use the Feign client
public class LicenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenceServiceApplication.class, args);
	}

	// Adding internationalization
	@Bean
	public LocaleResolver localeResolver() {

		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true); // if message is not found, return its code instead of throwing an exception
		/**
		 * - sets the base name of the languages properties file
		 * - ex: if we were in Italy, we would use the Locale.IT, and would have a file called messages_it.properties
		 * - see, the base message file will be 'messages', and for each locale file, we will append the local abbreviation
		 * to the file name, ex: messages_it.properties
		 * - in case we don't find a message in a specific locale message file, the message source will search on the default
		 * message file messages.properties
		 */
		messageSource.setBasename("messages"); // sets the base name of the languages properties file

		return messageSource;
	}

	@LoadBalanced		// gets a list of all the instances for the Organization services
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
