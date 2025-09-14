package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.Prediction.PredictionService;

@Controller
@RequestMapping("/predictions")
@RequiredArgsConstructor
public class PredictionViewController {
    private final PredictionService predictionService;


    @GetMapping("/{id}")
    public String viewPrediction(@PathVariable Long id, Model model) {
        model.addAttribute("prediction", predictionService.get(id));

        return "predictionview";
    }

}
