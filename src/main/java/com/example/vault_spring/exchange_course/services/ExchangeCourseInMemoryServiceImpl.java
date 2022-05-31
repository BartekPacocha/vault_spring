package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.commons.models.ExchangeCourse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeCourseInMemoryServiceImpl implements ExchangeCourseInMemoryService {

    private final Map<String, ExchangeCourse> exchangeCourseMap = new HashMap<>();

    public void subscribeExchangeCourse(final ExchangeCourse exchangeCourse) {
        String currencyTypeName = exchangeCourse.getCurrency().getCurrencyType().getName();

        exchangeCourseMap.put(currencyTypeName, exchangeCourse);
    }

    public ExchangeCourse getExchangeCourse(final CurrencyData currency) {
        String currencyTypeName = currency.getCurrencyType().getName();

        return exchangeCourseMap.get(currencyTypeName);
    }

}
