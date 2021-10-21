package com.edu.framework.atividadefisica.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateFactory {
    
    @Bean
	public RestTemplate getRestTemplate() {
	   return new RestTemplate();
	}

}
