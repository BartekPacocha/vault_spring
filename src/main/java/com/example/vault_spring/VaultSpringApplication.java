package com.example.vault_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaultSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultSpringApplication.class, args);
	}

}