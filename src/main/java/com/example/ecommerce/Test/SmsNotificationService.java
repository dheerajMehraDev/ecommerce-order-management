package com.example.ecommerce.Test;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@ConditionalOnProperty(name = "notification.type" , havingValue = "sms")
public class SmsNotificationService implements NotificationService{
    @Override
    public void send() {
        System.out.println("SMS sending ");
    }
}
