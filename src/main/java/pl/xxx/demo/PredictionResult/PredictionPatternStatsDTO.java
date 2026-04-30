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
    // Percentages (0-100) for chart bars
    private Double myDrawPercent;
    private Double myWinPercent;

    private Double othersDrawPercent;
    private Double othersWinPercent;

    private Double actualDrawPercent;
    private Double actualWinPercent;

    // Raw totals for tooltip context
    private Integer myTotal;
    private Integer othersPlayerCount;
    private Integer actualTotal;
}
