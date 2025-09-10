package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<RankingDTO>> getRanking() {
        List<RankingDTO> rankingDTOList = rankingService.getCurrentRanking();
        return ResponseEntity.ok(rankingDTOList);
    }


}
