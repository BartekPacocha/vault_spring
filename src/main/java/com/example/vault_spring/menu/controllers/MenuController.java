package com.example.vault_spring.menu.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MenuController {

    @GetMapping(path = "/")
    public String showMenu() {
        return "menu";
    }
}
