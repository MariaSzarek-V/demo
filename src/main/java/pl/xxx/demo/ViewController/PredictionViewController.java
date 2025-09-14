package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.xxx.demo.Admin.AdminGameDTO;
import pl.xxx.demo.Error.GameTimeStatusException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;
import pl.xxx.demo.Prediction.PredictionRequestDTO;
import pl.xxx.demo.Prediction.PredictionResponseDTO;
import pl.xxx.demo.Prediction.PredictionService;

@Controller
@RequestMapping("/predictions")
@RequiredArgsConstructor
public class PredictionViewController {
    private final PredictionService predictionService;
    private final GameService gameService;

    //user nie opotrzebuje ogladac swoejgo prediction
    //pod /prediction/{id}

    //FORMULARZ NOWY
    @GetMapping("/new/{gameId}")
    public String showCreateFormPrediction(@PathVariable Long gameId, Model model) {
        GameResponseDTO game = gameService.get(gameId);
        PredictionRequestDTO dto = new PredictionRequestDTO();
        dto.setGameId(gameId);
        model.addAttribute("prediction", dto);
        model.addAttribute("game", game);
        model.addAttribute("readOnly", false);
        return "predictions/predictionform";
    }


    //formularz edycji do istniejacej predykcji
    @GetMapping("/edit/{id}")
    public String showEditFormPrediction(@PathVariable Long id, Model model) {
        PredictionResponseDTO dto = predictionService.get(id);
        GameResponseDTO game = gameService.get(dto.getGameId());
        model.addAttribute("prediction", dto);
        model.addAttribute("game", game);
//        model.addAttribute("formAction", "/predictions/new");
        model.addAttribute("readOnly", false);
        return "predictions/predictionform";
    }

    //formularz zapisz nowy
    @PostMapping("/new/{gameId}")
    public String createPrediction(@PathVariable Long gameId, @Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO,
                                   BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("prediction", predictionDTO);
            model.addAttribute("game", gameService.get(gameId));
            model.addAttribute("readOnly", false);
            return "predictions/predictionform";
        }
        predictionDTO.setGameId(gameId);
        predictionService.add(predictionDTO);
        ra.addFlashAttribute("message", "Prediction created");
        return "redirect:/games";

    }

    //zapisz edycje
    @PostMapping("/edit/{id}")
    public String updatePrediction(@PathVariable Long id, @Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO, Model model, BindingResult result, RedirectAttributes ra) {

        Long gameId = predictionService.get(id).getGameId();
        if (result.hasErrors()) {
            predictionDTO.setId(id);
            model.addAttribute("prediction", predictionDTO);
            model.addAttribute("game", gameService.get(gameId));
//            model.addAttribute("formAction", "/predictions/new");
            model.addAttribute("readOnly", false);
            return "predictions/predictionform";
        }
        predictionService.update(id, predictionDTO);
        ra.addFlashAttribute("message", "Prediction updated");
        return "redirect:/games";
    }

    @GetMapping("/delete/{id}")
    public String deletePrediction(@PathVariable Long id, RedirectAttributes ra) {
        predictionService.delete(id);
        ra.addFlashAttribute("message", "Prediction deleted");
        return "redirect:/games";
    }


}
