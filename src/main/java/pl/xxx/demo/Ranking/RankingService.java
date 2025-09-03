package pl.xxx.demo.Ranking;

import org.springframework.stereotype.Service;
import pl.xxx.demo.User.User;
import pl.xxx.demo.User.UserRepository;
import pl.xxx.demo.UserPoints.UserPoints;
import pl.xxx.demo.UserPoints.UserPointsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final UserRepository userRepository;
    private final UserPointsRepository userPointsRepository;

    public RankingService(UserRepository userRepository, UserPointsRepository userPointsRepository) {
        this.userRepository = userRepository;
        this.userPointsRepository = userPointsRepository;
    }

    public List<RankingDTO> getRanking() {
        List<User> users = userRepository.findAll();
        List<RankingDTO> ranking = new ArrayList<>();
        for (User user : users) {
            int totalPoints = userPointsRepository.findByUser(user)
                    .stream()
                    .mapToInt(UserPoints::getPoints)
                    .sum();

            ranking.add(new RankingDTO(user.getId(), user.getUsername(), totalPoints));
        }
        return ranking.stream()
                .sorted((a, b) -> b.getTotalPoints().compareTo(a.getTotalPoints()))
                .collect(Collectors.toList());
    }

}
