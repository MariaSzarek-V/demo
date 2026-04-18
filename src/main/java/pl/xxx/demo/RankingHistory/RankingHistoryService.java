package pl.xxx.demo.RankingHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.DuplicateRankingEntryException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.League.League;
import pl.xxx.demo.League.LeagueRepository;
import pl.xxx.demo.League.UserLeague;
import pl.xxx.demo.Ranking.RankingDTO;
import pl.xxx.demo.Ranking.RankingService;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RankingHistoryService {

    private final UserRepository userRepository;
    private final RankingHistoryRepository rankingHistoryRepository;
    private final RankingService rankingService;
    private final GameRepository gameRepository;
    private final UserPointsRepository userPointsRepository;
    private final LeagueRepository leagueRepository;

    public List<RankingHistoryDTO> getLastRankingHistory() {
        Optional<RankingHistory> oneLastRankingHistory = rankingHistoryRepository.findFirstByOrderByIdDesc();
        if (oneLastRankingHistory.isPresent()) {
            List<RankingHistory> lastRankingHistoryList = rankingHistoryRepository.findByGameIdOrderByPositionAsc(oneLastRankingHistory.get().getGameId());

             return lastRankingHistoryList.stream()
                    .map(rh -> new RankingHistoryDTO(
                            rh.getGameId(),
                            rh.getUser().getId(),
                            rh.getUser().getUsername(),
                            rh.getUser().getAvatarUrl(),
                            rh.getTotalPoints(),
                            rh.getPosition(),
                            rh.getPositionChange()
                    )).toList();
        }
        return Collections.emptyList();
    }

    public List<RankingHistoryDTO> getLastRankingHistoryByLeague(Long leagueId) {
        Optional<RankingHistory> oneLastRankingHistory = rankingHistoryRepository.findFirstByOrderByIdDesc();
        if (oneLastRankingHistory.isPresent()) {
            // Pobierz ranking_history tylko dla danej ligi (filtruj po league_id)
            List<RankingHistory> lastRankingHistoryList = rankingHistoryRepository
                    .findByGameIdAndLeagueIdOrderByPositionAsc(oneLastRankingHistory.get().getGameId(), leagueId);

            return lastRankingHistoryList.stream()
                    .map(rh -> new RankingHistoryDTO(
                            rh.getGameId(),
                            rh.getUser().getId(),
                            rh.getUser().getUsername(),
                            rh.getUser().getAvatarUrl(),
                            rh.getTotalPoints(),
                            rh.getPosition(),
                            rh.getPositionChange()
                    )).toList();
        }
        return Collections.emptyList();
    }

    public void saveCurrentRankingToHistory(Long gameId){
        List<RankingDTO> currentRanking = rankingService.getCurrentRanking();
        if (rankingHistoryRepository.existsByGameId(gameId)) {
            throw new DuplicateRankingEntryException();
        }
        for (int i=0; i<currentRanking.size(); i++){
            RankingDTO rankingDTO = currentRanking.get(i);
            User user = userRepository.findByUsername(rankingDTO.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Integer positionChange = calculatePositionChange(gameId, user, rankingDTO.getPosition());

            RankingHistory historyEntry = new RankingHistory();
            historyEntry.setGameId(gameId);
            historyEntry.setUser(user);
            historyEntry.setTotalPoints(rankingDTO.getTotalPoints());
            historyEntry.setPosition(rankingDTO.getPosition());
            historyEntry.setPositionChange(positionChange);

            rankingHistoryRepository.save(historyEntry);
        }
    }

    private Integer calculatePositionChange(Long gameId, User user, Integer currentPosition) {
        Optional<Long> previousGameId = rankingHistoryRepository.findPreviousGameId(gameId);
        if (previousGameId.isEmpty()){
            return 0;
        }
        Optional<RankingHistory> lastHistory = rankingHistoryRepository.findByGameIdAndUser(previousGameId.get(), user);
        if (lastHistory.isPresent()) {
            Integer lastPosition = lastHistory.get().getPosition();
            return lastPosition - currentPosition;
        }
        return 0;
    }

    /**
     * Pobiera pełną historię rankingu dla wszystkich użytkowników - dla wykresu
     */
    public RankingHistoryChartDTO getAllUsersRankingHistoryForChart() {
        List<RankingHistory> allRankingHistory = rankingHistoryRepository.findAll();

        if (allRankingHistory == null || allRankingHistory.isEmpty()) {
            return RankingHistoryChartDTO.builder()
                    .gameLabels(new ArrayList<>())
                    .allUsersHistory(new ArrayList<>())
                    .build();
        }

        // 1. Pobierz wszystkie unikalne game_id (posortowane)
        List<Long> allGameIds = allRankingHistory.stream()
                .map(RankingHistory::getGameId)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // 2. Dla każdego meczu utwórz etykietę
        List<String> gameLabels = new ArrayList<>();
        for (Long gameId : allGameIds) {
            Optional<Game> gameOpt = gameRepository.findById(gameId);
            if (gameOpt.isPresent()) {
                Game game = gameOpt.get();
                String homeTeamName = game.getHomeCountry() != null ? game.getHomeCountry().getName() : "?";
                String awayTeamName = game.getAwayCountry() != null ? game.getAwayCountry().getName() : "?";
                String label = String.format("%s %d:%d %s",
                        homeTeamName,
                        game.getHomeScore() != null ? game.getHomeScore() : 0,
                        game.getAwayScore() != null ? game.getAwayScore() : 0,
                        awayTeamName);
                gameLabels.add(label);
            } else {
                gameLabels.add("Mecz #" + gameId);
            }
        }

        // 3. Grupuj historię po użytkownikach
        Map<String, List<RankingHistory>> historiesByUser = allRankingHistory.stream()
                .filter(rh -> rh.getUser() != null)
                .collect(Collectors.groupingBy(rh -> rh.getUser().getUsername()));

        // 4. Dla każdego użytkownika utwórz listę pozycji dla WSZYSTKICH meczów
        List<RankingHistoryChartDTO.UserRankingHistoryDTO> allUsersHistory = new ArrayList<>();

        historiesByUser.forEach((username, histories) -> {
            List<Integer> positions = new ArrayList<>();

            // Dla każdego gameId w allGameIds, znajdź pozycję użytkownika
            for (Long gameId : allGameIds) {
                Optional<RankingHistory> historyForGame = histories.stream()
                        .filter(rh -> rh.getGameId().equals(gameId))
                        .findFirst();

                if (historyForGame.isPresent()) {
                    positions.add(historyForGame.get().getPosition());
                } else {
                    // Jeśli użytkownik nie ma wpisu dla tego meczu, użyj null
                    positions.add(null);
                }
            }

            allUsersHistory.add(RankingHistoryChartDTO.UserRankingHistoryDTO.builder()
                    .username(username)
                    .positions(positions)
                    .build());
        });

        return RankingHistoryChartDTO.builder()
                .gameLabels(gameLabels)
                .allUsersHistory(allUsersHistory)
                .build();
    }

    /**
     * Odbudowuje całą historię rankingu chronologicznie dla wszystkich zakończonych meczów
     */
    @Transactional
    public void rebuildRankingHistory() {
        // 1. Wyczyść istniejącą historię
        rankingHistoryRepository.deleteAll();

        // 2. Pobierz wszystkie zakończone mecze posortowane chronologicznie (po ID)
        List<Game> finishedGames = gameRepository.findByGameStatusOrderByIdAsc(GameStatus.FINISHED);

        if (finishedGames.isEmpty()) {
            return;
        }

        // 3. Pobierz wszystkie ligi
        List<League> allLeagues = leagueRepository.findAll();

        // 4. Dla każdego meczu i każdej ligi oblicz ranking i zapisz do historii
        for (Game game : finishedGames) {
            for (League league : allLeagues) {
                // Pobierz użytkowników należących do tej ligi
                List<User> leagueUsers = userRepository.findUsersByLeagueId(league.getId());

                if (leagueUsers.isEmpty()) {
                    continue;
                }

                List<RankingDTO> rankingForGame = new ArrayList<>();

                // Oblicz ranking po tym meczu (sumuj punkty do tego meczu włącznie)
                for (User user : leagueUsers) {
                    Integer totalPoints = userPointsRepository.sumPointsByUserIdUpToGame(user.getId(), game.getId());
                    if (totalPoints == null) {
                        totalPoints = 0;
                    }
                    rankingForGame.add(new RankingDTO(null, user.getUsername(), totalPoints));
                }

                // Posortuj ranking
                rankingForGame.sort((a, b) -> b.getTotalPoints() - a.getTotalPoints());

                // Przypisz pozycje
                for (int i = 0; i < rankingForGame.size(); i++) {
                    rankingForGame.get(i).setPosition(i + 1);
                }

                // Zapisz historię dla tego meczu i ligi
                for (RankingDTO rankingDTO : rankingForGame) {
                    User user = userRepository.findByUsername(rankingDTO.getUsername())
                            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + rankingDTO.getUsername()));

                    // Oblicz zmianę pozycji (porównaj z poprzednim meczem w tej samej lidze)
                    Integer positionChange = calculatePositionChangeForLeague(game.getId(), user, league, rankingDTO.getPosition());

                    RankingHistory historyEntry = new RankingHistory();
                    historyEntry.setGameId(game.getId());
                    historyEntry.setUser(user);
                    historyEntry.setLeague(league);
                    historyEntry.setTotalPoints(rankingDTO.getTotalPoints());
                    historyEntry.setPosition(rankingDTO.getPosition());
                    historyEntry.setPositionChange(positionChange);

                    rankingHistoryRepository.save(historyEntry);
                }
            }
        }
    }

    private Integer calculatePositionChangeForLeague(Long gameId, User user, League league, Integer currentPosition) {
        Optional<Long> previousGameId = rankingHistoryRepository.findPreviousGameId(gameId);
        if (previousGameId.isEmpty()){
            return 0;
        }
        Optional<RankingHistory> lastHistory = rankingHistoryRepository.findByGameIdAndUserAndLeague(previousGameId.get(), user, league);
        if (lastHistory.isPresent()) {
            Integer lastPosition = lastHistory.get().getPosition();
            return lastPosition - currentPosition;
        }
        return 0;
    }
}
