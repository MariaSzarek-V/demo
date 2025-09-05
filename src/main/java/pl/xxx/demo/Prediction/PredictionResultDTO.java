package pl.xxx.demo.Prediction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionResultDTO {
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;

    private Integer predictedHomeScore;
    private Integer predictedAwayScore;

    public PredictionResultDTO() {}

    public PredictionResultDTO(String homeTeam, String awayTeam, Integer homeScore, Integer awayScore, Integer predictedHomeScore, Integer predictedAwayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.predictedHomeScore = predictedHomeScore;
        this.predictedAwayScore = predictedAwayScore;
    }

}
