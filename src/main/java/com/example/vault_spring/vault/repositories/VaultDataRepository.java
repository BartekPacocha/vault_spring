package com.example.vault_spring.vault.repositories;

import com.example.vault_spring.vault.models.VaultData;
import org.springframework.data.repository.CrudRepository;

public interface VaultDataRepository extends CrudRepository<VaultData, Integer> {
}
