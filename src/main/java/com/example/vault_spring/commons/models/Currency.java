package com.example.vault_spring.commons.models;

import com.example.vault_spring.commons.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

import static com.example.vault_spring.commons.enums.CurrencyType.EUR;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class Currency {
    private final CurrencyType currencyType;
    private final String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;

    public static Currency prototype() {
        return builder()
                .currencyType(EUR)
                .name("Euro")
                .buyPrice(ONE)
                .sellPrice(TEN)
                .build();
    }
}
