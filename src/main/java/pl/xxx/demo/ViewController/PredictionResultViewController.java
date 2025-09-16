package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.PredictionResult.GamePredictionResultDTO;
import pl.xxx.demo.PredictionResult.GamePredictionResultService;

import java.util.List;

@Controller
@RequestMapping("/results")
@RequiredArgsConstructor
public class PredictionResultViewController {

    private final GamePredictionResultService gamePredictionResultService;

    @GetMapping("/{gameId}")
    public String getGameWithPredictionsAndResults(@PathVariable Long gameId, Model model) {
        List<GamePredictionResultDTO> predictionResult = gamePredictionResultService.getGameWithPredictionsAndPoints(gameId);
        model.addAttribute("result", predictionResult);
        return "resultpergame";
    }
}

