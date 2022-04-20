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
    USD(2, "Dolar"),
    GPB(3, "Funt Brytyjski"),
    HUF(11, "Forint");

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
