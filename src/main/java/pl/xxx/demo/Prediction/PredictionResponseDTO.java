package pl.xxx.demo.Prediction;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PredictionResponseDTO  {
    private Long gameId;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private String homeTeam;
    private String awayTeam;
}
