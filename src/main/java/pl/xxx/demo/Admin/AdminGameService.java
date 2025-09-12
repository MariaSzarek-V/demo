package pl.xxx.demo.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.GameAlreadyFinishedException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.RankingHistory.RankingHistoryService;
import pl.xxx.demo.UserPoints.UserPointsService;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminGameService {


    private final GameRepository gameRepository;
    private final UserPointsService userPointsService;
    private final RankingHistoryService rankingHistoryService;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Game not found"));
    }
    public Game addGame(AdminGameDTO dto) {
        Game game = AdminGameDTOMapper.convertToAdminGame(dto);
        return gameRepository.save(game);
    }

    /*
    metoda update mecz,
    po zmianie na FINISHED przez admina:
    zliczaja sie punkty userow i zapisuje sie ranking
     */
    public AdminGameDTO updateGame(Long id, AdminGameDTO dto) {
        //TODO : zmienic nazwe na finalize game ? a osobno zrobic na update game?
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
        AdminGameDTOMapper.updateGameFromDto(dto, existingGame);
        Game savedGame = gameRepository.save(existingGame);

        if (existingGame.getGameStatus() == GameStatus.FINISHED) {
            userPointsService.calculatePredictionForGame(existingGame);
            rankingHistoryService.saveCurrentRankingToHistory(existingGame.getId());
        }
        return AdminGameDTOMapper.convertToAdminGameDTO(savedGame);
    }

    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));

        if (GameStatus.FINISHED.equals(game.getGameStatus())) {
            throw new GameAlreadyFinishedException(id);
        }
        gameRepository.delete(game);
    }
}
