package com.example.vault_spring.scraper.controllers;

import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.scraper.services.CurrencyScraperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CurrencyScrapperController {

    private final CurrencyScraperService currencyScraperService;

    @GetMapping(path = "/currencies")
    public String showAllCurrencies(Model model) {
        final List<Currency> allCurrenciesToList = currencyScraperService.getAll();
        model.addAttribute("currencies", allCurrenciesToList);

        return "currencies";
    }
}
