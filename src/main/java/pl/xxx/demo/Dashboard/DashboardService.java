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
        try {
            // 1. Pobierz aktualną pozycję w rankingu
            List<RankingDTO> currentRanking = rankingService.getCurrentRanking();
            if (currentRanking == null) {
                currentRanking = new ArrayList<>();
            }
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

        for (RankingHistory history : rankingHistory) {
            // Pobierz game dla tego rankingu
            Game game = gameRepository.findById(history.getGameId()).orElse(null);
            if (game != null) {
                // Format: "Polska 2:1 Niemcy"
                String homeTeamName = game.getHomeCountry() != null ? game.getHomeCountry().getName() : "?";
                String awayTeamName = game.getAwayCountry() != null ? game.getAwayCountry().getName() : "?";
                String label = String.format("%s %d:%d %s",
                    homeTeamName,
                    game.getHomeScore() != null ? game.getHomeScore() : 0,
                    game.getAwayScore() != null ? game.getAwayScore() : 0,
                    awayTeamName);
                gameLabels.add(label);
            } else {
                gameLabels.add("Mecz");
            }
            rankingPositions.add(history.getPosition());
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
        if (userPredictions == null) {
            userPredictions = new ArrayList<>();
        }

        int predictedDraws = 0;
        int predictedWins = 0; // gospodarze lub goście
        
        // Dodatkowe statystyki dla karty "Twoje wyniki"
        java.util.Map<String, Integer> predictionFrequency = new java.util.HashMap<>();
        int exactMatches = 0;  // 3 pkt
        int partialMatches = 0; // 1 pkt
        int noMatches = 0;      // 0 pkt
        int almostPerfect = 0;  // zabrakło 1 bramki do 3 pkt
        
        for (Prediction prediction : userPredictions) {
            int homeScore = prediction.getPredictedHomeScore();
            int awayScore = prediction.getPredictedAwayScore();
            
            // Zlicz typy dla donuta
            if (homeScore == awayScore) {
                predictedDraws++;
            } else {
                predictedWins++; // zwycięstwo (bez znaczenia kto)
            }
            
            // Zlicz najczęściej typowany wynik
            String predictionKey = homeScore + ":" + awayScore;
            predictionFrequency.put(predictionKey, predictionFrequency.getOrDefault(predictionKey, 0) + 1);
            
            // Zlicz statystyki punktowe (tylko dla zakończonych meczów z punktami)
            Game game = prediction.getGame();
            if (game != null && game.getGameStatus() == GameStatus.FINISHED && 
                game.getHomeScore() != null && game.getAwayScore() != null) {
                
                // Znajdź punkty dla tej predykcji
                Integer points = null;
                for (UserPoints up : userPointsList) {
                    if (up.getPrediction() != null && up.getPrediction().getId().equals(prediction.getId())) {
                        points = up.getPoints();
                        break;
                    }
                }
                
                if (points != null) {
                    if (points == 3) {
                        exactMatches++;
                    } else if (points == 1) {
                        partialMatches++;
                        
                        // Sprawdź czy zabrakło 1 bramki do 3 pkt
                        int actualHome = game.getHomeScore();
                        int actualAway = game.getAwayScore();
                        int predHome = homeScore;
                        int predAway = awayScore;
                        
                        // Przypadki "prawie" - dokładny wynik był o 1 bramkę obok
                        if ((Math.abs(predHome - actualHome) == 1 && predAway == actualAway) ||
                            (predHome == actualHome && Math.abs(predAway - actualAway) == 1)) {
                            almostPerfect++;
                        }
                    } else if (points == 0) {
                        noMatches++;
                    }
                }
            }
        }
        
        // Znajdź najczęściej typowany wynik
        String mostFrequentPrediction = null;
        int mostFrequentCount = 0;
        for (java.util.Map.Entry<String, Integer> entry : predictionFrequency.entrySet()) {
            if (entry.getValue() > mostFrequentCount) {
                mostFrequentCount = entry.getValue();
                mostFrequentPrediction = entry.getKey();
            }
        }
        
        // 6a. Statystyki dla donuta - średnie typy innych graczy (bez zalogowanego użytkownika)
        List<Prediction> allOtherUsersPredictions = new ArrayList<>();
        try {
            allOtherUsersPredictions = predictionRepository.findAll().stream()
                    .filter(p -> p.getUser() != null && !p.getUser().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Jeśli wystąpi błąd, zostaw pustą listę
        }
        
        int totalOtherDraws = 0;
        int totalOtherWins = 0;
        
        for (Prediction prediction : allOtherUsersPredictions) {
            int homeScore = prediction.getPredictedHomeScore();
            int awayScore = prediction.getPredictedAwayScore();
            
            if (homeScore == awayScore) {
                totalOtherDraws++;
            } else {
                totalOtherWins++;
            }
        }
        
        // Średnie (zaokrąglone do całkowitych)
        int averagePredictedDraws = totalOtherDraws;
        int averagePredictedWins = totalOtherWins;
        
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
        if (allRankingHistory != null && !allRankingHistory.isEmpty()) {
            // Grupuj po użytkownikach
            allRankingHistory.stream()
                    .filter(rh -> rh.getUser() != null)
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
        }
        
        // 9. Pobierz najbliższe mecze
        List<DashboardStatsDTO.UpcomingGameDTO> upcomingGames = getUpcomingGames(user);
        
        // 10. Pobierz ostatnie mecze
        List<DashboardStatsDTO.RecentGameDTO> recentGames = getRecentGames(user);
        
        // 11. Przygotuj mini ranking
        List<DashboardStatsDTO.MiniRankingDTO> miniRanking = getMiniRanking(user, currentRanking);

        return DashboardStatsDTO.builder()
                .currentPosition(userRanking.getPosition())
                .positionChange(positionChange)
                .totalPoints(totalPoints)
                .lastPredictionPoints(lastPredictionPoints)
                .lastPredictedHomeScore(lastPredictedHomeScore)
                .lastPredictedAwayScore(lastPredictedAwayScore)
                // Dodatkowe statystyki
                .mostFrequentPrediction(mostFrequentPrediction)
                .mostFrequentCount(mostFrequentCount)
                .exactMatches(exactMatches)
                .partialMatches(partialMatches)
                .noMatches(noMatches)
                .almostPerfect(almostPerfect)
                .gameLabels(gameLabels != null ? gameLabels : new ArrayList<>())
                .rankingPositions(rankingPositions != null ? rankingPositions : new ArrayList<>())
                .allUsersRankingHistory(allUsersHistory != null ? allUsersHistory : new ArrayList<>())
                // Donut - uproszczone (2 kategorie)
                .predictedDraws(predictedDraws)
                .predictedWins(predictedWins)
                .averagePredictedDraws(averagePredictedDraws)
                .averagePredictedWins(averagePredictedWins)
                .actualDraws(actualDraws)
                .actualWins(actualWins)
                // Najbliższe mecze
                .upcomingGames(upcomingGames != null ? upcomingGames : new ArrayList<>())
                // Ostatnie mecze
                .recentGames(recentGames != null ? recentGames : new ArrayList<>())
                // Mini ranking
                .miniRanking(miniRanking != null ? miniRanking : new ArrayList<>())
                .build();
        } catch (Exception e) {
            // W przypadku błędu zwróć pusty dashboard
            return DashboardStatsDTO.builder()
                    .currentPosition(null)
                    .positionChange(0)
                    .totalPoints(0)
                    .lastPredictionPoints(0)
                    .lastPredictedHomeScore(null)
                    .lastPredictedAwayScore(null)
                    .mostFrequentPrediction(null)
                    .mostFrequentCount(0)
                    .exactMatches(0)
                    .partialMatches(0)
                    .noMatches(0)
                    .almostPerfect(0)
                    .gameLabels(new ArrayList<>())
                    .rankingPositions(new ArrayList<>())
                    .allUsersRankingHistory(new ArrayList<>())
                    .predictedDraws(0)
                    .predictedWins(0)
                    .averagePredictedDraws(0)
                    .averagePredictedWins(0)
                    .actualDraws(0)
                    .actualWins(0)
                    .upcomingGames(new ArrayList<>())
                    .recentGames(new ArrayList<>())
                    .miniRanking(new ArrayList<>())
                    .build();
        }
    }
    
    private List<DashboardStatsDTO.UpcomingGameDTO> getUpcomingGames(User user) {
        try {
            LocalDateTime now = LocalDateTime.now();

            // 1. Pobierz 2 najbliższe mecze SCHEDULED
            List<Game> initialGames = gameRepository.findByGameStatusAndGameDateAfterOrderByGameDateAsc(
                    GameStatus.SCHEDULED,
                    now,
                    PageRequest.of(0, 2)
            );

            if (initialGames == null || initialGames.isEmpty()) {
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
                    .homeTeam(game.getHomeCountry() != null ? game.getHomeCountry().getName() : null)
                    .awayTeam(game.getAwayCountry() != null ? game.getAwayCountry().getName() : null)
                    .homeCountryCode(game.getHomeCountry() != null ? game.getHomeCountry().getCode() : null)
                    .awayCountryCode(game.getAwayCountry() != null ? game.getAwayCountry().getCode() : null)
                    .gameDate(game.getGameDate())
                    .hasPrediction(prediction != null)
                    .predictionId(prediction != null ? prediction.getId() : null)
                    .predictedHomeScore(prediction != null ? prediction.getPredictedHomeScore() : null)
                    .predictedAwayScore(prediction != null ? prediction.getPredictedAwayScore() : null)
                    .build();
            
            upcomingGameDTOs.add(dto);
        }

        return upcomingGameDTOs;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<DashboardStatsDTO.RecentGameDTO> getRecentGames(User user) {
        try {
            // 1. Pobierz 2 ostatnie zakończone mecze
            List<Game> initialGames = gameRepository.findByGameStatusOrderByGameDateDesc(
                    GameStatus.FINISHED,
                    PageRequest.of(0, 2)
            );

            if (initialGames == null || initialGames.isEmpty()) {
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
                    .homeTeam(game.getHomeCountry() != null ? game.getHomeCountry().getName() : null)
                    .awayTeam(game.getAwayCountry() != null ? game.getAwayCountry().getName() : null)
                    .homeCountryCode(game.getHomeCountry() != null ? game.getHomeCountry().getCode() : null)
                    .awayCountryCode(game.getAwayCountry() != null ? game.getAwayCountry().getCode() : null)
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
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private List<DashboardStatsDTO.MiniRankingDTO> getMiniRanking(User user, List<RankingDTO> currentRanking) {
        try {
            if (currentRanking == null || currentRanking.isEmpty()) {
                return new ArrayList<>();
            }

            List<DashboardStatsDTO.MiniRankingDTO> miniRanking = new ArrayList<>();
            String currentUsername = user.getUsername();
            List<Integer> addedPositions = new ArrayList<>();

            // Znajdź pozycję użytkownika
            int userPosition = -1;
            for (int i = 0; i < currentRanking.size(); i++) {
                if (currentRanking.get(i).getUsername().equals(currentUsername)) {
                    userPosition = i;
                    break;
                }
            }

        // 1. Dodaj top 3
        for (int i = 0; i < Math.min(3, currentRanking.size()); i++) {
            RankingDTO rankingDTO = currentRanking.get(i);
            miniRanking.add(DashboardStatsDTO.MiniRankingDTO.builder()
                    .position(rankingDTO.getPosition())
                    .username(rankingDTO.getUsername())
                    .totalPoints(rankingDTO.getTotalPoints())
                    .isCurrentUser(rankingDTO.getUsername().equals(currentUsername))
                    .build());
            addedPositions.add(i);
        }

        // 2. Jeśli użytkownik nie jest w top 3, dodaj jego pozycję + sąsiadów
        if (userPosition > 2) {
            // Dodaj separator (null)
            miniRanking.add(null);

            // Dodaj jednego nad użytkownikiem (jeśli istnieje i nie został już dodany)
            if (userPosition > 0 && !addedPositions.contains(userPosition - 1)) {
                RankingDTO aboveUser = currentRanking.get(userPosition - 1);
                miniRanking.add(DashboardStatsDTO.MiniRankingDTO.builder()
                        .position(aboveUser.getPosition())
                        .username(aboveUser.getUsername())
                        .totalPoints(aboveUser.getTotalPoints())
                        .isCurrentUser(false)
                        .build());
                addedPositions.add(userPosition - 1);
            }

            // Dodaj samego użytkownika (jeśli nie został już dodany)
            if (!addedPositions.contains(userPosition)) {
                RankingDTO userRanking = currentRanking.get(userPosition);
                miniRanking.add(DashboardStatsDTO.MiniRankingDTO.builder()
                        .position(userRanking.getPosition())
                        .username(userRanking.getUsername())
                        .totalPoints(userRanking.getTotalPoints())
                        .isCurrentUser(true)
                        .build());
                addedPositions.add(userPosition);
            }

            // Dodaj jednego pod użytkownikiem (jeśli istnieje i nie został już dodany)
            if (userPosition < currentRanking.size() - 1 && !addedPositions.contains(userPosition + 1)) {
                RankingDTO belowUser = currentRanking.get(userPosition + 1);
                miniRanking.add(DashboardStatsDTO.MiniRankingDTO.builder()
                        .position(belowUser.getPosition())
                        .username(belowUser.getUsername())
                        .totalPoints(belowUser.getTotalPoints())
                        .isCurrentUser(false)
                        .build());
                addedPositions.add(userPosition + 1);
            }
        }

        return miniRanking;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
