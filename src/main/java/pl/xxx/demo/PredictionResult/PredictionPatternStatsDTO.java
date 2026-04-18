package pl.xxx.demo.PredictionResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionPatternStatsDTO {
    // User's prediction pattern
    private Integer myDraws;
    private Integer myWins;

    // Average of other players in the league
    private Integer othersDraws;
    private Integer othersWins;

    // Actual match results
    private Integer actualDraws;
    private Integer actualWins;

    // Total games counted
    private Integer totalGames;
}
