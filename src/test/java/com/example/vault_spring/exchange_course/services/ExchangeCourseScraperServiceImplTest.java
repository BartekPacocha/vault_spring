package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.ExchangeCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ExchangeCourseScraperServiceImplTest {

    private ExchangeCourseScraperService exchangeCourseScraperService;

    @BeforeEach
    void setUp() {
        // exchangeCourseScraperService = new ExchangeCourseScraperServiceImpl();
    }

    @Test
    public void shouldGetAllCurrenciesWhenGetAllCall() {
        // when
        final List<ExchangeCourse> exchangeCourses = exchangeCourseScraperService.getAll();

        // then
        assertFalse(exchangeCourses.isEmpty());
    }
}