package pl.xxx.demo.Prediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByCalculatedFalse();

    List<Prediction> findByCalculatedTrue();

    List<Prediction> findByUserId(Long userId);
    List<Prediction> findByUserUsername(String username);
    List<Prediction> findByGameId(Long gameId);
}

