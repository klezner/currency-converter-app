package pl.kl.currencyconverter.nbp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Rate {

    @JsonProperty("mid")
    private String mid;

    @JsonProperty("code")
    private String code;
}
