package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.Prediction.PredictionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PredictionRepository predictionRepository;



    public List<Game> getAll(){
        return gameRepository.findAll();
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

    public Optional<Game> get(long id){
        return gameRepository.findById(id);
    }
}
