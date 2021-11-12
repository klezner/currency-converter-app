package pl.kl.currencyconverter.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyServiceConfig {

    @Bean
    protected NbpCurrencyService nbpCurrencyService() {
        return new NbpCurrencyService();
    }
}
