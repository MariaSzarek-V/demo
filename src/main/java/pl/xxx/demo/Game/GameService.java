package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Prediction.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GamePredictionDTO> getGamesWithPredictions() {
        List<Game> games = gameRepository.findAll();
        List<Prediction> predictions = predictionRepository.findAll();

        return games.stream()
                .map(game -> {
                    Prediction prediction = predictions.stream()
                            .filter(p -> p.getGame().getId().equals(game.getId()))
                            .findFirst()
                            .orElse(null);

                    return new GamePredictionDTO(
                            game.getId(),
                            game.getHomeTeam(),
                            game.getAwayTeam(),
                            prediction != null ? prediction.getPredictedHomeScore() : null,
                            prediction != null ? prediction.getPredictedAwayScore() : null,
                            prediction != null ? prediction.getId() : null
                    );
                })
                .collect(Collectors.toList());
    }


}
