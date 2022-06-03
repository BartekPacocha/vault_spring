package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.exchange_course.forms.ExchangeCourseSearchForm;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;
import com.example.vault_spring.exchange_course.models.ExchangeCourseEntity;
import com.example.vault_spring.exchange_course.repositories.ExchangeCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.vault_spring.exchange_course.models.ExchangeCourse.toEntity;

@Service
@AllArgsConstructor
public class ExchangeCourseServiceImpl implements ExchangeCourseService {

    private ExchangeCourseScraperService scraperService;
    private ExchangeCourseRepository repository;

    @Override
    public void save(final ExchangeCourse exchangeCourse) {
        final ExchangeCourseEntity exchangeCourseEntity = toEntity(exchangeCourse);

        saveEntity(exchangeCourseEntity);
    }

    @Override
    public void saveAll(final List<ExchangeCourse> exchangeCourses) {
        exchangeCourses.stream()
                .map(ExchangeCourse::toEntity)
                .forEach(this::saveEntity);
    }

    @Override
    public List<ExchangeCourse> getLastCourses() {
        return null;
    }

    @Override
    public List<ExchangeCourse> getAllCourses() {
        return null;
    }

    @Override
    public List<ExchangeCourse> searchCourses(final ExchangeCourseSearchForm searchForm) {
        return null;
    }

    private void saveEntity(final ExchangeCourseEntity exchangeCourse) {
        repository.save(exchangeCourse);
    }

//    private ExchangeCourseEntity findExchangeCourse(final ExchangeCourseSearchForm searchForm) {
//        repository.
//    }
}