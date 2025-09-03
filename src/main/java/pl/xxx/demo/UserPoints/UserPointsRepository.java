package pl.xxx.demo.UserPoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Prediction.Prediction;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {
}


