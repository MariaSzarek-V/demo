package pl.xxx.demo.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.GameAlreadyFinishedException;
import pl.xxx.demo.Error.GameNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Ranking.RankingService;
import pl.xxx.demo.UserPoints.UserPointsService;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminGameService {


    private final GameRepository gameRepository;
    private final UserPointsService userPointsService;
    private final RankingService rankingService;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() ->new RuntimeException("Game not found"));
    }
    public Game addGame(AdminGameDTO dto) {
        Game game = AdminGameDTOMapper.toCreateEntity(dto);
        return gameRepository.save(game);
    }

    /*
    metoda update mecz,
    po zmianie na FINISHED przez admina:
    zliczaja sie punkty userow i zapisuje sie ranking
     */
    public void updateGame(Long id, AdminGameDTO dto) {
        //TODO : zmienic nazwe na finalize game ? a osobno zrobic na update game?
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id " + id));
        existingGame = AdminGameDTOMapper.toUpdatedEntity(dto, existingGame);


        gameRepository.save(existingGame);
        if (existingGame.getGameStatus() == GameStatus.FINISHED) {
            userPointsService.calculatePredictionForGame(existingGame);
            rankingService.saveCurrentRankingToHistory(existingGame.getId());
        }
    }

    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        if (GameStatus.FINISHED.equals(game.getGameStatus())) {
            throw new GameAlreadyFinishedException(id);
        }

        gameRepository.delete(game);
    }




}
