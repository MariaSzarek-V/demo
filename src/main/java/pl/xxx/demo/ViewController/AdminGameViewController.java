package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.xxx.demo.Admin.AdminGameDTO;
import pl.xxx.demo.Admin.AdminGameService;
import pl.xxx.demo.Error.GameTimeStatusException;
import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/games")
public class AdminGameViewController {

    private final AdminGameService adminGameService;


    @GetMapping
    public String listAdminGames(Model model) {
        List<AdminGameDTO> games = adminGameService.getAllGames();
        model.addAttribute("games", games);
        return "admin/games/adminlist";
    }

    @GetMapping("/{id}")
    public String viewAdminGame(@PathVariable Long id, Model model) {
        model.addAttribute("game", adminGameService.getGameById(id));
        model.addAttribute("readOnly", true);
        model.addAttribute("formAction", "");
        return "admin/games/gameview";
    }

    @GetMapping("/new")
    public String showCreateAdminGameForm(Model model) {
        model.addAttribute("game", new AdminGameDTO());
        model.addAttribute("readOnly", false);
        model.addAttribute("formAction", "/admin/games/new");
        return "admin/games/form";
    }

    @PostMapping("/new")
    public String createAdminGame(@Valid @ModelAttribute("game") AdminGameDTO adminGameDTO, BindingResult result, Model model, RedirectAttributes ra){
        if (result.hasErrors()) {
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/admin/games/new");
            model.addAttribute("game", adminGameDTO);
            return "admin/games/form";
        }

        try {
            adminGameService.checkIfGameDateIsCorrect(adminGameDTO);
        } catch (GameTimeStatusException e) {
            result.rejectValue("gameDate", "gameDate.invalid", e.getMessage());
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/admin/games/new");
            model.addAttribute("game", adminGameDTO);
            return "admin/games/form";
        }
        adminGameService.addGame(adminGameDTO);
        ra.addFlashAttribute("message", "Game created");
        return "redirect:/admin/games";

    }


    @GetMapping("/edit/{id}")
    public String showEditAdminGameForm(@PathVariable Long id, Model model) {
        AdminGameDTO game = adminGameService.getGameById(id);
        model.addAttribute("game", game);
        model.addAttribute("readOnly", false);
        model.addAttribute("formAction", "/admin/games/edit/" + id);
        return "admin/games/form";
    }

    @PostMapping("/edit/{id}")
    public String updateAdminGame(@PathVariable Long id,
                                  @Valid @ModelAttribute("game") AdminGameDTO adminGameDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/admin/games/edit/" + id);
            model.addAttribute("game", adminGameDTO);
            return "admin/games/form";
        }

        try {
            adminGameService.checkIfGameDateIsCorrect(adminGameDTO);
        } catch (GameTimeStatusException e) {
            result.rejectValue("gameDate", "gameDate.invalid", e.getMessage());
            model.addAttribute("readOnly", false);
            model.addAttribute("formAction", "/admin/games/edit/" + id);
            model.addAttribute("game", adminGameDTO);
            return "admin/games/form";
        }

        adminGameService.updateGame(id, adminGameDTO);
        ra.addFlashAttribute("message", "Game updated");
        return "redirect:/admin/games";
    }




}
