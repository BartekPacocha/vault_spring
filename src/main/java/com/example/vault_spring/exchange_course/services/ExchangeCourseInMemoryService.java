package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.commons.models.ExchangeCourse;

public interface ExchangeCourseInMemoryService {

    void subscribeExchangeCourse(final ExchangeCourse exchangeCourse);
    ExchangeCourse getExchangeCourse(final CurrencyData currency);

}
