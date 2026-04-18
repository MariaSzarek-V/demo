package pl.xxx.demo.RankingHistory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/league/{leagueId}")
    public List<RankingHistoryDTO> getRankingHistoryByLeague(@PathVariable Long leagueId) {
        return rankingHistoryService.getLastRankingHistoryByLeague(leagueId);
    }

    @GetMapping("/history/all")
    public RankingHistoryChartDTO getAllUsersRankingHistory() {
        return rankingHistoryService.getAllUsersRankingHistoryForChart();
    }

    @GetMapping("/history/chart")
    public RankingHistoryChartDTO getAllUsersRankingHistoryByLeague(@RequestParam Long leagueId) {
        return rankingHistoryService.getAllUsersRankingHistoryForChartByLeague(leagueId);
    }

    @PostMapping("/history/rebuild")
    public ResponseEntity<String> rebuildRankingHistory() {
        rankingHistoryService.rebuildRankingHistory();
        return ResponseEntity.ok("Historia rankingu została pomyślnie odbudowana");
    }
}
