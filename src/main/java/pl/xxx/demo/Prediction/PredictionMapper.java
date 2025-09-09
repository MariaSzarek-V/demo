package pl.xxx.demo.Prediction;


public class PredictionMapper {
    public static Prediction toEntity(PredictionDTO dto) {
        Prediction entity = new Prediction();
        entity.setPredictedHomeScore(dto.getPredictedHomeScore());
        entity.setPredictedAwayScore(dto.getPredictedAwayScore());
        return entity;
    }
}