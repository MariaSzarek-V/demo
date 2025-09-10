package pl.xxx.demo.PredictionResult;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.xxx.demo.Prediction.PredictionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prediction-result")
public class PredictionResultController {

    private final PredictionResultService predictionResultService;
    private final PredictionRepository predictionRepository;
    private final PredictionResultMapper predictionResultMapper;

    @GetMapping
    public List<PredictionResultDTO> getAll() {
        return predictionResultService.getPredictionResults();
    }

    @GetMapping("/{id}")
    public List <PredictionResultDTO> getById(@PathVariable Long id) {
        return predictionRepository.findByUserId(id)
                .stream()
                .map(predictionResultMapper::convertToPredictionResultDTO)
                .collect(Collectors.toList());

    }

}
