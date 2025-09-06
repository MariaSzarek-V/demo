package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Prediction.PredictionService;

@Controller
@RequestMapping("app/games")
@RequiredArgsConstructor
public class GameViewController {

    private final GameService gameService;
    private final PredictionService predictionService;

    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getGamesWithPredictions());
        model.addAttribute("editId", null);
        return "games";
    }

    // Przejście w tryb edycji
    @GetMapping("/edit/{gameId}")
    public String editGame(@PathVariable Long gameId, Model model) {
        model.addAttribute("games", gameService.getGamesWithPredictions());
        model.addAttribute("editId", gameId);
        return "games";
    }

    // Zapis wyniku
    @PostMapping("/update")
    public String updateGame(@RequestParam(required = false) Long predictionId,
                             @RequestParam Long gameId,
                             @RequestParam int predictedHomeScore,
                             @RequestParam int predictedAwayScore) {
        predictionService.saveOrUpdate(predictionId, gameId, predictedHomeScore, predictedAwayScore);
        return "redirect:/app/games";
    }
}
