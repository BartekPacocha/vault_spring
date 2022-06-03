package com.example.vault_spring.exchange_course.forms;

import com.example.vault_spring.commons.models.CurrencyData;
import lombok.Builder;

@Builder(toBuilder = true)
public class ExchangeCourseSearchForm {

    private CurrencyData currencyData;

}
