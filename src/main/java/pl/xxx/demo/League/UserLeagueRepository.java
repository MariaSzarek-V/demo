package pl.xxx.demo.League;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLeagueRepository extends JpaRepository<UserLeague, Long> {

    List<UserLeague> findByUser(User user);

    List<UserLeague> findByLeague(League league);

    Optional<UserLeague> findByUserAndLeague(User user, League league);

    boolean existsByUserAndLeague(User user, League league);

    List<UserLeague> findByLeagueAndIsAdminTrue(League league);

    long countByLeague(League league);
}
