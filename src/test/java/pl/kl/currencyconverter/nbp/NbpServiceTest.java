package pl.kl.currencyconverter.nbp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.kl.currencyconverter.currency.Currencies;
import pl.kl.currencyconverter.currency.CurrencyCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NbpServiceTest {

    private final NbpServiceClient nbpServiceClient = Mockito.mock(NbpServiceClient.class);
    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private final NbpService nbpService = new NbpService(nbpServiceClient, currencyCalculator);

    @Test
    public void fromUSDtoPLN() {
        // given
        final Currencies fromCurrency = Currencies.USD;
        final Currencies toCurrency = Currencies.PLN;
        final BigDecimal givenAmount = BigDecimal.valueOf(1);
        final BigDecimal expectedAmount = BigDecimal.valueOf(4.000).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        when(nbpServiceClient.getAllRates()).thenReturn(getMockedRates());
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void fromPLNtoUSD() {
        // given
        final Currencies fromCurrency = Currencies.PLN;
        final Currencies toCurrency = Currencies.USD;
        final BigDecimal givenAmount = BigDecimal.valueOf(1);
        final BigDecimal expectedAmount = BigDecimal.valueOf(0.2500).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        when(nbpServiceClient.getAllRates()).thenReturn(getMockedRates());
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void fromUSDtoEUR() {
        // given
        final Currencies fromCurrency = Currencies.USD;
        final Currencies toCurrency = Currencies.EUR;
        final BigDecimal givenAmount = BigDecimal.valueOf(1);
        final BigDecimal expectedAmount = BigDecimal.valueOf(0.8889).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        when(nbpServiceClient.getAllRates()).thenReturn(getMockedRates());
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void fromEURtoUSD() {
        // given
        final Currencies fromCurrency = Currencies.EUR;
        final Currencies toCurrency = Currencies.USD;
        final BigDecimal givenAmount = BigDecimal.valueOf(1);
        final BigDecimal expectedAmount = BigDecimal.valueOf(1.1250).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        when(nbpServiceClient.getAllRates()).thenReturn(getMockedRates());
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }

    private Rates getMockedRates() {
        final List<Rate> newRates = new ArrayList<>();
        newRates.add(new Rate("4.000", "USD"));
        newRates.add(new Rate("4.500", "EUR"));
        return new Rates(newRates);
    }
}
