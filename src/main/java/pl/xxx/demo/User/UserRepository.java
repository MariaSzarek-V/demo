package pl.xxx.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUsersByEmail(String email);

    boolean existsUsersByUsername(String username);

    Optional<Object> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.userLeagues ul WHERE ul.league.id = :leagueId")
    List<User> findUsersByLeagueId(@Param("leagueId") Long leagueId);
}
