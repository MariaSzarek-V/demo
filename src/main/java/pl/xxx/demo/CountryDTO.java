package pl.xxx.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class CountryDTO {

    @JsonProperty("country_id")
    private long apiCountryId;
    @JsonProperty("country_name")
    private String name;
}