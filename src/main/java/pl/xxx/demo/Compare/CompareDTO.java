package pl.xxx.demo.Compare;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompareDTO {
    private UserComparisonDTO currentUser;
    private UserComparisonDTO comparedUser;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserComparisonDTO {
        private Long userId;
        private String username;
        private String avatarUrl;
        private Integer totalPoints;
        private Integer position;
        private List<PredictionComparisonDTO> predictions;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PredictionComparisonDTO {
        private Long gameId;
        private String homeTeam;
        private String awayTeam;
        private Integer actualHomeScore;
        private Integer actualAwayScore;
        private Integer predictedHomeScore;
        private Integer predictedAwayScore;
        private Integer points;
    }
}
