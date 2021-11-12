package pl.kl.currencyconverter.currency;

import org.springframework.stereotype.Component;
import pl.kl.currencyconverter.exception.CurrencyNotFoundException;
import pl.kl.currencyconverter.nbp.Rate;
import pl.kl.currencyconverter.nbp.Rates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyCalculator {
    public static final int BIG_DECIMAL_SCALE = 4;
    private final String REGEX = ",";
    private final String REPLACEMENT = "";
    private final int TWO_CURRENCIES = 2;

    public BigDecimal calculateAmount(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount, Rates allRates) {
        final List<Rate> rates = getRates(fromCurrency, toCurrency, allRates);

        if (isOneCurrencyPLN(rates)) {
            return calculateForPln(toCurrency, amount, rates);
        }
        return calculateForForeignCurrencies(fromCurrency, toCurrency, amount, rates);
    }

    private BigDecimal calculateForForeignCurrencies(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount, List<Rate> rates) {
        final Rate fromRate = rates.stream()
                .filter(rate -> rate.getCode().equals(fromCurrency.name()))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
        final Rate toRate = rates.stream()
                .filter(rate -> rate.getCode().equals(toCurrency.name()))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);

        final BigDecimal rateFrom = new BigDecimal(fromRate.getMid()).setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        final BigDecimal rateTo = new BigDecimal(toRate.getMid()).setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);

        return rateFrom.divide(rateTo, RoundingMode.HALF_UP).multiply(amount).setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    private List<Rate> getRates(Currencies fromCurrency, Currencies toCurrency, Rates allRates) {
        return allRates.getRates().stream()
                .filter(rate -> isFromOrToCurrency(fromCurrency, toCurrency, rate))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateForPln(Currencies toCurrency, BigDecimal amount, List<Rate> rates) {
        final BigDecimal rate = new BigDecimal(rates.get(0).getMid().replaceAll(REGEX, REPLACEMENT));

        if (ifDestinationCurrencyIsPLN(toCurrency)) {
            return multiplyAmountByRate(amount, rate);
        }
        return divideAmountByRate(amount, rate);
    }

    private BigDecimal multiplyAmountByRate(BigDecimal amount, BigDecimal rate) {
        return amount.setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP).multiply(rate).setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    private BigDecimal divideAmountByRate(BigDecimal amount, BigDecimal rate) {
        return amount.setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP).divide(rate, RoundingMode.HALF_UP).setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    private boolean isOneCurrencyPLN(List<Rate> rates) {
        return rates.size() < TWO_CURRENCIES;
    }

    private boolean ifDestinationCurrencyIsPLN(Currencies toCurrency) {
        return toCurrency.name().equals(Currencies.PLN.name());
    }

    private boolean isFromOrToCurrency(Currencies fromCurrency, Currencies toCurrency, Rate rate) {
        return fromCurrency.name().equals(rate.getCode()) || toCurrency.name().equals(rate.getCode());
    }
}
