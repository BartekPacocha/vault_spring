package com.example.vault_spring.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyType {

    EUR(1),
    USD(2),
    GPB(3),
    HUF(11);

    private final Integer rowNumber;

    public static CurrencyType typeOf(final String type) {
        for (CurrencyType currencyType : CurrencyType.values()) {
            if (currencyType.toString().equals(type)) {
                return currencyType;
            }
        }
        return null;
    }

}
