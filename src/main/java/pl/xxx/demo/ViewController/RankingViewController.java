package pl.xxx.demo.ViewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xxx.demo.RankingHistory.RankingHistory;
import pl.xxx.demo.RankingHistory.RankingHistoryDTO;
import pl.xxx.demo.RankingHistory.RankingHistoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingViewController {
    private final RankingHistoryService rankingHistoryService;

    @GetMapping
    public String rankingHistory(Model model) {
        List<RankingHistoryDTO> ranking = rankingHistoryService.getLastRankingHistory();
        List<RankingHistoryDTO> emptyRanking =  new ArrayList<>();
//        model.addAttribute("ranking", emptyRanking);
        model.addAttribute("ranking", ranking);
        return "ranking";
    }
}
