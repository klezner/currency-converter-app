package pl.kl.currencyconverter.currency;

import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public String calculateValue(Currencies fromCurrency, Currencies toCurrency, String amount) {

        return String.valueOf(Double.parseDouble(amount) * 1);
    }
}
