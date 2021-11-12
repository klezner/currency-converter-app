package pl.kl.currencyconverter.currency;

import org.springframework.stereotype.Component;
import pl.kl.currencyconverter.nbp.Rate;
import pl.kl.currencyconverter.nbp.Rates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyCalculator {

    public BigDecimal calculateAmount(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount, Rates allRates) {
        final List<Rate> rates = allRates.getRates().stream()
                .filter(rate -> isFromCurrency(fromCurrency, rate))
                .collect(Collectors.toList());

        return calculateForPln(amount, rates);
    }

    private BigDecimal calculateForPln(BigDecimal amount, List<Rate> rates) {
        final BigDecimal rate = new BigDecimal(rates.get(0).getMid());

        return multiplyAmountByRate(amount, rate);
    }

    private BigDecimal multiplyAmountByRate(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).setScale(2, RoundingMode.DOWN);
    }

    private boolean isFromCurrency(Currencies fromCurrency, Rate rate) {
        return fromCurrency.name().equals(rate.getCode());
    }
}
