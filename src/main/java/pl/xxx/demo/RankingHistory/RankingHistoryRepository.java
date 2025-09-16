package pl.xxx.demo.RankingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingHistoryRepository extends JpaRepository<RankingHistory, Long> {

    Optional<RankingHistory> findFirstByOrderByIdDesc();
    List<RankingHistory> findByGameId(Long gameId);

    @Query("select max(rh.gameId) from RankingHistory rh WHere rh.gameId < :currentGameId")
    Optional<Long> findPreviousGameId(@Param("currentGameId") Long currentGameId);

    Optional<RankingHistory> findByGameIdAndUser(Long gameId, User user);
    boolean existsByGameId(Long gameId);
}
