package pl.xxx.demo.UserPoints;

import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameResult;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserPointsService {

    private final UserPointsRepository userPointsRepository;

    public UserPointsService(UserPointsRepository userPointsRepository) {
        this.userPointsRepository = userPointsRepository;
    }

    public List<UserPoints> getAll() {
        return userPointsRepository.findAll();
    }

    public Optional<UserPoints> get(Long id) {
        return userPointsRepository.findById(id);
    }

//    public UserPoints add(UserPoints userPoints) {
//        return userPointsRepository.save(userPoints);
//    }
    public UserPoints add(User user, Game game, Prediction prediction) {
        UserPoints userPoints = new UserPoints();
        userPoints.setUser(user);
        userPoints.setGame(game);
        userPoints.setPoints(calculatePointsForGame(prediction, game));

    }

    public UserPoints update(Long id, UserPoints userPoints) {
        UserPoints existingUserPoints = userPointsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such user points to update"));
        existingUserPoints.setPoints(userPoints.getPoints());
        return userPointsRepository.save(userPoints);
    }


    public int calculatePointsForGame(Prediction prediction, Game game) {
        int pointsForGame = 0;
        int predictedHomeScore = prediction.getPredictedHomeScore();
        int predictedAwayScore = prediction.getPredictedAwayScore();
        int gameHomeScore = game.getHomeScore();
        int gameAwayScore = game.getAwayScore();
        Enum<GameResult> gameResult= GameResult.getGameResult(gameHomeScore, gameAwayScore);
        Enum<GameResult> predictedResult = GameResult.getGameResult(predictedHomeScore, gameHomeScore);
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
