package pl.xxx.demo.Prediction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PredictionRequestDTO {
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private Long gameId;
}
