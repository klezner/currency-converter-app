package pl.kl.currencyconverter.nbp;

import lombok.RequiredArgsConstructor;
import pl.kl.currencyconverter.currency.Currencies;
import pl.kl.currencyconverter.currency.CurrencyCalculator;
import pl.kl.currencyconverter.currency.CurrencyService;
import pl.kl.currencyconverter.exception.SameCurrenciesGivenException;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class NbpService implements CurrencyService {

    private final NbpServiceClient nbpServiceClient;
    private final CurrencyCalculator currencyCalculator;

    @Override
    public BigDecimal calculateValue(Currencies fromCurrency, Currencies toCurrency, BigDecimal amount) {
        if (fromCurrency.name().equals(toCurrency.name())) {
            throw new SameCurrenciesGivenException();
        }
        final Rates allRates = nbpServiceClient.getAllRates();
        return currencyCalculator.calculateAmount(fromCurrency, toCurrency, amount, allRates);
    }
}
