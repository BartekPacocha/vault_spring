package com.example.vault_spring;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.scraper.services.CurrencyScraperService;
import com.example.vault_spring.scraper.services.CurrencyScraperServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class VaultSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultSpringApplication.class, args);
	}

}
