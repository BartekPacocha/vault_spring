package com.example.vault_spring.vault.controllers;

import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.services.CurrencyTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class VaultController {

    private final CurrencyTransactionService currencyTransactionService;

    @GetMapping(path = "/vault")
    public String getVault() {

        List<CurrencyTransaction> currencyTransactions = currencyTransactionService.getAll();

        return "vault";
    }
}
