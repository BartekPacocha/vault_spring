package com.example.vault_spring.exchange_course.models;

import com.example.vault_spring.commons.models.CurrencyData;
import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Embeddable
public class ExchangeCourse {

    private CurrencyData currency;
    private LocalDate date;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;

    public static ExchangeCourseEntity toEntity(final ExchangeCourse exchangeCourse) {
        return ExchangeCourseEntity.builder()
                .buyPrice(exchangeCourse.buyPrice)
                .date(exchangeCourse.date)
                .sellPrice(exchangeCourse.sellPrice)
                .currency(exchangeCourse.currency)
                .build();
    }

}
