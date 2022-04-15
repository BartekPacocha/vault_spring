package com.example.vault_spring.transactions.repositories;

import com.example.vault_spring.transactions.models.CurrencyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTransactionRepository extends JpaRepository<CurrencyTransaction, Long> {
}
