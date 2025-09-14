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
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/predictions") // Usunięte /admin
public class TEMPViewController {

    private final PredictionService predictionService;
    private final GameService gameService;

    // Tworzenie nowej predykcji - wymaga gameId
    @GetMapping("/new/{gameId}")
    public String showCreatePredictionForm(@PathVariable Long gameId, Model model) {
        GameResponseDTO game = gameService.get(gameId);
        PredictionRequestDTO prediction = new PredictionRequestDTO();
        prediction.setGameId(gameId);

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", game);
        model.addAttribute("readOnly", false);
        model.addAttribute("formAction", "/predictions/new/" + gameId); // Usunięte /admin
        return "predictions/predictionform";
    }

    @PostMapping("/new/{gameId}")
    public String createPrediction(@PathVariable Long gameId,
                                   @Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO,
                                   BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            GameResponseDTO game = gameService.get(gameId);
            model.addAttribute("game", game);
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/predictions/new/" + gameId); // Usunięte /admin
            return "predictions/predictionform"; // Usunięte /admin z ścieżki widoku
        }

        predictionService.add(predictionDTO);
        ra.addFlashAttribute("message", "Predykcja dodana");
        return "redirect:/games"; // Usunięte /admin
    }

    // Edycja istniejącej predykcji - wymaga predictionId
    @GetMapping("/edit/{predictionId}")
    public String showEditPredictionForm(@PathVariable Long predictionId, Model model) {
        PredictionResponseDTO prediction = predictionService.get(predictionId);
        GameResponseDTO game = gameService.get(prediction.getGameId());

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", game);
        model.addAttribute("readOnly", false);
        model.addAttribute("formAction", "/predictions/edit/" + predictionId); // Usunięte /admin
        return "predictions/predictionform"; // Usunięte /admin z ścieżki widoku
    }

    @PostMapping("/edit/{predictionId}")
    public String updatePrediction(@PathVariable Long predictionId,
                                   @Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO,
                                   BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            PredictionResponseDTO existingPrediction = predictionService.get(predictionId);
            GameResponseDTO game = gameService.get(existingPrediction.getGameId());
            model.addAttribute("game", game);
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/predictions/edit/" + predictionId); // Usunięte /admin
            return "predictions/predictionform"; // Usunięte /admin z ścieżki widoku
        }

        predictionService.update(predictionId, predictionDTO);
        ra.addFlashAttribute("message", "Predykcja zaktualizowana");
        return "redirect:/games"; // Usunięte /admin
    }

    // Usuwanie predykcji
    @PostMapping("/delete/{predictionId}")
    public String deletePrediction(@PathVariable Long predictionId, RedirectAttributes ra) {
        predictionService.delete(predictionId);
        ra.addFlashAttribute("message", "Predykcja usunięta");
        return "redirect:/games"; // Usunięte /admin
    }
}