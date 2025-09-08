package pl.xxx.demo.RankingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingHistoryRepository extends JpaRepository<RankingHistory, Long> {
    /*
    metody:
    - findByuser?
    - findby game
    - find user history with games
    - find top by user id order by ctreade at desc

     */
}
