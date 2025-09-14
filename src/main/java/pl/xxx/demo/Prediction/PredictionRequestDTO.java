package pl.xxx.demo.Prediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PredictionRequestDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull(message = "Wynik nie może być pusty")
    @Min(value = 0, message = "Wynik nie może być ujemny")
    private Integer predictedHomeScore;
    @NotNull(message = "Wynik nie może być pusty")
    @Min(value = 0, message = "Wynik nie może być ujemny")
    private Integer predictedAwayScore;
    private Long gameId;
}
