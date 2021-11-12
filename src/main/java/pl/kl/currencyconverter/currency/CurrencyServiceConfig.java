package pl.kl.currencyconverter.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kl.currencyconverter.nbp.NbpService;
import pl.kl.currencyconverter.nbp.NbpServiceClient;

@Configuration
@RequiredArgsConstructor
public class CurrencyServiceConfig {

    private final NbpServiceClient nbpServiceClient;
    private final CurrencyCalculator currencyCalculator;

    @Bean
    protected NbpService nbpCurrencyService() {
        return new NbpService(nbpServiceClient, currencyCalculator);
    }
}
