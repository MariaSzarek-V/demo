package pl.xxx.demo.Prediction;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;

    public PredictionService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public Prediction add(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    public List<Prediction> getAll() {
        return predictionRepository.findAll();
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

    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
