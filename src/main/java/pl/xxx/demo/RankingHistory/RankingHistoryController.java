package pl.xxx.demo.RankingHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingHistoryController {

    private final RankingHistoryService rankingHistoryService;

    @GetMapping
    public List<RankingHistoryDTO> getRankingHistory() {
        return rankingHistoryService.getLastRankingHistory();
    }
}
