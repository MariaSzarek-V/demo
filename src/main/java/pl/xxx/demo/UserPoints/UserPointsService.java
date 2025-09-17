package pl.xxx.demo.UserPoints;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameResult;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.User.User;

import java.util.List;

@AllArgsConstructor
@Service
public class UserPointsService {

    private final UserPointsRepository userPointsRepository;
    private final PredictionRepository predictionRepository;

    public void calculatePredictionForGame(Game game) {
        /**
         * DONE!!!!
         * medoda do podlliczenia punktow za predykcje
         * gdy admin zmieni status meczu na completed
         * do wykorzystania w adminGameController
         */
        List<Prediction> predictions = predictionRepository.findByGameId(game.getId());
        for (Prediction prediction : predictions) {
            int points = calculatePointsForGame(prediction);
            User user = prediction.getUser();
            UserPoints userPoints = new UserPoints();
            userPoints.setUser(user);
            userPoints.setPoints(points);
            userPoints.setPrediction(prediction);
            userPointsRepository.save(userPoints);
            prediction.setCalculated(true);
            predictionRepository.save(prediction);
        }
    }


    public int calculatePointsForGame(Prediction prediction) {
        int pointsForGame = 0;
        int predictedHomeScore = prediction.getPredictedHomeScore();
        int predictedAwayScore = prediction.getPredictedAwayScore();
        Game game = prediction.getGame();
        int gameHomeScore = game.getHomeScore();
        int gameAwayScore = game.getAwayScore();
        Enum<GameResult> gameResult = GameResult.getGameResult(gameHomeScore, gameAwayScore);

        Enum<GameResult> predictedResult = GameResult.getGameResult(predictedHomeScore, predictedAwayScore);

        if (predictedHomeScore == gameHomeScore && predictedAwayScore == gameAwayScore) {
            pointsForGame += 3;
        } else if (predictedResult == gameResult) {
            pointsForGame += 1;
        } else {
            pointsForGame += 0;
        }
        return pointsForGame;

    }
}
