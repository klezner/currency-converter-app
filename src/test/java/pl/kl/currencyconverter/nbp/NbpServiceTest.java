package pl.kl.currencyconverter.nbp;

import org.junit.jupiter.api.Test;
import pl.kl.currencyconverter.currency.Currencies;
import pl.kl.currencyconverter.currency.CurrencyCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NbpServiceTest {

    private final NbpServiceClient nbpServiceClient = new NbpServiceClient();
    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private final NbpService nbpService = new NbpService(nbpServiceClient, currencyCalculator);

    @Test
    public void fromUSDtoPLN() {
        // given
        final Currencies fromCurrency = Currencies.USD;
        final Currencies toCurrency = Currencies.PLN;
        final BigDecimal givenAmount = BigDecimal.valueOf(100);
        final BigDecimal expectedAmount = BigDecimal.valueOf(405.5900).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal givenAmount = BigDecimal.valueOf(100);
        final BigDecimal expectedAmount = BigDecimal.valueOf(24.6554).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal givenAmount = BigDecimal.valueOf(100);
        final BigDecimal expectedAmount = BigDecimal.valueOf(87.3900).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
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
        final BigDecimal givenAmount = BigDecimal.valueOf(100);
        final BigDecimal expectedAmount = BigDecimal.valueOf(114.4300).setScale(CurrencyCalculator.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }
}
