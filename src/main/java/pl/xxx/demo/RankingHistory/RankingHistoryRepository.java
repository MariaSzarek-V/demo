package pl.xxx.demo.RankingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.League.League;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingHistoryRepository extends JpaRepository<RankingHistory, Long> {

    Optional<RankingHistory> findFirstByOrderByIdDesc();
    List<RankingHistory> findByGameIdOrderByPositionAsc(Long gameId);
    List<RankingHistory> findByGameIdAndLeagueIdOrderByPositionAsc(Long gameId, Long leagueId);

    @Query("select max(rh.gameId) from RankingHistory rh WHere rh.gameId < :currentGameId")
    Optional<Long> findPreviousGameId(@Param("currentGameId") Long currentGameId);

    Optional<RankingHistory> findByGameIdAndUser(Long gameId, User user);
    Optional<RankingHistory> findByGameIdAndUserAndLeague(Long gameId, User user, League league);
    boolean existsByGameId(Long gameId);

    // Pobierz historię rankingu użytkownika posortowaną po gameId
    List<RankingHistory> findByUserIdOrderByGameIdAsc(Long userId);

    // Pobierz całą historię dla danej ligi
    List<RankingHistory> findByLeagueId(Long leagueId);

    @Query("SELECT rh FROM RankingHistory rh WHERE rh.gameId = (SELECT MAX(rh2.gameId) FROM RankingHistory rh2) ORDER BY rh.position ASC")
    List<RankingHistory> findLatestRanking();

    @Query("SELECT rh FROM RankingHistory rh WHERE rh.league.id = :leagueId AND rh.gameId = (SELECT MAX(rh2.gameId) FROM RankingHistory rh2 WHERE rh2.league.id = :leagueId) ORDER BY rh.position ASC")
    List<RankingHistory> findLatestRankingByLeague(@Param("leagueId") Long leagueId);
}
