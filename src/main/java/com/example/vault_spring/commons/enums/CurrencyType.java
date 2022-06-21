package com.example.vault_spring.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
// TODO: change to lookup table to be able to add new currency
public enum CurrencyType {

    EUR(1, "Euro"),
    USD(2, "Dolar amerykański"),
    GPB(3, "Funt brytyjski"),
    HUF(11, "Forint węgierski");

    private Integer rowNumber;
    private String name;

    public static CurrencyType typeOf(final String type) {
        for (CurrencyType currencyType : CurrencyType.values()) {
            if (currencyType.toString().equals(type)) {
                return currencyType;
            }
        }
        return null;
    }

}
