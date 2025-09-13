package pl.xxx.demo.Prediction;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictionResponseDTOMapper {

    public static PredictionResponseDTO convertToPredictionResponseDTO(Prediction prediction) {
        return PredictionResponseDTO
                .builder()
                .gameId(prediction.getGame().getId())
                .predictedAwayScore(prediction.getPredictedAwayScore())
                .predictedHomeScore(prediction.getPredictedHomeScore())
                .homeTeam(prediction.getGame().getHomeTeam())
                .awayTeam(prediction.getGame().getAwayTeam())
                .build();
    }

    public static List<PredictionResponseDTO> convertToPredictionResponseDTO(List<Prediction> predictions) {
        return predictions.stream()
                .map(prediction -> convertToPredictionResponseDTO(prediction))
                .toList();
    }

}
