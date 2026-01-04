package com.example.ecommerce.Test;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("EmailNotificationService")
@Primary
public class EmailNotificationService implements NotificationService{
    @Override
    public void send() {
        System.out.println("sending email");
    }

    @PostConstruct
    public void afterInit(){
        System.out.println("initilized bean of notification service");
    }

    @PreDestroy
    public void beforeDestroy(){
        System.out.println("destroying the bean of notification service");
    }
}
