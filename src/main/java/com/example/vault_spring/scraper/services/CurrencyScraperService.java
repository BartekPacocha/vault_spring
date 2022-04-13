package com.example.vault_spring.scraper.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyScraperService {

    Currency getCurrency(final CurrencyType currencyType);

    List<Currency> getAll();

}
