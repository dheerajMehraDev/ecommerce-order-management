package com.example.ecommerce;

import com.example.ecommerce.Test.EmailNotificationService;
import com.example.ecommerce.Test.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("EmailNotificationService")
	private NotificationService notificationService ;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("running");
		notificationService.send();
	}
}
