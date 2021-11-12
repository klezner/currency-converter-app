package pl.kl.currencyconverter.currency;

public class NbpCurrencyService implements CurrencyService {

    @Override
    public String calculateValue(Currencies fromCurrency, Currencies toCurrency, String amount) {
        return String.valueOf(Double.parseDouble(amount) * 1);
    }
}
