package com.example.vault_spring.currency_transactions.models;

import com.example.vault_spring.commons.models.CurrencyData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder(toBuilder=true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private LocalDate transactionDate;

    @Column
    private Double currencyAmount;

    @Column
    private BigDecimal currencyBuyPrice;

    @Column
    @Embedded
    private CurrencyData currency;

    @Column
    private BigDecimal transactionSum;
}
