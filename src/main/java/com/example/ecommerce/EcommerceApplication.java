package com.example.ecommerce;

import com.example.ecommerce.Test.EmailNotificationService;
import com.example.ecommerce.Test.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private Map<String , NotificationService> notificationServiceMap;

    public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
