package pl.xxx.demo.Prediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Enum.GameStatus;

import java.util.List;


@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByUserId(Long userId);
    List<Prediction> findByGameId(Long gameId);
    List<Prediction> findByGameGameStatus(GameStatus status);
    Prediction findByUserIdAndGameId(Long userId, Long gameId);
}

