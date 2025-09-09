package pl.xxx.demo.Prediction;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Setter
@Getter
public class PredictionDTO {
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;
}
