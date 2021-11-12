package pl.kl.currencyconverter.currency;

import java.math.BigDecimal;

public interface CurrencyService {

    BigDecimal calculateValue(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount);
}
