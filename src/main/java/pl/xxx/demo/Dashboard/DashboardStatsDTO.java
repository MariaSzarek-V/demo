package pl.xxx.demo.Dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    // Podstawowe statystyki
    private Integer currentPosition;        // Pozycja w rankingu
    private Integer positionChange;         // Zmiana pozycji (np. +2 lub -3)
    private Integer totalPoints;            // Suma wszystkich punktów
    private Integer lastPredictionPoints;   // Punkty za ostatni typ
    private Integer lastPredictedHomeScore; // Typowany wynik gospodarzy
    private Integer lastPredictedAwayScore; // Typowany wynik gości
    
    // Dane do wykresu pozycji w rankingu
    private List<String> gameLabels;        // Etykiety meczów ["Mecz 1", "Mecz 2", ...]
    private List<Integer> rankingPositions; // Pozycje w rankingu [8, 7, 5, 5, ...]
    
    // Dane wszystkich użytkowników do wykresu
    private List<UserRankingHistoryDTO> allUsersRankingHistory; // Historia wszystkich użytkowników
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRankingHistoryDTO {
        private String username;
        private List<Integer> positions;
    }
    
    // Dane do donuta - uproszczone (2 kategorie)
    private Integer predictedDraws;         // Ile razy typowałeś remis
    private Integer predictedWins;          // Ile razy typowałeś zwycięstwo (gospodarzy lub gości)
    
    private Integer actualDraws;            // Ile było remisów
    private Integer actualWins;             // Ile było zwycięstw (gospodarzy lub gości)
    
    // Najbliższe mecze
    private List<UpcomingGameDTO> upcomingGames; // Lista najbliższych meczów
    
    // Ostatnie mecze
    private List<RecentGameDTO> recentGames; // Lista ostatnich zakończonych meczów
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpcomingGameDTO {
        private Long gameId;
        private String homeTeam;
        private String awayTeam;
        private LocalDateTime gameDate;
        private Boolean hasPrediction;
        private Long predictionId;
        private Integer predictedHomeScore;
        private Integer predictedAwayScore;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentGameDTO {
        private Long gameId;
        private String homeTeam;
        private String awayTeam;
        private LocalDateTime gameDate;
        private Integer homeScore;           // Rzeczywisty wynik gospodarzy
        private Integer awayScore;           // Rzeczywisty wynik gości
        private Boolean hasPrediction;
        private Integer predictedHomeScore;  // Typ użytkownika
        private Integer predictedAwayScore;  // Typ użytkownika
        private Integer points;              // Punkty zdobyte za typ
    }
}
