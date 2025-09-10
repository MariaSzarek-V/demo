//package pl.xxx.demo.Ranking;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/ranking")
//@RequiredArgsConstructor
//public class RankingController {
//
//    private final RankingService rankingService;
//
//    @GetMapping
//    public ResponseEntity<List<RankingDTO>> getRanking() {
//        List<RankingDTO> rankingDTOList = rankingService.getCurrentRanking();
//        return ResponseEntity.ok(rankingDTOList);
//    }
//
//    @PostMapping("save-to-history/{gameId}")
//    public ResponseEntity<String> saveToHistory(@PathVariable("gameId") String gameId) {
//        try {
//            rankingService.saveCurrentRankingToHistory(Long.valueOf(gameId));
//            return ResponseEntity.ok("Ranking saved for gameid " + gameId);
//
//        } catch (Exception e){
//            return ResponseEntity.internalServerError()
//                    .body("error maria error" + e.getMessage());
//        }
//    }
//
//
//
//
//
//}
