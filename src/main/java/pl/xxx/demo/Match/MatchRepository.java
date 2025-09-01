package pl.xxx.demo.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Start.Start;



@Repository
public interface MatchRepository  extends JpaRepository<Match, Long> {

}

