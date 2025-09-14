package pl.xxx.demo.GamePredictionResult;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GamePredictionResultDTO {

    private Long id;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private Integer predictedHomeScore;
    private Integer predictedAwayScore;



}
