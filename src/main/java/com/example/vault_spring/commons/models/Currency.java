package com.example.vault_spring.commons.models;

import com.example.vault_spring.commons.enums.CurrencyType;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static com.example.vault_spring.commons.enums.CurrencyType.EUR;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Embeddable
public class Currency {

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private String name;

    public static Currency prototype() {
        return builder()
                .currencyType(EUR)
                .name("Euro")
                .build();
    }
}
