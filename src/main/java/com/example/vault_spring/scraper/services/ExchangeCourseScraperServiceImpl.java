package com.example.vault_spring.scraper.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.Currency;
import com.example.vault_spring.commons.models.ExchangeCourse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeCourseScraperServiceImpl implements ExchangeCourseScraperService {

    public static final String URL = "http://www.kantorwojnicz.pl/";
    public static final String TABLE_TAG = "table";
    public static final String TBODY_TAG = "tbody";
    public static final String TR_TAG = "tr";
    public static final String TD_TAG = "td";
    public static final String STRONG_TAG = "strong";
    public static final String COMMA = ",";
    public static final String DOT = ".";

    @Override
    public ExchangeCourse getExchangeCourse(final CurrencyType currencyType) {
        final Optional<ExchangeCourse> exchangeCourse = downloadExchangeCourse(currencyType);

        return exchangeCourse.orElseThrow();
    }

    @Override
    public List<ExchangeCourse> getAll() {
        List<ExchangeCourse> exchangeCourses = new ArrayList<>();

        for (CurrencyType type : CurrencyType.values()) {
            final Optional<ExchangeCourse> exchangeCourse = downloadExchangeCourse(type);
            exchangeCourse.ifPresent(exchangeCourses::add);
        }

        return exchangeCourses;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void schedulerTest() {
        System.out.println("schedulerTest " + LocalDateTime.now());
    }

    private Optional<ExchangeCourse> downloadExchangeCourse(final CurrencyType currencyType) {
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements trTags = doc.getElementsByTag(TABLE_TAG)
                    .get(1).getElementsByTag(TBODY_TAG)
                    .get(0).getElementsByTag(TR_TAG);

            final BigDecimal valueSell = getBigDecimal(currencyType, trTags, 3);
            final BigDecimal valueBuy = getBigDecimal(currencyType, trTags, 4);

            final Element tdName = trTags.get(currencyType.getRowNumber()).getElementsByTag(TD_TAG).get(2);
            final Element strongName = tdName.getElementsByTag(STRONG_TAG).get(0);
            final String strValName = Jsoup.parse(strongName.toString()).text().replaceAll(COMMA, DOT);

            final Currency currency = Currency.builder()
                    .currencyType(currencyType)
                    .name(strValName)
                    .build();

            ExchangeCourse exchangeCourse = ExchangeCourse.builder()
                    .currency(currency)
                    .buyPrice(valueBuy)
                    .sellPrice(valueSell)
                    .build();

            return Optional.of(exchangeCourse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private BigDecimal getBigDecimal(final CurrencyType currencyType, final Elements trTags, final Integer tdTagRow) {
        Element td = trTags.get(currencyType.getRowNumber()).getElementsByTag(TD_TAG).get(tdTagRow);
        Element strong = td.getElementsByTag(STRONG_TAG).get(0);
        String strVal = Jsoup.parse(strong.toString()).text().replaceAll(COMMA, DOT);

        return new BigDecimal(strVal);
    }
}
