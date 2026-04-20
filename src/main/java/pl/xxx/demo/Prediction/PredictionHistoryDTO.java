package pl.xxx.demo.Prediction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionHistoryDTO {
    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private String homeCountryCode;
    private String awayCountryCode;
    private LocalDateTime gameDate;
    private String gameStatus;
    private Integer actualHomeScore;
    private Integer actualAwayScore;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
    private Integer points;
}
