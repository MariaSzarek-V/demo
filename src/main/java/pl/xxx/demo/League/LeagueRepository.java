package pl.xxx.demo.League;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    Optional<League> findByName(String name);

    List<League> findByIsActiveTrue();

    boolean existsByName(String name);
}
