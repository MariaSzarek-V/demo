package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;
import pl.xxx.demo.PredictionResult.GamePredictionResultDTO;
import pl.xxx.demo.Prediction.PredictionService;

import java.util.List;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameViewController {

    private final GameService gameService;
    private final PredictionService predictionService;


// tutaj mecze tylko i wylcznie
    @GetMapping
    public String allGames(Model model) {
        List<GameResponseDTO> games = gameService.getAll();
        model.addAttribute("games", games);
        return "games";

    }
//TODO TERAZ TOOOOOOOOO

    @GetMapping("/results")
    public String getGamesWithPredictionsAndResults(Model model) {
        List<GamePredictionResultDTO> games = gameService.getAllGamesWithUserPredictionsAndPoints();
        model.addAttribute("games", games);
        return "prediction-result";
    }


    @GetMapping("/edit/{gameId}")
    public String editGame(@PathVariable Long gameId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        GameResponseDTO game = gameService.get(gameId);
        model.addAttribute("game", game);
        return "edit_game"; // widok z formularzem
    }

//    @GetMapping("/edit/{gameId}")
//    public String editGame(@PathVariable Long gameId, Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//
//        Game game = gameService.get(gameId)
//                .orElseThrow(() -> new RuntimeException("Game not found"));
//
//        model.addAttribute("game", game);
//        model.addAttribute("myPredictions", predictionService.getMyPredictions());
//        model.addAttribute("editId", gameId);
//
//        return "edit_game";
//    }

//    // Zapis wyniku
//    @PostMapping("/update")
//    public String updateGame(@RequestParam(required = false) Long predictionId,
//                             @RequestParam Long gameId,
//                             @RequestParam int predictedHomeScore,
//                             @RequestParam int predictedAwayScore) {
//        predictionService.saveOrUpdate(predictionId, gameId, predictedHomeScore, predictedAwayScore);
//        return "redirect:/games";
    }

