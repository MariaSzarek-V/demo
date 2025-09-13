package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;

import java.util.List;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GamesViewController {

    private final GameService gameService;

    @GetMapping("/all")
    public String allGames(Model model) {
        List<GameResponseDTO>games = gameService.getAll();
        model.addAttribute("games", games);
        return "games";

    }


}
