package pl.xxx.demo.Prediction;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;

@AllArgsConstructor
@Component
public class PredictionRequestDTOMapper {

    private final GameRepository gameRepository;


    public static Prediction convertToPrediction(PredictionRequestDTO dto, Game game, User user) {
        return Prediction
                .builder()
                .predictedAwayScore(dto.getPredictedAwayScore())
                .predictedHomeScore(dto.getPredictedHomeScore())
                .game(game)
                .user(user)
                .build();
    }



    public void updatePredictionIfNotNull(Prediction prediction, PredictionRequestDTO dto) {
        if (dto.getPredictedAwayScore() != null) {
            prediction.setPredictedAwayScore(dto.getPredictedAwayScore());
        }
        if (dto.getPredictedHomeScore() != null) {
            prediction.setPredictedHomeScore(dto.getPredictedHomeScore());
        }
        if (dto.getGameId() != null) {
            Game game = gameRepository.findById(dto.getGameId())
                    .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + dto.getGameId()));
            prediction.setGame(game);
        }
    }
}
