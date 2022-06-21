package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.forms.ExchangeCourseSearchForm;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;

import java.util.List;

public interface ExchangeCourseService {
    void save(ExchangeCourse exchangeCourse);
    void saveAll(List<ExchangeCourse> exchangeCourses);
    List<ExchangeCourse> getLastCourses();
    ExchangeCourse getLastCurrencyCourse(CurrencyData currency);
    List<ExchangeCourse> getAllCourses();
    List<ExchangeCourse> searchCourses(ExchangeCourseSearchForm searchForm);

    void refreshExchangeCourses();
}
