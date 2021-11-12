package pl.kl.currencyconverter.currency;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class Currency {
    private final Currencies fromCurrency;
    private final Currencies toCurrency;
    private final String amount;
}
