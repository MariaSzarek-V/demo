package pl.xxx.demo.PredictionResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name="5. Results")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class GamePredictionResultController {

    private final GamePredictionResultService gamePredictionResultService;


    @GetMapping("/my-prediction-result")
    public List<GamePredictionResultResponseDTO> getByGamesPredictionsResult() {
        return gamePredictionResultService.getAllPredictionsByUserWithResult();
    }

    @GetMapping("/allusers-prediction-result/{gameId}")
    public List<GamePredictionResultResponseDTO> getUsersPredictionResultsByGameId(@PathVariable Long gameId) {
        return gamePredictionResultService.getGameWithPredictionsAndPointsB(gameId);
    }
}
