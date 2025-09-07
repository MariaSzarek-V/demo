package pl.xxx.demo.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.UserPoints.UserPointsService;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminGameService {


    private final GameRepository gameRepository;
    private final UserPointsService userPointsService;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() ->new RuntimeException("Game not found"));
    }
    public void addGame(Game game) {
        gameRepository.save(game);
    }
    public Game updateGame(Long id, Game game) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id " + id));

        existingGame.setHomeTeam(game.getHomeTeam());
        existingGame.setAwayTeam(game.getAwayTeam());
        existingGame.setHomeScore(game.getHomeScore());
        existingGame.setAwayScore(game.getAwayScore());
        existingGame.setGameDate(game.getGameDate());
        existingGame.setGameStatus(game.getGameStatus());
        gameRepository.save(existingGame);
        if (existingGame.getGameStatus() == GameStatus.FINISHED) {
            userPointsService.calculatePredictionForGame(existingGame);
        }
        return existingGame;
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }



}
