package pl.kl.currencyconverter.currency;

public interface CurrencyService {

    String calculateValue(Currencies fromCurrency, Currencies toCurrency, String amount);
}
