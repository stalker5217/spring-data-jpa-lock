package com.example.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringDataJpaLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaLockApplication.class, args);
	}

}
