package pl.kl.currencyconverter.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    BigDecimal getCalculatedValue(@RequestParam Currencies fromCurrency,
                                  @RequestParam Currencies toCurrency,
                                  @RequestParam BigDecimal amount) {

        return currencyService.calculateValue(fromCurrency, toCurrency, amount);
    }
}
