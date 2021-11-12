package pl.kl.currencyconverter.currency;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Builder
public class Currency {
    private final Currencies fromCurrency;
    private final Currencies toCurrency;
    private final BigDecimal amount;
}
