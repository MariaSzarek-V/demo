package pl.xxx.demo.Admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="1. Admin game")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final AdminGameService adminGameService;

    @GetMapping
    public List<AdminGameDTO> getGames() {
        return adminGameService.getAllGames();
    }

    @GetMapping("/{id}")
    public AdminGameDTO getGame(@PathVariable Long id) {
        return adminGameService.getGameById(id);
    }

    @PostMapping
    public AdminGameDTO createGame(@Valid @RequestBody AdminGameDTO dto) {
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
