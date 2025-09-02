package pl.xxx.demo.Prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/predictions")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("")
    public List<Prediction> getAllPredictions() {
        return predictionService.getAll();
    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Prediction getPredictionById(@PathVariable Long id) {
//        return predictionService.get(id);
//    }

// ALTRENATYWA:

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
