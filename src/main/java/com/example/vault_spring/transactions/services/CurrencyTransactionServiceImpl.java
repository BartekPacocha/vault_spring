package com.example.vault_spring.transactions.services;

import com.example.vault_spring.transactions.models.CurrencyTransaction;
import com.example.vault_spring.transactions.repositories.CurrencyTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CurrencyTransactionServiceImpl implements CurrencyTransactionService {

    private final CurrencyTransactionRepository repository;

    @Override
    public List<CurrencyTransaction> getAll() {
        return repository.findAll();
    }

    @Override
    public CurrencyTransaction save(CurrencyTransaction currencyTransaction) {

        CurrencyTransaction transaction = currencyTransaction.toBuilder()
                .transactionSum(countTransactionSum(currencyTransaction))
                .build();

        return repository.save(transaction);
    }

    private BigDecimal countTransactionSum(final CurrencyTransaction currencyTransaction) {
        final Double currencyAmount = currencyTransaction.getCurrencyAmount();
        final BigDecimal currencyBuyPrice = currencyTransaction.getCurrencyBuyPrice();

        return new BigDecimal(currencyAmount).multiply(currencyBuyPrice);
    }
}
