package pl.xxx.demo.UserPoints;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPointsService {

    private final UserPointsRepository userPointsRepository;

    public UserPointsService(UserPointsRepository userPointsRepository) {
        this.userPointsRepository = userPointsRepository;
    }

    public List<UserPoints> getAll() {
        return userPointsRepository.findAll();
    }

    public Optional<UserPoints> get(Long id) {
        return userPointsRepository.findById(id);
    }

    public UserPoints add(UserPoints userPoints) {
        return userPointsRepository.save(userPoints);
    }

    public UserPoints update(Long id, UserPoints userPoints) {
        UserPoints existingUserPoints = userPointsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such user points to update"));
        existingUserPoints.setPoints(userPoints.getPoints());
        return userPointsRepository.save(userPoints);
    }


}
