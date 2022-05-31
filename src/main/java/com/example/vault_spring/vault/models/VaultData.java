package com.example.vault_spring.vault.models;

import com.example.vault_spring.commons.models.CurrencyData;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class VaultData {

    private CurrencyData currency;
    private Double currencyAmount;
    private BigDecimal currencyTransactionSum;
    private BigDecimal currencySellPrice;
    private BigDecimal transactionDifference;

}
