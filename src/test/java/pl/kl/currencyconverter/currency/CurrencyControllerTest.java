package pl.kl.currencyconverter.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("4.05")));
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
}
