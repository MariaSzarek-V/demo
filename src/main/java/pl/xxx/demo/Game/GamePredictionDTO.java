package pl.xxx.demo.Game;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class GamePredictionDTO {
    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private Long predictionId;
}