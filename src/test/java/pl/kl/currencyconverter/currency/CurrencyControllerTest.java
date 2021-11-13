package pl.kl.currencyconverter.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.kl.currencyconverter.exception.SameCurrenciesGivenException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStatusOk_WhenRequestParametersAreGiven() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("fromCurrency", "USD");
        httpHeaders.add("toCurrency", "PLN");
        httpHeaders.add("amount", "1");

        final MockHttpServletRequestBuilder params = MockMvcRequestBuilders
                .get("/currency")
                .params(httpHeaders);

        mockMvc.perform(params)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnStatusBadRequest_WhenRequestParametersAreNotGiven() throws Exception {
        final MockHttpServletRequestBuilder params = MockMvcRequestBuilders
                .get("/currency");

        mockMvc.perform(params)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatusBadRequest_WhenOneOfRequestParametersIsNotGiven() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("toCurrency", "PLN");
        httpHeaders.add("amount", "1");

        final MockHttpServletRequestBuilder params = MockMvcRequestBuilders
                .get("/currency")
                .params(httpHeaders);

        mockMvc.perform(params)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatusBadRequest_whenCurrenciesAreTheSame() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("fromCurrency", "USD");
        httpHeaders.add("toCurrency", "USD");
        httpHeaders.add("amount", "1");

        final MockHttpServletRequestBuilder params = MockMvcRequestBuilders
                .get("/currency")
                .params(httpHeaders);

        mockMvc.perform(params)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SameCurrenciesGivenException));
    }

    @Test
    void shouldReturnStatusBadRequest_whenCurrencyIsNotFoundInApiResponse() throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("fromCurrency", "PLN");
        httpHeaders.add("toCurrency", "TZS");
        httpHeaders.add("amount", "1");

        final MockHttpServletRequestBuilder params = MockMvcRequestBuilders
                .get("/currency")
                .params(httpHeaders);

        mockMvc.perform(params)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }
}
