package pl.xxx.demo.UserPoints;

import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameResult;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserPointsService {

    private final UserPointsRepository userPointsRepository;
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;

    public UserPointsService(UserPointsRepository userPointsRepository, PredictionRepository predictionRepository, GameRepository gameRepository) {
        this.userPointsRepository = userPointsRepository;
        this.predictionRepository = predictionRepository;
        this.gameRepository = gameRepository;
    }
//
//    public List<UserPoints> getAll() {
//        return userPointsRepository.findAll();
//    }
//
//    public Optional<UserPoints> get(Long id) {
//        return userPointsRepository.findById(id);
//    }

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

    public void calculateAllUncalculatedPredictions() {
        /**
         * to jest to samo co calculatePredictionForGame
         * czyli funkcja wyzej , przemyslec
         * czy ja tego w ogole potrzebuje
         */
        List<Prediction> allUncalculatedPredictions = predictionRepository.findByCalculatedFalse();

        for (Prediction prediction : allUncalculatedPredictions) {
            int points = calculatePointsForGame(prediction);
            User user = prediction.getUser();
            UserPoints userPoints = new UserPoints();
            userPoints.setPoints(points);
            userPoints.setUser(user);
            userPoints.setPrediction(prediction);
            userPointsRepository.save(userPoints);

            prediction.setCalculated(true);
            predictionRepository.save(prediction);

        }
    }

//    STARE CALCULATE
//    public UserPoints add(UserPoints userPoints) {
//        Prediction prediction = predictionRepository.findById(userPoints.getPrediction().getId())
//                .orElseThrow(() -> new RuntimeException("Prediction not found"));
//        Game game = gameRepository.findById(userPoints.getGame().getId())
//                .orElseThrow(() -> new RuntimeException("Game not found"));
//        int points = calculatePointsForGame(prediction, game);
//        userPoints.setPoints(points);
//        return userPointsRepository.save(userPoints);
//    }

    public UserPoints update(Long id, UserPoints userPoints) {
        UserPoints existingUserPoints = userPointsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such user points to update"));
        existingUserPoints.setPoints(userPoints.getPoints());
        return userPointsRepository.save(userPoints);
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

        System.out.println("game result: " + gameResult.toString());
        System.out.println("predicted home: " + predictedResult.toString());
        return pointsForGame;


//STARE
//    public int calculatePointsForGame(Prediction prediction, Game game) {
//        int pointsForGame = 0;
//        int predictedHomeScore = prediction.getPredictedHomeScore();
//        int predictedAwayScore = prediction.getPredictedAwayScore();
//        int gameHomeScore = game.getHomeScore();
//        int gameAwayScore = game.getAwayScore();
//        Enum<GameResult> gameResult= GameResult.getGameResult(gameHomeScore, gameAwayScore);
//
//        Enum<GameResult> predictedResult = GameResult.getGameResult(predictedHomeScore, predictedAwayScore);
//
//        if (predictedHomeScore == gameHomeScore && predictedAwayScore == gameAwayScore) {
//            pointsForGame += 3;
//        } else if (predictedResult == gameResult) {
//            pointsForGame += 1;
//        } else {
//            pointsForGame += 0;
//        }
//
//        System.out.println("game result: " + gameResult.toString());
//        System.out.println("predicted home: " + predictedResult.toString());
//        return pointsForGame;
//


    }


}
