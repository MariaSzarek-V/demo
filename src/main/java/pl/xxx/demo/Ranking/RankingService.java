package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.Error.ResourceNotFoundException;
import pl.xxx.demo.RankingHistory.RankingHistory;
import pl.xxx.demo.RankingHistory.RankingHistoryRepository;
import pl.xxx.demo.User.User;

import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserRepository userRepository;
    private final UserPointsRepository userPointsRepository;

    public List<RankingDTO> getCurrentRanking() {
        List<User> users = userRepository.findAll();
        List<RankingDTO> ranking = new ArrayList<>();
        for (User user : users) {
            Integer totalPoints = userPointsRepository.sumPointsByUserId(user.getId());
            if (totalPoints == null) {
                totalPoints = 0;
            }
            ranking.add(new RankingDTO(null, user.getUsername(), totalPoints));
        }
        ranking.sort((a, b) -> b.getTotalPoints() - a.getTotalPoints());

        for (int i = 0; i < ranking.size(); i++) {
            RankingDTO rankingDTO = ranking.get(i);
            rankingDTO.setPosition(i + 1);
        }
        return ranking;
    }
}