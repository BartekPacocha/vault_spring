package com.example.vault_spring.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CurrencyType {

    EUR(1),
    USD(2),
    GPB(3),
    HUF(11);

    private Integer rowNumber;

    public static CurrencyType typeOf(final String type) {
        for (CurrencyType currencyType : CurrencyType.values()) {
            if (currencyType.toString().equals(type)) {
                return currencyType;
            }
        }
        return null;
    }

}
