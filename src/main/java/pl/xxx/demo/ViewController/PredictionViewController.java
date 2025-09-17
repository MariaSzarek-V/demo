package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.xxx.demo.Prediction.PredictionRequestDTO;
import pl.xxx.demo.Prediction.PredictionResponseDTO;
import pl.xxx.demo.Prediction.PredictionService;
import pl.xxx.demo.Game.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/predictions")
public class PredictionViewController {

    private final PredictionService predictionService;
    private final GameService gameService;


    @GetMapping("/new/{gameId}")
    public String showCreateForm(@PathVariable Long gameId, Model model) {
        PredictionRequestDTO prediction = new PredictionRequestDTO();
        prediction.setGameId(gameId);

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", gameService.get(gameId));
        return "predictions/new";  // osobny widok
    }

    @PostMapping("/new/{gameId}")
    public String create(@PathVariable Long gameId,
                         @Valid @ModelAttribute("prediction") PredictionRequestDTO dto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("game", gameService.get(gameId));
            return "predictions/new";
        }
        predictionService.add(dto);
        return "redirect:/games";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        PredictionResponseDTO response = predictionService.get(id);

        PredictionRequestDTO prediction = new PredictionRequestDTO();
        prediction.setId(id);
        prediction.setGameId(response.getGameId());
        prediction.setPredictedHomeScore(response.getPredictedHomeScore());
        prediction.setPredictedAwayScore(response.getPredictedAwayScore());

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", gameService.get(response.getGameId()));
        return "predictions/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("prediction") PredictionRequestDTO dto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("game", gameService.get(dto.getGameId()));
            return "predictions/edit";
        }
        predictionService.update(id, dto);
        return "redirect:/games";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        predictionService.delete(id);
        return "redirect:/games";
    }
}