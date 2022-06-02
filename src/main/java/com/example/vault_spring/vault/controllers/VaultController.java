package com.example.vault_spring.vault.controllers;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.vault.models.VaultData;
import com.example.vault_spring.vault.models.VaultDataSumForm;
import com.example.vault_spring.vault.services.VaultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class VaultController {

    private final VaultService vaultService;

    @GetMapping(path = "/vault")
    public String getVault(Model model) {

        CurrencyType[] currencyTypes = CurrencyType.values();
        final List<VaultData> vaultData = vaultService.getVaultDataWithFilter(List.of(currencyTypes));

        final VaultDataSumForm vaultDataSum = vaultService.getVaultDataSum(vaultData);

        model.addAttribute("vaultData", vaultData);
        model.addAttribute("vaultDataSum", vaultDataSum);

        return "vault";
    }
}
