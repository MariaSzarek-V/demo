package pl.xxx.demo.Prediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Start.Start;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
}

