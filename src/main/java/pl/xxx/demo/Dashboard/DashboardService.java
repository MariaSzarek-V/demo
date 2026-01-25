package pl.xxx.demo.Dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.Ranking.RankingDTO;
import pl.xxx.demo.Ranking.RankingService;
import pl.xxx.demo.RankingHistory.RankingHistory;
import pl.xxx.demo.RankingHistory.RankingHistoryRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.UserPoints.UserPoints;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final RankingService rankingService;
    private final UserPointsRepository userPointsRepository;
    private final RankingHistoryRepository rankingHistoryRepository;
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;
    
    public DashboardStatsDTO getUserDashboardStats(User user) {
        // 1. Pobierz aktualną pozycję w rankingu
        List<RankingDTO> currentRanking = rankingService.getCurrentRanking();
        RankingDTO userRanking = currentRanking.stream()
                .filter(r -> r.getUsername().equals(user.getUsername()))
                .findFirst()
                .orElse(new RankingDTO(null, user.getUsername(), 0));
        
        // 2. Pobierz sumę punktów
        Integer totalPoints = userPointsRepository.sumPointsByUserId(user.getId());
        if (totalPoints == null) {
            totalPoints = 0;
        }
        
        // 3. Pobierz punkty za ostatni typ i typowany wynik
        List<UserPoints> userPointsList = userPointsRepository.findByUserIdOrderByIdDesc(user.getId());
        Integer lastPredictionPoints = userPointsList.isEmpty() ? 0 : userPointsList.get(0).getPoints();
        Integer lastPredictedHomeScore = null;
        Integer lastPredictedAwayScore = null;
        
        if (!userPointsList.isEmpty()) {
            Prediction lastPrediction = userPointsList.get(0).getPrediction();
            if (lastPrediction != null) {
                lastPredictedHomeScore = lastPrediction.getPredictedHomeScore();
                lastPredictedAwayScore = lastPrediction.getPredictedAwayScore();
            }
        }
        
        // 4. Pobierz historię pozycji w rankingu
        List<RankingHistory> rankingHistory = rankingHistoryRepository
                .findByUserIdOrderByGameIdAsc(user.getId());
        
        // Przygotuj dane do wykresu
        List<String> gameLabels = new ArrayList<>();
        List<Integer> rankingPositions = new ArrayList<>();
        
        int gameNumber = 1;
        for (RankingHistory history : rankingHistory) {
            gameLabels.add("Mecz " + gameNumber);
            rankingPositions.add(history.getPosition());
            gameNumber++;
        }
        
        // Jeśli nie ma historii, użyj aktualnej pozycji
        if (rankingHistory.isEmpty() && userRanking.getPosition() != null) {
            gameLabels.add("Obecnie");
            rankingPositions.add(userRanking.getPosition());
        }
        
        // 5. Oblicz zmianę pozycji
        Integer positionChange = 0;
        if (rankingHistory.size() >= 2) {
            int lastPosition = rankingHistory.get(rankingHistory.size() - 1).getPosition();
            int previousPosition = rankingHistory.get(rankingHistory.size() - 2).getPosition();
            positionChange = previousPosition - lastPosition; // dodatnia jeśli poszedł w górę
        }
        
        // 6. Statystyki dla donuta - uproszczone (2 kategorie)
        List<Prediction> userPredictions = predictionRepository.findByUserId(user.getId());
        
        int predictedDraws = 0;
        int predictedWins = 0; // gospodarze lub goście
        
        for (Prediction prediction : userPredictions) {
            int homeScore = prediction.getPredictedHomeScore();
            int awayScore = prediction.getPredictedAwayScore();
            
            if (homeScore == awayScore) {
                predictedDraws++;
            } else {
                predictedWins++; // zwycięstwo (bez znaczenia kto)
            }
        }
        
        // 7. Statystyki dla donuta - rzeczywiste wyniki
        List<Long> gameIds = userPredictions.stream()
                .map(p -> p.getGame().getId())
                .collect(Collectors.toList());
        
        List<Game> finishedGames = gameIds.isEmpty() ? new ArrayList<>() : 
                gameRepository.findAllById(gameIds).stream()
                        .filter(g -> g.getGameStatus() == GameStatus.FINISHED)
                        .filter(g -> g.getHomeScore() != null && g.getAwayScore() != null)
                        .collect(Collectors.toList());
        
        int actualDraws = 0;
        int actualWins = 0; // gospodarze lub goście
        
        for (Game game : finishedGames) {
            int homeScore = game.getHomeScore();
            int awayScore = game.getAwayScore();
            
            if (homeScore == awayScore) {
                actualDraws++;
            } else {
                actualWins++; // zwycięstwo (bez znaczenia kto)
            }
        }
        
        // 8. Pobierz historię pozycji WSZYSTKICH użytkowników
        List<DashboardStatsDTO.UserRankingHistoryDTO> allUsersHistory = new ArrayList<>();
        List<RankingHistory> allRankingHistory = rankingHistoryRepository.findAll();
        
        // Grupuj po użytkownikach
        allRankingHistory.stream()
                .collect(Collectors.groupingBy(rh -> rh.getUser().getUsername()))
                .forEach((username, histories) -> {
                    List<Integer> positions = histories.stream()
                            .sorted(Comparator.comparingLong(RankingHistory::getGameId))
                            .map(RankingHistory::getPosition)
                            .collect(Collectors.toList());
                    
                    allUsersHistory.add(DashboardStatsDTO.UserRankingHistoryDTO.builder()
                            .username(username)
                            .positions(positions)
                            .build());
                });
        
        // 9. Pobierz najbliższe mecze
        List<DashboardStatsDTO.UpcomingGameDTO> upcomingGames = getUpcomingGames(user);
        
        // 10. Pobierz ostatnie mecze
        List<DashboardStatsDTO.RecentGameDTO> recentGames = getRecentGames(user);
        
        return DashboardStatsDTO.builder()
                .currentPosition(userRanking.getPosition())
                .positionChange(positionChange)
                .totalPoints(totalPoints)
                .lastPredictionPoints(lastPredictionPoints)
                .lastPredictedHomeScore(lastPredictedHomeScore)
                .lastPredictedAwayScore(lastPredictedAwayScore)
                .gameLabels(gameLabels)
                .rankingPositions(rankingPositions)
                .allUsersRankingHistory(allUsersHistory)
                // Donut - uproszczone (2 kategorie)
                .predictedDraws(predictedDraws)
                .predictedWins(predictedWins)
                .actualDraws(actualDraws)
                .actualWins(actualWins)
                // Najbliższe mecze
                .upcomingGames(upcomingGames)
                // Ostatnie mecze
                .recentGames(recentGames)
                .build();
    }
    
    private List<DashboardStatsDTO.UpcomingGameDTO> getUpcomingGames(User user) {
        LocalDateTime now = LocalDateTime.now();
        
        // 1. Pobierz 2 najbliższe mecze SCHEDULED
        List<Game> initialGames = gameRepository.findByGameStatusAndGameDateAfterOrderByGameDateAsc(
                GameStatus.SCHEDULED, 
                now, 
                PageRequest.of(0, 2)
        );
        
        if (initialGames.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Game> allUpcomingGames = new ArrayList<>(initialGames);
        
        // 2. Jeśli jest drugi mecz, sprawdź czy są inne mecze tego samego dnia
        if (initialGames.size() == 2) {
            Game secondGame = initialGames.get(1);
            LocalDateTime secondGameDate = secondGame.getGameDate();
            
            // Pobierz początek i koniec dnia drugiego meczu
            LocalDateTime startOfDay = secondGameDate.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = secondGameDate.toLocalDate().atTime(LocalTime.MAX);
            
            // Znajdź wszystkie mecze z tego dnia
            List<Game> gamesOnSameDay = gameRepository.findByGameStatusAndGameDateBetweenOrderByGameDateAsc(
                    GameStatus.SCHEDULED,
                    startOfDay,
                    endOfDay
            );
            
            // Dodaj mecze z tego samego dnia, które nie są już na liście
            for (Game game : gamesOnSameDay) {
                if (allUpcomingGames.stream().noneMatch(g -> g.getId().equals(game.getId()))) {
                    allUpcomingGames.add(game);
                }
            }
            
            // Posortuj wszystkie mecze według daty
            allUpcomingGames.sort(Comparator.comparing(Game::getGameDate));
        }
        
        // 3. Dla każdego meczu sprawdź czy user ma prediction
        List<DashboardStatsDTO.UpcomingGameDTO> upcomingGameDTOs = new ArrayList<>();
        
        for (Game game : allUpcomingGames) {
            Prediction prediction = predictionRepository.findByUserIdAndGameId(user.getId(), game.getId());
            
            DashboardStatsDTO.UpcomingGameDTO dto = DashboardStatsDTO.UpcomingGameDTO.builder()
                    .gameId(game.getId())
                    .homeTeam(game.getHomeTeam())
                    .awayTeam(game.getAwayTeam())
                    .gameDate(game.getGameDate())
                    .hasPrediction(prediction != null)
                    .predictionId(prediction != null ? prediction.getId() : null)
                    .predictedHomeScore(prediction != null ? prediction.getPredictedHomeScore() : null)
                    .predictedAwayScore(prediction != null ? prediction.getPredictedAwayScore() : null)
                    .build();
            
            upcomingGameDTOs.add(dto);
        }
        
        return upcomingGameDTOs;
    }
    
    private List<DashboardStatsDTO.RecentGameDTO> getRecentGames(User user) {
        // 1. Pobierz 2 ostatnie zakończone mecze
        List<Game> initialGames = gameRepository.findByGameStatusOrderByGameDateDesc(
                GameStatus.FINISHED,
                PageRequest.of(0, 2)
        );
        
        if (initialGames.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Game> allRecentGames = new ArrayList<>(initialGames);
        
        // 2. Jeśli jest drugi mecz, sprawdź czy są inne mecze z tego samego dnia
        if (initialGames.size() == 2) {
            Game secondGame = initialGames.get(1);
            LocalDateTime secondGameDate = secondGame.getGameDate();
            
            // Pobierz początek i koniec dnia drugiego meczu
            LocalDateTime startOfDay = secondGameDate.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = secondGameDate.toLocalDate().atTime(LocalTime.MAX);
            
            // Znajdź wszystkie mecze z tego dnia
            List<Game> gamesOnSameDay = gameRepository.findByGameStatusAndGameDateBetweenOrderByGameDateAsc(
                    GameStatus.FINISHED,
                    startOfDay,
                    endOfDay
            );
            
            // Dodaj mecze z tego samego dnia, które nie są już na liście
            for (Game game : gamesOnSameDay) {
                if (allRecentGames.stream().noneMatch(g -> g.getId().equals(game.getId()))) {
                    allRecentGames.add(game);
                }
            }
            
            // Posortuj wszystkie mecze według daty malejąco (najnowsze pierwsze)
            allRecentGames.sort((g1, g2) -> g2.getGameDate().compareTo(g1.getGameDate()));
        }
        
        // 3. Dla każdego meczu pobierz predykcję i punkty użytkownika
        List<DashboardStatsDTO.RecentGameDTO> recentGameDTOs = new ArrayList<>();
        
        for (Game game : allRecentGames) {
            Prediction prediction = predictionRepository.findByUserIdAndGameId(user.getId(), game.getId());
            
            // Znajdź punkty dla tej predykcji
            Integer points = null;
            if (prediction != null) {
                List<UserPoints> userPointsList = userPointsRepository.findByUserIdOrderByIdDesc(user.getId());
                for (UserPoints up : userPointsList) {
                    if (up.getPrediction() != null && up.getPrediction().getId().equals(prediction.getId())) {
                        points = up.getPoints();
                        break;
                    }
                }
            }
            
            DashboardStatsDTO.RecentGameDTO dto = DashboardStatsDTO.RecentGameDTO.builder()
                    .gameId(game.getId())
                    .homeTeam(game.getHomeTeam())
                    .awayTeam(game.getAwayTeam())
                    .gameDate(game.getGameDate())
                    .homeScore(game.getHomeScore())
                    .awayScore(game.getAwayScore())
                    .hasPrediction(prediction != null)
                    .predictedHomeScore(prediction != null ? prediction.getPredictedHomeScore() : null)
                    .predictedAwayScore(prediction != null ? prediction.getPredictedAwayScore() : null)
                    .points(points)
                    .build();
            
            recentGameDTOs.add(dto);
        }
        
        return recentGameDTOs;
    }
}
