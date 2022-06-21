package com.example.vault_spring.exchange_course.controllers;

import com.example.vault_spring.exchange_course.services.ExchangeCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CurrencyScrapperController {

    private final ExchangeCourseService exchangeCourseService;

    @GetMapping(path = "/exchangeCourses")
    public String showAllCurrencies(final Model model,
                                    @RequestParam(value = "refresh", required = false, defaultValue = "false") boolean refresh) {
        if (refresh) {
            exchangeCourseService.refreshExchangeCourses();
        }

        final var latestCourses = exchangeCourseService.getLastCourses();
        model.addAttribute("exchangeCourses", latestCourses);
        model.addAttribute("courseDate", latestCourses.get(0).getDate());

        return "exchangeCourses";
    }
}
