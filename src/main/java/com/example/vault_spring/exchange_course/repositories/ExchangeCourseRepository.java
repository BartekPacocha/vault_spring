package com.example.vault_spring.exchange_course.repositories;

import com.example.vault_spring.exchange_course.models.ExchangeCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeCourseRepository extends JpaRepository<ExchangeCourseEntity, Long> {

//    @Query("select * from exchange_course ec where ec.")

}
