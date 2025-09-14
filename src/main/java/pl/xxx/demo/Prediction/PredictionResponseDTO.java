package pl.xxx.demo.Prediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PredictionResponseDTO  {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long gameId;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private String homeTeam;
    private String awayTeam;
}
