package com.example.vault_spring.scraper.services;

import com.example.vault_spring.commons.enums.CurrencyType;
import com.example.vault_spring.commons.models.Currency;
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
public class CurrencyScraperServiceImpl implements CurrencyScraperService {

    public static final String URL = "http://www.kantorwojnicz.pl/";
    public static final String TABLE_TAG = "table";
    public static final String TBODY_TAG = "tbody";
    public static final String TR_TAG = "tr";
    public static final String TD_TAG = "td";
    public static final String STRONG_TAG = "strong";
    public static final String COMMA = ",";
    public static final String DOT = ".";

    @Override
    public Currency getCurrency(final CurrencyType currencyType) {
        final Optional<Currency> currency = downloadCurrency(currencyType);

        return currency.orElseThrow();
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();

        for (CurrencyType type : CurrencyType.values()) {
            final Optional<Currency> currency = downloadCurrency(type);
            currency.ifPresent(currencies::add);
        }

        return currencies;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void schedulerTest() {
        System.out.println("schedulerTest " + LocalDateTime.now());
    }

    private Optional<Currency> downloadCurrency(final CurrencyType currencyType) {
        Currency currency = Currency.builder().build();

        try {
            Document doc = Jsoup.connect(URL).get();

            Elements trTags = doc.getElementsByTag(TABLE_TAG)
                    .get(1).getElementsByTag(TBODY_TAG)
                    .get(0).getElementsByTag(TR_TAG);

            Element tdSell = trTags.get(currencyType.getRowNumber()).getElementsByTag(TD_TAG).get(3);
            Element strongSell = tdSell.getElementsByTag(STRONG_TAG).get(0);
            String strValSell = Jsoup.parse(strongSell.toString()).text().replaceAll(COMMA, DOT);
            BigDecimal valueSell = new BigDecimal(strValSell);

            Element tdBuy = trTags.get(currencyType.getRowNumber()).getElementsByTag(TD_TAG).get(4);
            Element strongBuy = tdBuy.getElementsByTag(STRONG_TAG).get(0);
            String strValBuy = Jsoup.parse(strongBuy.toString()).text().replaceAll(COMMA, DOT);
            BigDecimal valueBuy = new BigDecimal(strValBuy);

            Element tdName = trTags.get(currencyType.getRowNumber()).getElementsByTag(TD_TAG).get(2);
            Element strongName = tdName.getElementsByTag(STRONG_TAG).get(0);
            String strValName = Jsoup.parse(strongName.toString()).text().replaceAll(COMMA, DOT);

            currency = Currency.builder()
                    .currencyType(currencyType)
                    .name(strValName)
                    .buyPrice(valueBuy)
                    .sellPrice(valueSell)
                    .build();

            return Optional.of(currency);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.of(currency);
    }
}
