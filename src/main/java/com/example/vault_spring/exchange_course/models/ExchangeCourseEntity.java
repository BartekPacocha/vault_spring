package com.example.vault_spring.exchange_course.models;

import com.example.vault_spring.commons.models.CurrencyData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder(toBuilder=true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ExchangeCourse")
public class ExchangeCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    @Embedded
    private CurrencyData currency;

    @Column(precision = 5, scale = 4)
    @Type(type = "big_decimal")
    private BigDecimal buyPrice;

    @Column(precision = 5, scale = 4)
    @Type(type = "big_decimal")
    private BigDecimal sellPrice;

}
