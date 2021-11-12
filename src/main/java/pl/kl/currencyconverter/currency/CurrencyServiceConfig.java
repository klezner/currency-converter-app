package pl.kl.currencyconverter.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CurrencyServiceConfig {

    private final NbpServiceClient nbpServiceClient;
    private final CurrencyCalculator currencyCalculator;

    @Bean
    protected NbpCurrencyService nbpCurrencyService() {
        return new NbpCurrencyService();
    }
}
