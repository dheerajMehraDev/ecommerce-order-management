package com.example.ecommerce.Test;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class SmsNotificationService implements NotificationService{
    @Override
    public void send() {
        System.out.println("SMS sending ");
    }
}
