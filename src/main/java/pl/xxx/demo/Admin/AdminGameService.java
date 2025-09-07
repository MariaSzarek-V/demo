package pl.xxx.demo.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminGameService {


    private final GameRepository gameRepository;

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

        return gameRepository.findById(id).orElse(null);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }



}
