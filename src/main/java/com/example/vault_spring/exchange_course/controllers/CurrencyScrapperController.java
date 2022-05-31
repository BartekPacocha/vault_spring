package com.example.vault_spring.exchange_course.controllers;

import com.example.vault_spring.commons.models.ExchangeCourse;
import com.example.vault_spring.exchange_course.services.ExchangeCourseScraperService;
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

    private final ExchangeCourseScraperService exchangeCourseScraperService;

    @GetMapping(path = "/exchangeCourses")
    public String showAllCurrencies(Model model) {
        final List<ExchangeCourse> allCurrenciesToList = exchangeCourseScraperService.getAll();
        model.addAttribute("exchangeCourses", allCurrenciesToList);

        return "exchangeCourses";
    }
}
