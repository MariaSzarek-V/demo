package pl.xxx.demo.Prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.xxx.demo.Game.Game;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/predictions")
public class PredictionController {

    private final PredictionService predictionService;
    private final PredictionRepository predictionRepository;

    public PredictionController(PredictionService predictionService, PredictionRepository predictionRepository) {
        this.predictionService = predictionService;
        this.predictionRepository = predictionRepository;
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



    //sprawdzenie PredictionDTO
    @GetMapping("/dto")
    public List<PredictionResultDTO>getAll() {
        return predictionRepository.findAll().stream()
                .map(this::convertToPredictionResultDTO)
                .collect(Collectors.toList());
    }

    private PredictionResultDTO convertToPredictionResultDTO(Prediction prediction) {
        PredictionResultDTO predictionResultDTO = new PredictionResultDTO();
        predictionResultDTO.setHomeTeam(Optional.ofNullable(prediction.getGame())
                .map(Game::getHomeTeam).orElse("no game found"));
        predictionResultDTO.setAwayTeam(Optional.ofNullable(prediction.getGame())
                .map(Game::getAwayTeam).orElse("no game found"));
        predictionResultDTO.setHomeScore(Optional.ofNullable(prediction.getGame())
                .map(Game::getHomeScore).orElse(-1));
        predictionResultDTO.setAwayScore(Optional.ofNullable(prediction.getGame())
                .map(Game::getAwayScore).orElse(-1));
        predictionResultDTO.setPredictedHomeScore(prediction.getPredictedHomeScore());
        predictionResultDTO.setPredictedAwayScore(prediction.getPredictedAwayScore());

        return predictionResultDTO;
    }

}
