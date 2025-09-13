package pl.xxx.demo.Game;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Prediction.PredictionService;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameViewController {

    private final GameService gameService;
    private final PredictionService predictionService;

//    @GetMapping
//    public String listGames(Model model) {
//        model.addAttribute("games", gameService.getGamesWithPredictions());
//        model.addAttribute("editId", null);
//        return "games";
//    }
    @GetMapping
    public String listGames(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        model.addAttribute("games", gameService.getGamesWithPredictions());
        model.addAttribute("myPredictions", predictionService.getMyPredictions()); // dodane

        return "tables";
    }

    @GetMapping("/edit/{gameId}")
    public String editGame(@PathVariable Long gameId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Game game = gameService.get(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

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

