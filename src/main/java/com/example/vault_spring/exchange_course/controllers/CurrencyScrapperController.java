package com.example.vault_spring.exchange_course.controllers;

import com.example.vault_spring.exchange_course.services.ExchangeCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CurrencyScrapperController {

    private final ExchangeCourseService service;

    @GetMapping(path = "/exchangeCourses")
    public String showAllCurrencies(Model model) {
        final var latestCourses = service.getLastCourses();
        model.addAttribute("exchangeCourses", latestCourses);

        return "exchangeCourses";
    }
}
