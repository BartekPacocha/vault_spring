package com.example.vault_spring.vault.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.vault.models.VaultData;
import com.example.vault_spring.vault.models.VaultDataSumForm;

import java.util.List;

public interface VaultService {

    List<VaultData> getVaultDataWithFilter(final List<CurrencyType> currencyData);

    VaultDataSumForm getVaultDataSum(final List<VaultData> vaultData);

}
