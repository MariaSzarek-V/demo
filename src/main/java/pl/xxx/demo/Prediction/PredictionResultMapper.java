package pl.xxx.demo.Prediction;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.User.User;
import pl.xxx.demo.UserPoints.UserPoints;

import java.util.Optional;
import pl.xxx.demo.UserPoints.UserPointsRepository;

@Component // KLUCZOWE -> sprawia, że to SpringBean, ktory mozna wstrzykiwac
@RequiredArgsConstructor //LOMBOK -generuje konstruktor dla wszystkich pol final
public class PredictionResultMapper {

    private final UserPointsRepository userPointsRepository;

    public PredictionResultDTO convertToPredictionResultDTO(Prediction prediction) {
        PredictionResultDTO dto = new PredictionResultDTO();

        dto.setUsername(Optional.ofNullable(prediction.getUser())
                .map(User::getUsername).orElse("no user"));
        dto.setHomeTeam(Optional.ofNullable(prediction.getGame())
                .map(Game::getHomeTeam).orElse("no game found"));
        dto.setAwayTeam(Optional.ofNullable(prediction.getGame())
                .map(Game::getAwayTeam).orElse("no game found"));
        dto.setHomeScore(Optional.ofNullable(prediction.getGame())
                .map(Game::getHomeScore).orElse(-1));
        dto.setAwayScore(Optional.ofNullable(prediction.getGame())
                .map(Game::getAwayScore).orElse(-1));
        dto.setPredictedHomeScore(prediction.getPredictedHomeScore());
        dto.setPredictedAwayScore(prediction.getPredictedAwayScore());

        Integer userPoints = userPointsRepository.findByPrediction(prediction)
                .map(UserPoints::getPoints)
                .orElse(-1);
        dto.setPoints(userPoints);

        return dto;
    }
}
