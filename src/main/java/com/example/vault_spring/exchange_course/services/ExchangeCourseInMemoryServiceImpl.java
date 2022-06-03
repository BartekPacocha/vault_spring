package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.vault_spring.commons.CronConstants.CRON_EVERYDAY_AT_12_AM;

@Service
@AllArgsConstructor
public class ExchangeCourseInMemoryServiceImpl implements ExchangeCourseInMemoryService {

    private final ExchangeCourseScraperService exchangeCourseScraperService;

    private final Map<String, ExchangeCourse> exchangeCourseMap = new HashMap<>();

    public void subscribeExchangeCourse(final ExchangeCourse exchangeCourse) {
        String currencyTypeName = exchangeCourse.getCurrency().getCurrencyType().getName();

        exchangeCourseMap.put(currencyTypeName, exchangeCourse);
    }

    public Optional<ExchangeCourse> getExchangeCourse(final CurrencyData currency) {
        String currencyTypeName = currency.getCurrencyType().getName();

        if (exchangeCourseMap.isEmpty()) {
            subscribeExchangeCourses();
        }

        return Optional.of(exchangeCourseMap.get(currencyTypeName));
    }

    @Scheduled(cron = CRON_EVERYDAY_AT_12_AM)
    public void getAndSaveExchangeCourses() {
        subscribeExchangeCourses();
    }

    private void subscribeExchangeCourses() {
        List<ExchangeCourse> exchangeCourses = exchangeCourseScraperService.downloadAll();

        exchangeCourses.forEach(this::subscribeExchangeCourse);
    }

}
