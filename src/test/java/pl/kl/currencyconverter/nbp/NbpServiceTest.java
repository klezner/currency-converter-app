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
        final BigDecimal expectedAmount = BigDecimal.valueOf(4.0559).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal expectedAmount = BigDecimal.valueOf(0.2466).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal expectedAmount = BigDecimal.valueOf(0.8790).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal expectedAmount = BigDecimal.valueOf(1.1377).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        when(nbpServiceClient.getAllRates()).thenReturn(getMockedRates());
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }

    private Rates getMockedRates() {
        final List<Rate> rates = new ArrayList<>();
        rates.add(new Rate("4.0559", "USD"));
        rates.add(new Rate("4.6142", "EUR"));
        return new Rates(rates);
    }
}
