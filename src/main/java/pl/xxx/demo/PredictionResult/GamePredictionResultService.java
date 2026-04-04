package pl.xxx.demo.PredictionResult;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GamePredictionResultService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GamePredictionResultRepository gamePredictionResultRepository;


    public List<GamePredictionResultResponseDTO> getAllPredictionsByUserWithResult() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return gamePredictionResultRepository.findAllPredictionsByUserWithResults(currentUser.getId())
                .stream()
                .map(dto -> {
                    GamePredictionResultResponseDTO responseDTO = new GamePredictionResultResponseDTO();
                    responseDTO.setUsername(dto.getUsername());
                    responseDTO.setHomeTeam(dto.getHomeTeam());
                    responseDTO.setAwayTeam(dto.getAwayTeam());
                    responseDTO.setHomeCountryCode(dto.getHomeCountryCode());
                    responseDTO.setAwayCountryCode(dto.getAwayCountryCode());
                    responseDTO.setHomeScore(dto.getHomeScore());
                    responseDTO.setAwayScore(dto.getAwayScore());
                    responseDTO.setPredictedHomeScore(dto.getPredictedHomeScore());
                    responseDTO.setPredictedAwayScore(dto.getPredictedAwayScore());
                    responseDTO.setPoints(dto.getPoints());
                    responseDTO.setGameDate(dto.getGameDate() != null ? dto.getGameDate().toString() : null);
                    return responseDTO;
                })
                .toList();
    }

    public List<GamePredictionResultResponseDTO> getGameWithPredictionsAndPointsB(Long gameId) {

        gameRepository.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("Game with id " + gameId + " not found"));

        return gamePredictionResultRepository.findGameWithAllUserPredictionsAndPoints(gameId)
                .stream()
                .map(dto -> {
                    GamePredictionResultResponseDTO responseDTO = new GamePredictionResultResponseDTO();
                    responseDTO.setUsername(dto.getUsername());
                    responseDTO.setHomeTeam(dto.getHomeTeam());
                    responseDTO.setAwayTeam(dto.getAwayTeam());
                    responseDTO.setHomeCountryCode(dto.getHomeCountryCode());
                    responseDTO.setAwayCountryCode(dto.getAwayCountryCode());
                    responseDTO.setHomeScore(dto.getHomeScore());
                    responseDTO.setAwayScore(dto.getAwayScore());
                    responseDTO.setPredictedHomeScore(dto.getPredictedHomeScore());
                    responseDTO.setPredictedAwayScore(dto.getPredictedAwayScore());
                    responseDTO.setPoints(dto.getPoints());
                    responseDTO.setGameDate(dto.getGameDate() != null ? dto.getGameDate().toString() : null);
                    return responseDTO;
                })
                .toList();
    }

    /**
     * FOR FRONTEN
     *
     */
    public List<GamePredictionResultDTO> getAllGamesWithUserPredictionsAndPoints() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return gamePredictionResultRepository.findAllGamesWithUserPedictionAndPoints(currentUser.getId());
    }

    /**
     * FOR FRONTEN
     *
     */
    public List<GamePredictionResultDTO> getGameWithPredictionsAndPoints(Long gameId) {
        gameRepository.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("Game with id " + gameId + " not found"));
        return gamePredictionResultRepository.findGameWithAllUserPredictionsAndPoints(gameId);
    }
    
    /**
     * Oblicza statystyki dla danego meczu
     */
    public GameStatsDTO getGameStats(Long gameId) {
        List<GamePredictionResultDTO> results = getGameWithPredictionsAndPoints(gameId);
        
        if (results.isEmpty()) {
            return GameStatsDTO.builder()
                    .exactScores(0)
                    .correctOutcome(0)
                    .incorrect(0)
                    .exactScoresPercent(0.0)
                    .correctOutcomePercent(0.0)
                    .incorrectPercent(0.0)
                    .mostCommonPredictions(new java.util.ArrayList<>())
                    .mostCommonCount(0)
                    .homeTeamWinPredictions(0)
                    .awayTeamWinPredictions(0)
                    .drawPredictions(0)
                    .totalPredictions(0)
                    .build();
        }
        
        int exactScores = 0;
        int correctOutcome = 0;
        int incorrect = 0;
        int homeWins = 0;
        int awayWins = 0;
        int draws = 0;
        
        // Mapa do zliczania najczęściej typowanych wyników
        java.util.Map<String, Integer> predictionCounts = new java.util.HashMap<>();
        
        for (GamePredictionResultDTO result : results) {
            Integer points = result.getPoints();
            
            if (points != null) {
                if (points == 3) {
                    exactScores++;
                } else if (points == 1) {
                    correctOutcome++;
                } else {
                    incorrect++;
                }
            }
            
            // Zliczaj typowane wyniki i rozstrzygnięcia
            if (result.getPredictedHomeScore() != null && result.getPredictedAwayScore() != null) {
                String prediction = result.getPredictedHomeScore() + "-" + result.getPredictedAwayScore();
                predictionCounts.put(prediction, predictionCounts.getOrDefault(prediction, 0) + 1);
                
                // Zliczaj rozstrzygnięcia
                int homeScore = result.getPredictedHomeScore();
                int awayScore = result.getPredictedAwayScore();
                
                if (homeScore > awayScore) {
                    homeWins++;
                } else if (awayScore > homeScore) {
                    awayWins++;
                } else {
                    draws++;
                }
            }
        }
        
        // Znajdź najwyższą liczbę wystąpień
        Integer maxCount = 0;
        for (Integer count : predictionCounts.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        // Zbierz wszystkie wyniki z najwyższą liczbą wystąpień
        java.util.List<String> mostCommonPredictions = new java.util.ArrayList<>();
        for (java.util.Map.Entry<String, Integer> entry : predictionCounts.entrySet()) {
            if (entry.getValue().equals(maxCount)) {
                mostCommonPredictions.add(entry.getKey());
            }
        }
        
        // Sortuj wyniki alfabetycznie dla spójności
        java.util.Collections.sort(mostCommonPredictions);
        
        int total = exactScores + correctOutcome + incorrect;
        
        // Oblicz procenty
        double exactPercent = total > 0 ? (exactScores * 100.0 / total) : 0.0;
        double correctPercent = total > 0 ? (correctOutcome * 100.0 / total) : 0.0;
        double incorrectPercent = total > 0 ? (incorrect * 100.0 / total) : 0.0;
        
        return GameStatsDTO.builder()
                .exactScores(exactScores)
                .correctOutcome(correctOutcome)
                .incorrect(incorrect)
                .exactScoresPercent(Math.round(exactPercent * 10.0) / 10.0)
                .correctOutcomePercent(Math.round(correctPercent * 10.0) / 10.0)
                .incorrectPercent(Math.round(incorrectPercent * 10.0) / 10.0)
                .mostCommonPredictions(mostCommonPredictions)
                .mostCommonCount(maxCount)
                .homeTeamWinPredictions(homeWins)
                .awayTeamWinPredictions(awayWins)
                .drawPredictions(draws)
                .totalPredictions(total)
                .build();
    }
}
