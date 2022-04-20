package com.example.vault_spring.transactions.services;

import com.example.vault_spring.transactions.models.CurrencyTransaction;
import com.example.vault_spring.transactions.models.CurrencyTransactionCreateForm;

import java.util.List;

public interface CurrencyTransactionService {

    List<CurrencyTransaction> getAll();

    // CurrencyTransaction save(CurrencyTransaction currencyTransaction);

    CurrencyTransaction save(CurrencyTransactionCreateForm createForm);

}
