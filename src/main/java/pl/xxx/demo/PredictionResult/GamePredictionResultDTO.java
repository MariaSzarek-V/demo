package pl.xxx.demo.PredictionResult;

import lombok.*;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class GamePredictionResultDTO {

    private Long userId;
    private String username;
    private Long gameId;
    private Long predictionId;
    private Long pointsId;

    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime gameDate;
    private GameStatus gameStatus;

    private Integer predictedHomeScore;
    private Integer predictedAwayScore;

    private Integer points;

    // Konstruktor dla mojego SELECT w JPQL
    public GamePredictionResultDTO(
            Long userId,
            String username,
            Long gameId,
            String homeTeam,
            String awayTeam,
            Integer homeScore,
            Integer awayScore,
            LocalDateTime gameDate,
            GameStatus gameStatus,
            Integer predictedHomeScore,
            Integer predictedAwayScore,
            Long predictionId,
            Integer points,
            Long pointsId
    ) {
        this.userId = userId;
        this.username = username;
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.gameDate = gameDate;
        this.gameStatus = gameStatus;
        this.predictedHomeScore = predictedHomeScore;
        this.predictedAwayScore = predictedAwayScore;
        this.predictionId = predictionId;
        this.points = points;
        this.pointsId = pointsId;
    }
    public boolean isFinished() {
        return GameStatus.FINISHED.equals(this.gameStatus);
    }
}