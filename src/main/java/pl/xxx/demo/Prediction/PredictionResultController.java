package pl.xxx.demo.Prediction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/predictionsresult")
public class PredictionResultController {

    private final PredictionResultService predictionResultService;
    private final PredictionRepository predictionRepository;
    private final PredictionResultMapper predictionResultMapper;
    //sprawdzenie PredictionDTO
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
