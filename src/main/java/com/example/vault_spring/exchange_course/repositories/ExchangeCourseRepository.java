package com.example.vault_spring.exchange_course.repositories;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.models.ExchangeCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeCourseRepository extends JpaRepository<ExchangeCourseEntity, Long> {

    @Query("SELECT ec FROM exchange_course ec WHERE date = (SELECT MAX(date) from exchange_course ec2)")
    List<ExchangeCourseEntity> getLastCourses();

    @Query("SELECT ec FROM exchange_course ec WHERE date = (SELECT MAX(date) from exchange_course ec2) " +
            "and ec.currency = ?1")
    ExchangeCourseEntity getLastCurrencyCourse(final CurrencyData currencyData);

    @Query("SELECT MAX(date) from exchange_course")
    LocalDate getLatestDate();
}
