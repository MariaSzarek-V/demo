package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
