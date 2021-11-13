package pl.kl.currencyconverter.nbp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rate {

    @JsonProperty("mid")
    private String mid;

    @JsonProperty("code")
    private String code;
}
