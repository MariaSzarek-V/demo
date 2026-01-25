package pl.xxx.demo.Game;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    public List<Game> findByGameStatus(GameStatus gameStatus);
    
    // Znajdź najbliższe mecze SCHEDULED z datą >= teraz, posortowane po dacie rosnąco
    List<Game> findByGameStatusAndGameDateAfterOrderByGameDateAsc(
            GameStatus gameStatus, 
            LocalDateTime date, 
            Pageable pageable
    );
    
    // Znajdź wszystkie mecze SCHEDULED w konkretnym dniu
    List<Game> findByGameStatusAndGameDateBetweenOrderByGameDateAsc(
            GameStatus gameStatus,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );
    
    // Znajdź ostatnie zakończone mecze, posortowane po dacie malejąco
    List<Game> findByGameStatusOrderByGameDateDesc(
            GameStatus gameStatus,
            Pageable pageable
    );

}
