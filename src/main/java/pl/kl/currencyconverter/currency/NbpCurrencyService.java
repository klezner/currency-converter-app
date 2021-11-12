package pl.kl.currencyconverter.currency;

import java.math.BigDecimal;

public class NbpCurrencyService implements CurrencyService {

    @Override
    public String calculateValue(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount) {
        return String.valueOf(amount.multiply(new BigDecimal(1)));
    }
}
