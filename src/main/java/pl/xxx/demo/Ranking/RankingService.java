package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.RankingHistory.RankingHistory;
import pl.xxx.demo.RankingHistory.RankingHistoryRepository;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserDTO;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserRepository userRepository;
    private final UserPointsRepository userPointsRepository;
    private final RankingHistoryRepository rankingHistoryRepository;


    public List<RankingDTO> getCurrentRanking() {
        List<User> users = userRepository.findAll();
        List<RankingDTO> ranking = new ArrayList<>();
        for (User user : users) {
            Integer totalPoints = userPointsRepository.sumPointsByUserId(user.getId());
            if (totalPoints == null) {
                totalPoints = 0;
            }

            RankingDTO dto = new RankingDTO(
                    new UserDTO(user.getId(), user.getUsername()),
                    totalPoints
            );
            ranking.add(dto);

        }
        ranking.sort((a, b) -> b.getTotalPoints() - a.getTotalPoints());
        return ranking;
    }


    //@Transactional?
    public void saveCurrentRankingToHistory(Long gameId){
        List<RankingDTO> currentRanking = getCurrentRanking();

        for (int i=0; i<currentRanking.size(); i++){
            RankingDTO rankingDTO = currentRanking.get(i);
            User user = userRepository.findById(rankingDTO.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Integer positionChange = calculatePositionChange(gameId, user, i+1);

            RankingHistory historyEntry = new RankingHistory();
            historyEntry.setGameId(gameId);

            historyEntry.setUser(user);
            historyEntry.setTotalPoints(rankingDTO.getTotalPoints());
            historyEntry.setPosition(i+1);
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
