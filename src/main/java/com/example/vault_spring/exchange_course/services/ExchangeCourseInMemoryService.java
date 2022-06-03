package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;

import java.util.Optional;

public interface ExchangeCourseInMemoryService {

    void subscribeExchangeCourse(final ExchangeCourse exchangeCourse);
    Optional<ExchangeCourse> getExchangeCourse(final CurrencyData currency);

}
