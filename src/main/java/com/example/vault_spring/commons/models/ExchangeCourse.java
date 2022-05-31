package com.example.vault_spring.commons.models;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;

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

    public static ExchangeCourse prototype() {
        return ExchangeCourse.builder()
                .currency(CurrencyData.prototype())
                .buyPrice(ONE)
                .sellPrice(TEN)
                .build();
    }
}
