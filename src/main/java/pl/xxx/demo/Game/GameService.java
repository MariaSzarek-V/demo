package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Prediction.*;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PredictionRepository predictionRepository;


    public List<GameResponseDTO> getAll() {
        List<Game> games = gameRepository.findAll();
        return GameResponseDTOMapper.convertToGameResponseDTOList(games);
    }

    public GameResponseDTO get(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game with id " + id + " not found"));
        return GameResponseDTOMapper.convertToGameResponseDTO(game);
    }

    public List<GameResponseDTO> getUpcomingGames() {
        List<Game> games = gameRepository.findByGameStatus(GameStatus.SCHEDULED);
        return GameResponseDTOMapper.convertToGameResponseDTOList(games);
    }

    public List<GameResponseDTO> getFinishedGames() {
        List<Game> games = gameRepository.findByGameStatus(GameStatus.FINISHED);
        return GameResponseDTOMapper.convertToGameResponseDTOList(games);
    }

}


