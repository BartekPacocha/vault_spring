package com.example.vault_spring.scraper.services;

import com.example.vault_spring.commons.models.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CurrencyScraperServiceImplTest {

    private CurrencyScraperService currencyScraperService;

    @BeforeEach
    void setUp() {
        currencyScraperService = new CurrencyScraperServiceImpl();
    }

    @Test
    public void shouldGetAllCurrenciesWhenGetAllCall() {
        // when
        final List<Currency> currencies = currencyScraperService.getAll();

        // then
        assertFalse(currencies.isEmpty());
    }
}