package com.example.vault_spring.vault.models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class VaultDataSumForm {

    private Double currencyAmountSum;
    private BigDecimal currencyTransactionSum;
    private BigDecimal transactionDifferenceSum;

}
