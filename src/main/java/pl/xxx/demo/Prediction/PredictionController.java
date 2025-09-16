package pl.xxx.demo.Prediction;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.UserPoints.UserPointsRepository;
import java.util.List;


@Tag(name="4. Prediction")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService predictionService;
    private final PredictionRepository predictionRepository;
    private final UserPointsRepository userPointsRepository;


    @GetMapping("/my")
    public List<PredictionResponseDTO> getMyPredictions() {
        return predictionService.getMyPredictions();
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
    public PredictionResponseDTO updatePrediction(@PathVariable Long id, @RequestBody PredictionRequestDTO dto) {
        return predictionService.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public void deletePrediction(@PathVariable Long id) {
        predictionService.delete(id);
    }

}
