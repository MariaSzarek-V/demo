package pl.xxx.demo.PredictionResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameStatsDTO {
    // Accuracy statistics
    private Integer exactScores;        // Liczba dokładnych trafień (3 pkt)
    private Integer correctOutcome;     // Liczba trafień rozstrzygnięcia (1 pkt)
    private Integer incorrect;          // Liczba nietrafień (0 pkt)
    
    // Percentages for chart
    private Double exactScoresPercent;
    private Double correctOutcomePercent;
    private Double incorrectPercent;
    
    // Most common predictions (może być kilka z tą samą liczbą)
    private List<String> mostCommonPredictions;  // Lista najczęściej typowanych wyników np. ["2-1", "1-0"]
    private Integer mostCommonCount;              // Ile razy wystąpiły
    
    // Outcome statistics
    private Integer homeTeamWinPredictions;  // Ile osób typowało zwycięstwo gospodarzy
    private Integer awayTeamWinPredictions;  // Ile osób typowało zwycięstwo gości
    private Integer drawPredictions;         // Ile osób typowało remis
    
    // Total predictions
    private Integer totalPredictions;
}
