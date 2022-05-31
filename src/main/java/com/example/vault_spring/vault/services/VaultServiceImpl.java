package com.example.vault_spring.vault.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.commons.models.ExchangeCourse;
import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.services.CurrencyTransactionService;
import com.example.vault_spring.exchange_course.services.ExchangeCourseInMemoryService;
import com.example.vault_spring.vault.models.VaultData;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Service
@AllArgsConstructor
public class VaultServiceImpl implements VaultService {

    private final CurrencyTransactionService currencyTransactionService;
    private final ExchangeCourseInMemoryService exchangeCourseInMemoryService;

    public void sumTransactions() {
        List<CurrencyTransaction> currencyTransactions = currencyTransactionService.getAll();

        List<VaultData> collect = currencyTransactions.stream()
                // map to VaultData ?
                .map(this::createVaultData)
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @Scheduled(cron = "0/30 * * * * *")
    void testCron() {
        sumTransactions();
    }

    private VaultData createVaultData(final CurrencyTransaction currencyTransaction) {
        final BigDecimal currencySellPrice = getCurrencySellPrice(currencyTransaction.getCurrency());
        final BigDecimal transactionDifference = countTransactionDifference(currencySellPrice,
                currencyTransaction);

        return VaultData.builder()
                .currency(currencyTransaction.getCurrency())
                .currencyAmount(currencyTransaction.getCurrencyAmount())
                .currencyTransactionSum(currencyTransaction.getTransactionSum())
                .currencySellPrice(currencySellPrice)
                .transactionDifference(transactionDifference)
                .build();
    }

    private BigDecimal getCurrencySellPrice(final CurrencyData currency) {
        ExchangeCourse exchangeCourse = exchangeCourseInMemoryService.getExchangeCourse(currency);

        return exchangeCourse.getSellPrice();
    }

    private BigDecimal countTransactionDifference(final BigDecimal currencySellPrice, final CurrencyTransaction currencyTransaction) {
        final BigDecimal currentlySum =
                currencySellPrice.multiply(valueOf(currencyTransaction.getCurrencyAmount()));

        return currentlySum.subtract(currencyTransaction.getTransactionSum());
    }

}
