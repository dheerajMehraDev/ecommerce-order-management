package com.example.ecommerce.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestClient;

@Configuration
@EnableJpaAuditing
public class AppConfig {
    @Bean
    RestClient getRestClinet(){
        return RestClient.builder().build();
    }
}

