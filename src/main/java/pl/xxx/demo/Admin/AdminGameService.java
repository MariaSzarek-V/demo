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
        return gameRepository.findById(id).orElse(null);
    }
    public void addGame(Game game) {
        gameRepository.save(game);
    }
    public void updateGame(Game game) {
        gameRepository.save(game);
    }
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }



}
