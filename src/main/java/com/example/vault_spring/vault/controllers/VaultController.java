package com.example.vault_spring.vault.controllers;

import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.commons.models.ExchangeCourse;
import com.example.vault_spring.vault.models.VaultData;
import com.example.vault_spring.vault.repositories.VaultDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.vault_spring.commons.models.Currency.prototype;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class VaultController {

    private final VaultDataRepository vaultDataRepository;

    @GetMapping(path = "/vault")
    public String getVault() {
        return "vault";
    }

    @GetMapping(path = "/vault/add")
    public String addVaultData() {

        final VaultData aaa = VaultData.builder()
                .exchangeCourse(ExchangeCourse.prototype())
                .build();

        vaultDataRepository.save(aaa);

        return "vault";
    }
}
