package com.example.vault_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class VaultSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaultSpringApplication.class, args);
    }

}
