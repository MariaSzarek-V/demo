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
    
    // Dodatkowe statystyki dla karty "Twoje wyniki"
    private String mostFrequentPrediction;  // Najczęściej typowany wynik (np. "2:1")
    private Integer mostFrequentCount;       // Ile razy został wytypowany
    private Integer exactMatches;            // Liczba dokładnych trafień (3 pkt)
    private Integer partialMatches;          // Liczba częściowych trafień (1 pkt)
    private Integer noMatches;               // Liczba nietrafień (0 pkt)
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
    
    private Integer averagePredictedDraws;  // Średnia typów remisów innych graczy
    private Integer averagePredictedWins;   // Średnia typów zwycięstw innych graczy
    
    private Integer actualDraws;            // Ile było remisów
    private Integer actualWins;             // Ile było zwycięstw (gospodarzy lub gości)
    
    // Najbliższe mecze
    private List<UpcomingGameDTO> upcomingGames; // Lista najbliższych meczów
    
    // Ostatnie mecze
    private List<RecentGameDTO> recentGames; // Lista ostatnich zakończonych meczów
    
    // Mini ranking
    private List<MiniRankingDTO> miniRanking; // Top 3 + użytkownik + sąsiedzi
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpcomingGameDTO {
        private Long gameId;
        private String homeTeam;
        private String awayTeam;
        private String homeCountryCode;
        private String awayCountryCode;
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
        private String homeCountryCode;
        private String awayCountryCode;
        private LocalDateTime gameDate;
        private Integer homeScore;           // Rzeczywisty wynik gospodarzy
        private Integer awayScore;           // Rzeczywisty wynik gości
        private Boolean hasPrediction;
        private Integer predictedHomeScore;  // Typ użytkownika
        private Integer predictedAwayScore;  // Typ użytkownika
        private Integer points;              // Punkty zdobyte za typ
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MiniRankingDTO {
        private Integer position;
        private String username;
        private Integer totalPoints;
        private Boolean isCurrentUser;       // Czy to zalogowany użytkownik
    }
}
