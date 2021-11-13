package pl.kl.currencyconverter.nbp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Rates {

    @JsonProperty("rates")
    private List<Rate> rates;
}
