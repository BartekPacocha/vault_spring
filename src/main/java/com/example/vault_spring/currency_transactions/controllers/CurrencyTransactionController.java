package com.example.vault_spring.currency_transactions.controllers;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.currency_transactions.models.CurrencyTransaction;
import com.example.vault_spring.currency_transactions.models.CurrencyTransactionCreateForm;
import com.example.vault_spring.currency_transactions.services.CurrencyTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CurrencyTransactionController {

    private final CurrencyTransactionService service;

    @GetMapping(path = "/transactions")
    public String getAllTransactions(Model model) {
        List<CurrencyTransaction> all = service.getAll();

        BigDecimal transactionsSum = all.stream()
                .map(CurrencyTransaction::getTransactionSum)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);

        model.addAttribute("transactions", all);
        model.addAttribute("transactionsSum", transactionsSum);

        return "transactions";
    }

    @GetMapping("/transactions/add")
    public String showAddTransactions(Model model) {
        // CurrencyTransaction transaction = new CurrencyTransaction();

        CurrencyType[] currencyTypes = CurrencyType.values();

        model.addAttribute("transaction", new CurrencyTransactionCreateForm());
        model.addAttribute("currencyTypes", currencyTypes);

        return "add_transaction";
    }

    @PostMapping("/transactions/add")
    public String addTransaction(@ModelAttribute("transaction") CurrencyTransactionCreateForm createForm,
                                 Model model) {
        service.save(createForm);

        return "redirect:/transactions";
    }
}
