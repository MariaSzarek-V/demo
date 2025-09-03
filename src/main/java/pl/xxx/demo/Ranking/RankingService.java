package pl.xxx.demo.Ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPoints;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankingService {

    private final UserRepository userRepository;
    private final UserPointsRepository userPointsRepository;



    public List<RankingDTO> getRanking() {
        List<User> users = userRepository.findAll();
        List<RankingDTO> ranking = new ArrayList<>();
        for (User user : users) {
            Integer totalPoints = userPointsRepository.sumPointsByUserId(user.getId());
            if (totalPoints == null) {
                totalPoints = 0;
            }

            RankingDTO dto = new RankingDTO(
                    user.getId(),
                    user.getUsername(),
                    totalPoints
            );
            ranking.add(dto);

        }
        ranking.sort((a, b) -> b.getTotalPoints() - a.getTotalPoints());
        return ranking;
    }
}
