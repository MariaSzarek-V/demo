package pl.xxx.demo.RankingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.User.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingHistoryRepository extends JpaRepository<RankingHistory, Long> {
    /*
    metody:
    - findByuser?
    - findby game
    - find user history with games
    - find top by user id order by ctreade at desc

     */
    // ostatni wpis rankingu dla danego usera i gry
    Optional<RankingHistory> findTopByGameIdAndUserOrderByIdDesc(Long gameId, User user);

    List<RankingHistory> findAllByGameId(Long gameId);

    //przebieg Usera pozycji w rankingach
    List<RankingHistory> findAllByUserOrderByGameIdDesc(User user);

    //3 pierwszych userow z meczu o id ??
    List<User> findTop3ByGameIdOrderByTotalPointsDesc(Long gameId);
}
