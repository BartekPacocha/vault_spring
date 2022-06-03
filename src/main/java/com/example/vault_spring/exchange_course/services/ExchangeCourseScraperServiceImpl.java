package com.example.vault_spring.exchange_course.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.CurrencyData;
import com.example.vault_spring.exchange_course.models.ExchangeCourse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

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
    public List<ExchangeCourse> downloadAll() {
        List<ExchangeCourse> exchangeCourses = new ArrayList<>();

        for (CurrencyType type : CurrencyType.values()) {
            final Optional<ExchangeCourse> exchangeCourse = downloadExchangeCourse(type);
            exchangeCourse.ifPresent(exchangeCourses::add);
        }

        return exchangeCourses;
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

            final CurrencyData currency = CurrencyData.builder()
                    .currencyType(currencyType)
                    .name(strValName)
                    .build();

            ExchangeCourse exchangeCourse = ExchangeCourse.builder()
                    .currency(currency)
                    .buyPrice(valueBuy)
                    .sellPrice(valueSell)
                    .date(now())
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
