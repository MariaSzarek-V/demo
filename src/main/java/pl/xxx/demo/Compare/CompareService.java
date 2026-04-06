package pl.xxx.demo.Compare;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.Ranking.RankingDTO;
import pl.xxx.demo.RankingHistory.RankingHistory;
import pl.xxx.demo.RankingHistory.RankingHistoryRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPoints;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompareService {

    private final UserRepository userRepository;
    private final PredictionRepository predictionRepository;
    private final UserPointsRepository userPointsRepository;
    private final RankingHistoryRepository rankingHistoryRepository;

    public CompareDTO compareUsers(Long currentUserId, Long comparedUserId) {
        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
        User comparedUser = userRepository.findById(comparedUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Compared user not found"));

        CompareDTO.UserComparisonDTO currentUserData = buildUserComparison(currentUser);
        CompareDTO.UserComparisonDTO comparedUserData = buildUserComparison(comparedUser);

        CompareDTO compareDTO = new CompareDTO();
        compareDTO.setCurrentUser(currentUserData);
        compareDTO.setComparedUser(comparedUserData);

        return compareDTO;
    }

    private CompareDTO.UserComparisonDTO buildUserComparison(User user) {
        CompareDTO.UserComparisonDTO userComparison = new CompareDTO.UserComparisonDTO();
        userComparison.setUserId(user.getId());
        userComparison.setUsername(user.getUsername());
        userComparison.setAvatarUrl(user.getAvatarUrl());

        Integer totalPoints = userPointsRepository.sumPointsByUserId(user.getId());
        userComparison.setTotalPoints(totalPoints != null ? totalPoints : 0);

        // Pobierz pozycję z ostatniego rankingu
        List<RankingHistory> latestRanking = rankingHistoryRepository.findLatestRanking();
        Integer position = null;
        for (RankingHistory rh : latestRanking) {
            if (rh.getUser().getId().equals(user.getId())) {
                position = rh.getPosition();
                break;
            }
        }
        userComparison.setPosition(position);

        List<Prediction> predictions = predictionRepository.findByUserId(user.getId());
        List<UserPoints> userPointsList = userPointsRepository.findByUserIdOrderByIdDesc(user.getId());

        Map<Long, Integer> predictionPointsMap = userPointsList.stream()
                .collect(Collectors.toMap(
                        up -> up.getPrediction().getId(),
                        UserPoints::getPoints
                ));

        List<CompareDTO.PredictionComparisonDTO> predictionComparisons = new ArrayList<>();
        for (Prediction prediction : predictions) {
            // Only include finished games
            if (prediction.getGame().getGameStatus() != pl.xxx.demo.Enum.GameStatus.FINISHED) {
                continue;
            }

            CompareDTO.PredictionComparisonDTO predictionDTO = new CompareDTO.PredictionComparisonDTO();
            predictionDTO.setGameId(prediction.getGame().getId());
            predictionDTO.setHomeTeam(prediction.getGame().getHomeCountry().getName());
            predictionDTO.setAwayTeam(prediction.getGame().getAwayCountry().getName());
            predictionDTO.setHomeCountryCode(prediction.getGame().getHomeCountry().getCode());
            predictionDTO.setAwayCountryCode(prediction.getGame().getAwayCountry().getCode());
            predictionDTO.setGameDate(prediction.getGame().getGameDate().toString());
            predictionDTO.setActualHomeScore(prediction.getGame().getHomeScore());
            predictionDTO.setActualAwayScore(prediction.getGame().getAwayScore());
            predictionDTO.setPredictedHomeScore(prediction.getPredictedHomeScore());
            predictionDTO.setPredictedAwayScore(prediction.getPredictedAwayScore());
            predictionDTO.setPoints(predictionPointsMap.getOrDefault(prediction.getId(), 0));
            predictionComparisons.add(predictionDTO);
        }

        userComparison.setPredictions(predictionComparisons);

        return userComparison;
    }
}
