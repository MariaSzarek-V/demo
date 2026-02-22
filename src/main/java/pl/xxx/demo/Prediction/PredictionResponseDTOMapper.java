package pl.xxx.demo.Prediction;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictionResponseDTOMapper {

    public static PredictionResponseDTO convertToPredictionResponseDTO(Prediction prediction) {
        return PredictionResponseDTO
                .builder()
                .id(prediction.getId())
                .gameId(prediction.getGame().getId())
                .predictedAwayScore(prediction.getPredictedAwayScore())
                .predictedHomeScore(prediction.getPredictedHomeScore())
                .homeTeam(prediction.getGame().getHomeCountry() != null ? prediction.getGame().getHomeCountry().getName() : null)
                .awayTeam(prediction.getGame().getAwayCountry() != null ? prediction.getGame().getAwayCountry().getName() : null)
                .build();
    }

    public static List<PredictionResponseDTO> convertToPredictionResponseDTO(List<Prediction> predictions) {
        return predictions.stream()
                .map(prediction -> convertToPredictionResponseDTO(prediction))
                .toList();
    }

}
