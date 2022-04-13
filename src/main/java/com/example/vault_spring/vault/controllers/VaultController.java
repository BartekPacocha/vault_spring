package com.example.vault_spring.vault.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class VaultController {

    @GetMapping(path = "/vault")
    public String getVault() {
        return "vault";
    }
}
