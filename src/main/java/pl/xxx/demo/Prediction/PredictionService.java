package pl.xxx.demo.Prediction;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xxx.demo.Game.GameRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final GameRepository gameRepository;


    public Prediction add(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    public List<Prediction> getAll() {
        return predictionRepository.findAll();
    }

    public List<Prediction> getPredictionsByUserId(Long userId) {
        return predictionRepository.findByUserId(userId);
    }


    public Optional<Prediction> get(Long id) {
        return predictionRepository.findById(id);
    }

    public void update(Long id, Prediction prediction) {
        Prediction existingPrediction = predictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such prediction to update"));
        existingPrediction.setPredictedHomeScore(prediction.getPredictedHomeScore());
        existingPrediction.setPredictedAwayScore(prediction.getPredictedAwayScore());
    }

    public void saveOrUpdate(Long predictionId, Long gameId, int homeScore, int awayScore) {
        Prediction prediction;

        if (predictionId != null) {
            prediction = predictionRepository.findById(predictionId)
                    .orElseThrow(() -> new RuntimeException("Prediction not found"));
        } else {
            prediction = new Prediction();
            prediction.setGame(gameRepository.findById(gameId)
                    .orElseThrow(() -> new RuntimeException("Game not found")));
        }

        prediction.setPredictedHomeScore(homeScore);
        prediction.setPredictedAwayScore(awayScore);
        predictionRepository.save(prediction);
    }

    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
