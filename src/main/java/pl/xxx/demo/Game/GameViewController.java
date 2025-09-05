package pl.xxx.demo.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionService;

import java.util.List;

@Controller
@RequestMapping("app/games")
@RequiredArgsConstructor
public class GameViewController {

    private final GameService gameService;
    private final PredictionService predictionService;

    @GetMapping
    public String listGames(Model model) {
        List<GamePredictionDTO> games = gameService.getGamesWithPredictions();
        model.addAttribute("games", games);
        model.addAttribute("editId", null);
        return "ranking/games";
    }

    // Przejście w tryb edycji
    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable Long id, Model model) {
        model.addAttribute("games", gameService.getGamesWithPredictions());
        model.addAttribute("editId", id); // ustawiamy ID do edycji
        return "ranking/games";
    }

    // Zapis wyniku
    @PostMapping("/update")
    public String updateGame(@RequestParam Long id,
                             @RequestParam int predictedHomeScore,
                             @RequestParam int predictedAwayScore) {
        Prediction prediction = new Prediction();
        prediction.setPredictedHomeScore(predictedHomeScore);
        prediction.setPredictedAwayScore(predictedAwayScore);
        predictionService.update(id, prediction);
        return "redirect:/app/games"; // wracamy do listy
    }
}
