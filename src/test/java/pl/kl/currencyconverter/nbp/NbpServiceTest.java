package pl.kl.currencyconverter.nbp;

import org.junit.jupiter.api.Test;
import pl.kl.currencyconverter.currency.Currencies;
import pl.kl.currencyconverter.currency.CurrencyCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NbpServiceTest {

    private final NbpServiceClient nbpServiceClient = new NbpServiceClient();
    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator();
    private final NbpService nbpService = new NbpService(nbpServiceClient, currencyCalculator);

    @Test
    public void fromPLNtoUSD() {
        // given
        final Currencies fromCurrency = Currencies.USD;
        final Currencies toCurrency = Currencies.PLN;
        final BigDecimal givenAmount = BigDecimal.valueOf(100);
        final BigDecimal expectedAmount = BigDecimal.valueOf(405.59);
        // when
        final BigDecimal calculatedAmount = nbpService.calculateValue(fromCurrency, toCurrency, givenAmount);
        // then
        assertEquals(expectedAmount, calculatedAmount);
    }
}
