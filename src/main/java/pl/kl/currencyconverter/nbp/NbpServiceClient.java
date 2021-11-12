package pl.kl.currencyconverter.nbp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NbpServiceClient {

    private static final String BASE_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";
    private final WebClient webClient;
    private NbpServiceClientRepository nbpServiceClientRepository;

    public NbpServiceClient() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Rates getAllRates() {
        if (nbpServiceClientRepository != null) {
            return nbpServiceClientRepository.getCollectedRates();
        }
        final Rates rates = getAllExchangeRates().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not found"));
        nbpServiceClientRepository = new NbpServiceClientRepository(rates);

        return rates;
    }

    private List<Rates> getAllExchangeRates() {
        final Rates[] exchangeRatesResponse = getResponse();
        assert exchangeRatesResponse != null;

        return Arrays.stream(exchangeRatesResponse)
                .collect(Collectors.toList());
    }

    private Rates[] getResponse() {

        return webClient.get()
                .retrieve()
                .bodyToMono(Rates[].class)
                .block();
    }
}
