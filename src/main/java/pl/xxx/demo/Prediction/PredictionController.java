package pl.xxx.demo.Prediction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Enum.GameStatus;
import pl.xxx.demo.User.User;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService predictionService;
    private final PredictionRepository predictionRepository;
    private final UserPointsRepository userPointsRepository;

    public PredictionController(PredictionService predictionService, PredictionRepository predictionRepository, UserPointsRepository userPointsRepository) {
        this.predictionService = predictionService;
        this.predictionRepository = predictionRepository;
        this.userPointsRepository = userPointsRepository;
    }

    @GetMapping("/my")
    public List<PredictionResponseDTO> getMyPredictions() {
        return predictionService.getMyPredictions();
    }

    @GetMapping("/my/gamestatus/{status}")
    public List<PredictionResponseDTO> getMyPredictionsByGameStatus(@PathVariable GameStatus status) {
        return predictionService.getMyPredictionsByGameStatus(status);
    }

    @GetMapping("/{id}")
    public PredictionResponseDTO getPredictionById(@PathVariable Long id) {
        return predictionService.get(id);
    }



    @PostMapping
    public PredictionResponseDTO createPrediction(@RequestBody PredictionRequestDTO dto) {
        return predictionService.add(dto);
    }

    @PutMapping("/{id}")
    public String updatePrediction(@PathVariable Long id, @RequestBody PredictionRequestDTO dto) {
        predictionService.update(id, dto);
        return "Prediction updated";
    }

    @GetMapping("/game/{gameId}")
    public List<PredictionResponseDTO>getAllPredictionsForGame(@PathVariable Long gameId) {
        return predictionService.getPredictionsByGameId(gameId);
    }

//    @GetMapping("/scheduled-games")
//    public List<PredictionResponseDTO> getAllPredictionsForScheduledGames() {
//        return predictionService.getPredictionsForScheduledGames();
//    }

    // nie wiem czy jest potrzbene???/

    @DeleteMapping("/{id}")
    public void deletePrediction(@PathVariable Long id) {
        predictionService.delete(id);
    }

}
