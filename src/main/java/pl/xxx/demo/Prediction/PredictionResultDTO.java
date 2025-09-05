package pl.xxx.demo.Prediction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionResultDTO {

    private String username;

    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;

    private Integer predictedHomeScore;
    private Integer predictedAwayScore;

    private Integer points;


}
