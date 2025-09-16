package pl.xxx.demo.PredictionResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GamePredictionResultResponseDTO {
    private String username;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private Integer points;
}