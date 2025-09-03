package pl.xxx.demo.UserPoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.User.User;

import java.util.List;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {
    List<UserPoints> findByUser(User user);

}


