package com.example.vault_spring.transactions.models;

import com.example.vault_spring.commons.models.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyTransactionCreateForm {

    private String transactionDate;

    private Double currencyAmount;

    private BigDecimal currencyBuyPrice;

    private String currency;
}
