package pl.xxx.demo.UserPoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {

    @Query("SELECT SUM(up.points) FROM UserPoints up WHERE up.user.id = :userId")
    Integer sumPointsByUserId(@Param("userId") Long userId);

    // Pobierz wszystkie punkty użytkownika posortowane od najnowszych
    List<UserPoints> findByUserIdOrderByIdDesc(Long userId);

    // Znajdź UserPoints dla konkretnej predykcji
    java.util.Optional<UserPoints> findByPredictionId(Long predictionId);

    // Zsumuj punkty użytkownika do konkretnego meczu włącznie
    @Query("SELECT SUM(up.points) FROM UserPoints up " +
           "JOIN up.prediction p " +
           "WHERE up.user.id = :userId AND p.game.id <= :gameId")
    Integer sumPointsByUserIdUpToGame(@Param("userId") Long userId, @Param("gameId") Long gameId);

}


