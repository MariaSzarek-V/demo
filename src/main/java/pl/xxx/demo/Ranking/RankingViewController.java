package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingViewController {
    private final RankingService rankingService;

    @GetMapping
    public String list(Model model) {
        List<RankingDTO> ranking = rankingService.getCurrentRanking();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }
}