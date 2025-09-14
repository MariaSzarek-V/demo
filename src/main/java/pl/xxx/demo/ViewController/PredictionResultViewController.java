package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.Prediction.PredictionRepository;
import pl.xxx.demo.Prediction.PredictionService;
import pl.xxx.demo.PredictionResult.PredictionResultDTO;
import pl.xxx.demo.PredictionResult.PredictionResultMapper;
import pl.xxx.demo.PredictionResult.PredictionResultService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/prediction-result")
@RequiredArgsConstructor
public class PredictionResultViewController {

    private final PredictionResultService predictionResultService;
    private final PredictionRepository predictionRepository;
    private final PredictionResultMapper predictionResultMapper;
    private final PredictionService predictionService;

    @GetMapping
    public String predictionResultView(Model model) {
        List<PredictionResultDTO> predictions = predictionResultService.getPredictionResults();
        model.addAttribute("predictions", predictions);
        return "prediction-result";
    }

    @GetMapping("/my")
    public String getPredictionForLoggedUser(Principal principal, Model model) {
        String username = principal.getName();
        List<PredictionResultDTO> predictionsUserId = predictionRepository.findByUserUsername(username)
                .stream()
                .map(predictionResultMapper::convertToPredictionResultDTO)
                .collect(Collectors.toList());
        model.addAttribute("predictions", predictionsUserId);
        return "prediction-result";

    }

// predykcje po id usera - jeszcze z czasow bez logowania
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        List<PredictionResultDTO> predictionsUserId = predictionRepository.findByUserId(id)
                .stream()
                .map(predictionResultMapper::convertToPredictionResultDTO)
                .collect(Collectors.toList());
        model.addAttribute("predictions", predictionsUserId);
        return "prediction-result";

    }

}

