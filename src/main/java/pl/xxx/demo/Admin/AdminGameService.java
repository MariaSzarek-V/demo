package pl.xxx.demo.Admin;

import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.GameDeleteNotAllowedException;
import pl.xxx.demo.Error.GameScoreEmptyException;
import pl.xxx.demo.Error.GameTimeStatusException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameRepository;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameResponseDTOMapper;
import pl.xxx.demo.RankingHistory.RankingHistoryService;
import pl.xxx.demo.UserPoints.UserPointsService;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminGameService {


    private final GameRepository gameRepository;
    private final UserPointsService userPointsService;
    private final RankingHistoryService rankingHistoryService;

    public List<AdminGameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return AdminGameDTOMapper.convertToAdminGameDTOList(games);
    }

    public AdminGameDTO getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Game not found"));
        return AdminGameDTOMapper.convertToAdminGameDTO(game);
    }


    public AdminGameDTO addGame(AdminGameDTO dto) {
        checkIfGameDateIsCorrect(dto);
        Game game = AdminGameDTOMapper.convertToAdminGame(dto);
        gameRepository.save(game);
        return AdminGameDTOMapper.convertToAdminGameDTO(game);
    }

    /*
    metoda update mecz,
    po zmianie na FINISHED przez admina:
    zliczaja sie punkty userow i zapisuje sie ranking
     */
    public AdminGameDTO updateGame(Long id, AdminGameDTO dto) {
        checkIfGameDateIsCorrect(dto);
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));

        if (dto.getGameStatus() == GameStatus.FINISHED) {
            if (dto.getHomeScore() == null || dto.getAwayScore() == null) {
                throw new GameScoreEmptyException();
            }
        }
        dto.setId(id);
        AdminGameDTOMapper.updateGameFromDto(dto, existingGame);
        Game savedGame = gameRepository.save(existingGame);

        if (savedGame.getGameStatus() == GameStatus.FINISHED) {
            userPointsService.calculatePredictionForGame(savedGame);
            rankingHistoryService.saveCurrentRankingToHistory(savedGame.getId());
        }
        return AdminGameDTOMapper.convertToAdminGameDTO(savedGame);
    }



    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));

        if (GameStatus.ADMIN_VIEW.equals(game.getGameStatus())) {
            gameRepository.delete(game);
        } else {
            throw new GameDeleteNotAllowedException();
        }
    }

    /*
    metoda pomocnicza do sprawdzania daty meczu,
    czy przy scheduled jest przed obecna;
    czy finished - data po now
     */

    public boolean checkIfGameDateIsCorrect(AdminGameDTO dto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime gameDate = dto.getGameDate();
        GameStatus status = dto.getGameStatus();

        if (status == GameStatus.SCHEDULED && gameDate.isBefore(now)) {
            throw new GameTimeStatusException();
        }

        if (status == GameStatus.FINISHED && gameDate.isAfter(now)) {
            throw new GameTimeStatusException();
        }

        return true;

    }
}
