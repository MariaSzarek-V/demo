package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;
import pl.xxx.demo.PredictionResult.GamePredictionResultDTO;
import pl.xxx.demo.Prediction.PredictionService;
import pl.xxx.demo.PredictionResult.GamePredictionResultService;

import java.util.List;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameViewController {

    private final GameService gameService;
    private final GamePredictionResultService gamePredictionResultService;

    @GetMapping("")
    public String getGamesWithPredictionsAndResults(Model model) {
        List<GamePredictionResultDTO> games = gamePredictionResultService.getAllGamesWithUserPredictionsAndPoints();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/edit/{gameId}")
    public String editGame(@PathVariable Long gameId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        GameResponseDTO game = gameService.get(gameId);
        model.addAttribute("game", game);
        return "edit_game";
    }
}

