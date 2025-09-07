package pl.xxx.demo.Admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final AdminGameService adminGameService;

    @GetMapping
    public List<Game> getGames() {
        return adminGameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return adminGameService.getGameById(id);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        adminGameService.addGame(game);
        return game;
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        adminGameService.updateGame(id, game);
        return game;
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        adminGameService.deleteGame(id);
    }





}
