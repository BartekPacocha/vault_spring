package com.example.vault_spring.currency_transactions.repositories;

import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTransactionRepository extends JpaRepository<CurrencyTransaction, Long> {
}
