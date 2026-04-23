package pl.xxx.demo.Prediction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.UserPoints.UserPoints;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PredictionResponseDTOMapper {

    private final UserPointsRepository userPointsRepository;

    public PredictionResponseDTO convertToPredictionResponseDTO(Prediction prediction) {
        // Find points for this prediction
        Integer points = null;
        if (prediction.getGame().getGameStatus() == GameStatus.FINISHED) {
            points = userPointsRepository.findByPredictionId(prediction.getId())
                    .map(UserPoints::getPoints)
                    .orElse(null);
        }

        return PredictionResponseDTO
                .builder()
                .id(prediction.getId())
                .gameId(prediction.getGame().getId())
                .predictedAwayScore(prediction.getPredictedAwayScore())
                .predictedHomeScore(prediction.getPredictedHomeScore())
                .homeTeam(prediction.getGame().getHomeCountry() != null ? prediction.getGame().getHomeCountry().getName() : null)
                .awayTeam(prediction.getGame().getAwayCountry() != null ? prediction.getGame().getAwayCountry().getName() : null)
                .points(points)
                .build();
    }

    public List<PredictionResponseDTO> convertToPredictionResponseDTO(List<Prediction> predictions) {
        return predictions.stream()
                .map(prediction -> convertToPredictionResponseDTO(prediction))
                .toList();
    }

}
