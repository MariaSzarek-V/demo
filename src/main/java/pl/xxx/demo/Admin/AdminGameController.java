package pl.xxx.demo.Admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.UserPoints.UserPointsService;

import java.util.List;


/*
widok dla admina
moze miec osobny get dla meczy, pod warunkiem ze mecze
dla niezalogowanych sa widoczne ze statusem scheduled
czyli jest roznica w metodzie
 */
@Tag(name="1. Admin game")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final AdminGameService adminGameService;
    private final UserPointsService userPointsService;

    @GetMapping
    public List<Game> getGames() {
        return adminGameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return adminGameService.getGameById(id);
    }

    @PostMapping
    public Game createGame(@Valid @RequestBody AdminGameDTO dto) {
        return adminGameService.addGame(dto);
    }

    @PutMapping("/{id}")
    public AdminGameDTO updateGame(@PathVariable Long id, @Valid @RequestBody AdminGameDTO dto) {
        /**
         * update game ze sprawdzeniem czy mecz zakoncony,
         * jesli FINISHED to podliczenie punktow dla userow, ktorzy wytypowali mecz
         */
        adminGameService.updateGame(id, dto);
        return dto;
    }

    @DeleteMapping("/{id}")
    public String deleteGame(@PathVariable Long id) {
        adminGameService.deleteGame(id);
        return "Mecz o "+ id + " został usunięty";
    }





}
