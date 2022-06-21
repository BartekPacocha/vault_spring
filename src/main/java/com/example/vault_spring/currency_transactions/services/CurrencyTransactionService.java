package com.example.vault_spring.currency_transactions.services;

import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.models.CurrencyTransactionCreateForm;

import java.util.List;

public interface CurrencyTransactionService {

    List<CurrencyTransaction> getAll();
    void save(CurrencyTransactionCreateForm createForm);

}
