package com.example.vault_spring.transactions.services;

import com.example.vault_spring.transactions.models.CurrencyTransaction;

import java.util.List;

public interface CurrencyTransactionService {

    List<CurrencyTransaction> getAll();

    CurrencyTransaction save(CurrencyTransaction currencyTransaction);

}
