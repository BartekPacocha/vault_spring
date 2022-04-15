package com.example.vault_spring.vault.models;

import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.commons.models.ExchangeCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
public class VaultData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    @Embedded
    private ExchangeCourse exchangeCourse;
}
