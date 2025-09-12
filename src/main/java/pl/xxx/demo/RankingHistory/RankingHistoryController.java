package pl.xxx.demo.RankingHistory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name="6. Ranking")
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
