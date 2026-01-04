package com.example.ecommerce.Test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean("SmsNotificationService")
    NotificationService notificationService(){
        return new SmsNotificationService();
    }

}
