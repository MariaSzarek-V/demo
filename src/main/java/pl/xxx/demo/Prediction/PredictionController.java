package pl.xxx.demo.Prediction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/predictions")
public class PredictionController {

    private final PredictionService predictionService;
    private final PredictionRepository predictionRepository;
    private final UserPointsRepository userPointsRepository;

    public PredictionController(PredictionService predictionService, PredictionRepository predictionRepository, UserPointsRepository userPointsRepository) {
        this.predictionService = predictionService;
        this.predictionRepository = predictionRepository;
        this.userPointsRepository = userPointsRepository;
    }

    @GetMapping("")
    public List<Prediction> getAllPredictions() {
        return predictionService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Prediction> getPredictionById(@PathVariable Long id) {
        Optional<Prediction> predictionById = predictionService.get(id);
        if (predictionById.isPresent()) {
            return ResponseEntity.ok(predictionById.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Prediction createPrediction(@RequestBody Prediction prediction) {
        predictionService.add(prediction);
        return prediction;
    }

    @PutMapping("/{id}")
    public Prediction updatePrediction(@PathVariable Long id, @RequestBody Prediction prediction) {
        predictionService.update(id, prediction);
        return prediction;
    }


    @DeleteMapping("/{id}")
    public void deletePrediction(@PathVariable Long id) {
        predictionService.delete(id);
    }

}
