package com.example.vault_spring.vault.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.services.CurrencyTransactionService;
import com.example.vault_spring.exchange_course.services.ExchangeCourseService;
import com.example.vault_spring.vault.models.VaultData;
import com.example.vault_spring.vault.models.VaultDataSumForm;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.vault_spring.commons.CronConstants.CRON_EVERYDAY_AT_16_AM;
import static java.math.BigDecimal.valueOf;

@Service
@AllArgsConstructor
public class VaultServiceImpl implements VaultService {

    private final CurrencyTransactionService currencyTransactionService;
    private final ExchangeCourseService exchangeCourseService;

    @Override
    public List<VaultData> getVaultDataWithFilter(final List<CurrencyType> currencyData) {
        return getAllVaultData().stream()
                .filter(e -> currencyData.contains(e.getCurrency().getCurrencyType()))
                .collect(Collectors.toList());
    }

    @Override
    public VaultDataSumForm getVaultDataSum(final List<VaultData> vaultData) {
        final BigDecimal transactionDifferenceSum = vaultData.stream()
                .map(VaultData::getTransactionDifference)
                .reduce(valueOf(0), BigDecimal::add);

        final BigDecimal currencyTransactionSum = vaultData.stream()
                .map(VaultData::getCurrencyTransactionSum)
                .reduce(valueOf(0), BigDecimal::add);

        final Double currencyAmountSum = vaultData.stream()
                .map(VaultData::getCurrencyAmount)
                .reduce(0d, Double::sum);


        return VaultDataSumForm.builder()
                .currencyAmountSum(currencyAmountSum)
                .transactionDifferenceSum(transactionDifferenceSum)
                .currencyTransactionSum(currencyTransactionSum)
                .build();
    }

    @Scheduled(cron = CRON_EVERYDAY_AT_16_AM)
    void sendEmailReport() {
        // TODO List<VaultData> allVaultData = getAllVaultData();
    }

    private List<VaultData> getAllVaultData() {
        return currencyTransactionService.getAll().stream()
                // map to VaultData ?
                // filter predicate
                .map(this::createVaultData)
                .collect(Collectors.toList());
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
                .buyPrice(currencyTransaction.getCurrencyBuyPrice())
                .build();
    }

    private BigDecimal getCurrencySellPrice(final CurrencyData currency) {
        final var lastCurrencyCourse = exchangeCourseService.getLastCurrencyCourse(currency);

        return lastCurrencyCourse.getSellPrice();
    }

    private BigDecimal countTransactionDifference(final BigDecimal currencySellPrice, final CurrencyTransaction currencyTransaction) {
        final BigDecimal currentlySum =
                currencySellPrice.multiply(valueOf(currencyTransaction.getCurrencyAmount()));

        return currentlySum.subtract(currencyTransaction.getTransactionSum());
    }

}
