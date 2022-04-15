package com.example.vault_spring.scraper.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.ExchangeCourse;

import java.util.List;

public interface ExchangeCourseScraperService {

    ExchangeCourse getExchangeCourse(final CurrencyType currencyType);

    List<ExchangeCourse> getAll();

}
