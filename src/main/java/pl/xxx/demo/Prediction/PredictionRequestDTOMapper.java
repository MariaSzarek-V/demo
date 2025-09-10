package pl.xxx.demo.Prediction;

import org.springframework.stereotype.Component;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.User.User;

@Component
public class PredictionRequestDTOMapper {

    public static Prediction convertToPrediction(PredictionRequestDTO dto, Game game, User user) {
        return Prediction
                .builder()
                .predictedAwayScore(dto.getPredictedAwayScore())
                .predictedHomeScore(dto.getPredictedHomeScore())
                .game(game)
                .user(user)
                .build();
    }
}
