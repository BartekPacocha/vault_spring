package com.example.vault_spring.transactions.controllers;

import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.transactions.models.CurrencyTransaction;
import com.example.vault_spring.transactions.services.CurrencyTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CurrencyTransactionController {

    private final CurrencyTransactionService service;

    @GetMapping(path = "/transactions")
    public String getAllTransactions(Model model) {
        CurrencyTransaction currencyTransaction = CurrencyTransaction.builder()
                .currency(Currency.prototype())
                .currencyAmount(12d)
                .currencyBuyPrice(BigDecimal.TEN)
                .transactionDate(LocalDate.now())
                .build();

        service.save(currencyTransaction);

        List<CurrencyTransaction> all = service.getAll();


        model.addAttribute("transactions", all);

        return "transactions";
    }

    @GetMapping(value = "/transactions/add")
    public String showAddTransactions(Model model) {
        CurrencyTransaction transaction = new CurrencyTransaction();

        model.addAttribute("transaction", transaction);

        return "add_transaction";
    }

    @PostMapping(path = "/transactions/add")
    public String addTransaction(@ModelAttribute("transaction") CurrencyTransaction transaction,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/";
        }
        service.save(transaction);

        return "redirect:/transactions";
    }

//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:/index";
//    }

}
