package pl.xxx.demo.Game;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name="3. Game")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @GetMapping("")
    public List<GameResponseDTO> getAllGames() {
        return gameService.getAll();
    }

    @GetMapping("/{id}")
    public GameResponseDTO getGameById(@PathVariable Long id) {
        return gameService.get(id);
    }

    @GetMapping("/upcoming")
    public List<GameResponseDTO> getUpcomingGames(){
        return gameService.getUpcomingGames();
    }

    @GetMapping("/finished")
    public List<GameResponseDTO> getFinishedGames(){
        return gameService.getFinishedGames();
    }

}