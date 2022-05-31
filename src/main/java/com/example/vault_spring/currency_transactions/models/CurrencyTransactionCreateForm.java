package com.example.vault_spring.currency_transactions.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyTransactionCreateForm {

    private String transactionDate;

    private Double currencyAmount;

    private BigDecimal currencyBuyPrice;

    private String currency;
}
