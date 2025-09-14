package pl.xxx.demo.PredictionResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionResultDTO {

    private Long userId;
    private Long gameId;
    private Long predictionId;
    private Long pointsId;

    private String username;

    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;

    private Integer predictedHomeScore;
    private Integer predictedAwayScore;

    private Integer points;


}
