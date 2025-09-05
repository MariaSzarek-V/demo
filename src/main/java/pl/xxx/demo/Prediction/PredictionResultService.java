package pl.xxx.demo.Prediction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictionResultService {

    private final PredictionRepository predictionRepository;
    private final PredictionResultMapper predictionResultMapper;


    public List<PredictionResultDTO> getPredictionResults() {
        return predictionRepository.findAll().stream()
                .map(predictionResultMapper::convertToPredictionResultDTO)
                .collect(Collectors.toList());
    }
}
