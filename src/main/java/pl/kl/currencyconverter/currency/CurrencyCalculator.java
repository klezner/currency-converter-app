package pl.kl.currencyconverter.currency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.kl.currencyconverter.nbp.Rate;
import pl.kl.currencyconverter.nbp.Rates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CurrencyCalculator {

    public BigDecimal calculateAmount(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount, Rates allRates) {
        final List<Rate> rates = allRates.getRates().stream()
                .filter(rate -> isFromOrToCurrency(fromCurrency, toCurrency, rate))
                .collect(Collectors.toList());
        if (isOneCurrencyPLN(rates)) {
            return calculateForPln(toCurrency, amount, rates);
        }
        return BigDecimal.valueOf(-9.99);
    }

    private BigDecimal calculateForPln(Currencies toCurrency, BigDecimal amount, List<Rate> rates) {
        final BigDecimal rate = new BigDecimal(rates.get(0).getMid().replaceAll(",", ""));

        if (ifDestinationCurrencyIsPLN(toCurrency)) {
            return multiplyAmountByRate(amount, rate);
        }
        return divideAmountByRate(amount, rate);
    }

    private BigDecimal multiplyAmountByRate(BigDecimal amount, BigDecimal rate) {
        return amount.setScale(4, RoundingMode.DOWN).multiply(rate).setScale(2, RoundingMode.DOWN);
    }

    private BigDecimal divideAmountByRate(BigDecimal amount, BigDecimal rate) {
        return amount.setScale(4, RoundingMode.DOWN).divide(rate, RoundingMode.DOWN).setScale(2, RoundingMode.DOWN);
    }

    private boolean isOneCurrencyPLN(List<Rate> rates) {
        return rates.size() <= 1;
    }

    private boolean ifDestinationCurrencyIsPLN(Currencies toCurrency) {
        return toCurrency.name().equals(Currencies.PLN.name());
    }

    private boolean isFromOrToCurrency(Currencies fromCurrency, Currencies toCurrency, Rate rate) {
        return fromCurrency.name().equals(rate.getCode()) || toCurrency.name().equals(rate.getCode());
    }
}
