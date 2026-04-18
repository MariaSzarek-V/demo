package pl.xxx.demo.RankingHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingHistoryChartDTO {
    private List<String> gameLabels;  // Etykiety meczów ["Polska 2:1 Niemcy", ...]
    private List<UserRankingHistoryDTO> allUsersHistory;  // Historia wszystkich użytkowników

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRankingHistoryDTO {
        private String username;
        private List<Integer> positions;  // Pozycje w rankingu [1, 2, 3, ...]
    }
}
