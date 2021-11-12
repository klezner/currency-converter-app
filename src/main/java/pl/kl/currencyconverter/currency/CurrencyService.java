package pl.kl.currencyconverter.currency;

import java.math.BigDecimal;

public interface CurrencyService {

    String calculateValue(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount);
}
