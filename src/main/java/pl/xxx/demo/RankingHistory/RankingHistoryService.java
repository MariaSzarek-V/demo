package pl.xxx.demo.RankingHistory;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.NoRankingAvailableException;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.Ranking.RankingDTO;
import pl.xxx.demo.Ranking.RankingService;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RankingHistoryService {

    private final UserRepository userRepository;
    private final RankingHistoryRepository rankingHistoryRepository;
    private final RankingService rankingService;

    public List<RankingHistoryDTO> getLastRankingHistory() {
        Optional<RankingHistory> oneLastRankingHistory = rankingHistoryRepository.findFirstByOrderByIdDesc();
        if (oneLastRankingHistory.isPresent()) {
            List<RankingHistory> lastRankingHistoryList = rankingHistoryRepository.findByGameId(oneLastRankingHistory.get().getGameId());

             return lastRankingHistoryList.stream()
                    .map(rh -> new RankingHistoryDTO(
                            rh.getGameId(),
                            rh.getUser().getUsername(),
                            rh.getTotalPoints(),
                            rh.getPosition(),
                            rh.getPositionChange()
                    )).toList();
        } else {
            throw new NoRankingAvailableException();
        }

    }

    public void saveCurrentRankingToHistory(Long gameId){
        List<RankingDTO> currentRanking = rankingService.getCurrentRanking();

        for (int i=0; i<currentRanking.size(); i++){
            RankingDTO rankingDTO = currentRanking.get(i);
            User user = userRepository.findByUsername(rankingDTO.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Integer positionChange = calculatePositionChange(gameId, user, rankingDTO.getPosition());

            RankingHistory historyEntry = new RankingHistory();
            historyEntry.setGameId(gameId);
            historyEntry.setUser(user);
            historyEntry.setTotalPoints(rankingDTO.getTotalPoints());
            historyEntry.setPosition(rankingDTO.getPosition());
            historyEntry.setPositionChange(positionChange);

            rankingHistoryRepository.save(historyEntry);
        }
    }

    private Integer calculatePositionChange(Long gameId, User user, Integer currentPosition) {
        Optional<Long> previousGameId = rankingHistoryRepository.findPreviousGameId(gameId);
        if (previousGameId.isEmpty()){
            return 0;
        }
        Optional<RankingHistory> lastHistory = rankingHistoryRepository.findByGameIdAndUser(previousGameId.get(), user);
        if (lastHistory.isPresent()) {
            Integer lastPosition = lastHistory.get().getPosition();
            return lastPosition - currentPosition;
        }
        return 0;
    }
}
