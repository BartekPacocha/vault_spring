package com.example.vault_spring.currency_transactions.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.models.CurrencyTransactionCreateForm;
import com.example.vault_spring.currency_transactions.repositories.CurrencyTransactionRepository;
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
    public void save(CurrencyTransactionCreateForm createForm) {

        CurrencyTransaction currencyTransaction = CurrencyTransaction.builder()
                .currency(convertCurrencyFromCreateForm(createForm))
                .transactionDate(convertTransactionDAteFromCreateForm(createForm))
                .currencyAmount(createForm.getCurrencyAmount())
                .currencyBuyPrice(createForm.getCurrencyBuyPrice())
                .transactionSum(countTransactionSum(createForm))
                .build();

        repository.save(currencyTransaction);
    }

    private CurrencyData convertCurrencyFromCreateForm(final CurrencyTransactionCreateForm createForm) {
        String currency = createForm.getCurrency();

        CurrencyType currencyType = CurrencyType.typeOf(currency);

        return CurrencyData.builder()
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
