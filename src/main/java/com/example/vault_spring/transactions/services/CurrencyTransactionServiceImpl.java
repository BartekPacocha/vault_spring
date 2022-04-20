package com.example.vault_spring.transactions.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.transactions.models.CurrencyTransaction;
import com.example.vault_spring.transactions.models.CurrencyTransactionCreateForm;
import com.example.vault_spring.transactions.repositories.CurrencyTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public CurrencyTransaction save(CurrencyTransactionCreateForm createForm) {

        CurrencyTransaction currencyTransaction = CurrencyTransaction.builder()
                .currency(convertCurrencyFromCreateForm(createForm))
                .transactionDate(convertTransactionDAteFromCreateForm(createForm))
                .currencyAmount(createForm.getCurrencyAmount())
                .currencyBuyPrice(createForm.getCurrencyBuyPrice())
                .transactionSum(countTransactionSum(createForm))
                .build();

        return repository.save(currencyTransaction);
    }

    private Currency convertCurrencyFromCreateForm(final CurrencyTransactionCreateForm createForm) {
        String currency = createForm.getCurrency();

        CurrencyType currencyType = CurrencyType.typeOf(currency);

        return Currency.builder()
                .currencyType(currencyType)
                .name(currencyType.getName())
                .build();
    }

    private LocalDate convertTransactionDAteFromCreateForm(CurrencyTransactionCreateForm createForm) {
        String transactionDate = createForm.getTransactionDate();

        return LocalDate.parse(transactionDate);
    }

    private BigDecimal countTransactionSum(final CurrencyTransactionCreateForm createForm) {
        final Double currencyAmount = createForm.getCurrencyAmount();
        final BigDecimal currencyBuyPrice = createForm.getCurrencyBuyPrice();

        return new BigDecimal(currencyAmount).multiply(currencyBuyPrice);
    }
}
