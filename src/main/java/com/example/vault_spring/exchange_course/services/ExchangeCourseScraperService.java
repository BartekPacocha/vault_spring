package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;

import java.util.List;

public interface ExchangeCourseScraperService {

    ExchangeCourse getExchangeCourse(final CurrencyType currencyType);
    List<ExchangeCourse> downloadAll();

}
