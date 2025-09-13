package pl.xxx.demo.RankingHistory;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingHistoryViewController {
    private final RankingHistoryService rankingHistoryService;

    @GetMapping
    public String rankingHistory(Model model) {
        List<RankingHistoryDTO> ranking = rankingHistoryService.getLastRankingHistory();
        model.addAttribute("ranking", ranking);
//        return "ranking";
        return "tables";
    }
}
