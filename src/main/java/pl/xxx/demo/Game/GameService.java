package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return predictionRepository.findAll().stream()
                .map(pred -> new GamePredictionDTO(
                        pred.getId(),
                        pred.getGame().getHomeTeam(),
                        pred.getGame().getAwayTeam(),
                        pred.getPredictedHomeScore(),
                        pred.getPredictedAwayScore()
                ))
                .collect(Collectors.toList());
    }

    public Optional<Game> get(long id){
        return gameRepository.findById(id);
    }
}
