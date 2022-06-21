package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.forms.ExchangeCourseSearchForm;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;
import com.example.vault_spring.exchange_course.models.ExchangeCourseEntity;
import com.example.vault_spring.exchange_course.repositories.ExchangeCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.vault_spring.exchange_course.models.ExchangeCourse.fromEntity;
import static com.example.vault_spring.exchange_course.models.ExchangeCourse.toEntity;
import static java.util.stream.Collectors.toList;

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
        return repository.getLastCourses().stream()
                .map(ExchangeCourse::fromEntity)
                .collect(toList());
    }

    @Override
    public ExchangeCourse getLastCurrencyCourse(final CurrencyData currency) {
        return fromEntity(repository.getLastCurrencyCourse(currency));
    }


    @Override
    public List<ExchangeCourse> getAllCourses() {
        return repository.findAll().stream()
                .map(ExchangeCourse::fromEntity)
                .collect(toList());
    }

    // TODO
    @Override
    public List<ExchangeCourse> searchCourses(final ExchangeCourseSearchForm searchForm) {
        return null;
    }

    @Override
    public void refreshExchangeCourses() {
        final var latestDate = repository.getLatestDate();

        if (!latestDate.equals(LocalDate.now())) {
            scraperService.downloadAll().stream()
                    .map(ExchangeCourse::toEntity)
                    .forEach(e -> repository.save(e));
        }
    }

    private void saveEntity(final ExchangeCourseEntity exchangeCourse) {
        repository.save(exchangeCourse);
    }

//    private ExchangeCourseEntity findExchangeCourse(final ExchangeCourseSearchForm searchForm) {
//        repository.
//    }
}
