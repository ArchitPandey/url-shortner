package com.example.urlshortner.integrations.idgeneratorservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IdGeneratorRestClientConfig {

    @Bean("idGeneratorRestTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
