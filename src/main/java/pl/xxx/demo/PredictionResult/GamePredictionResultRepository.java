package pl.xxx.demo.PredictionResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.xxx.demo.Game.Game;

import java.util.List;

public interface GamePredictionResultRepository extends JpaRepository<Game, Long> {

    //
    @Query("""
        SELECT new pl.xxx.demo.PredictionResult.GamePredictionResultDTO(
        :userId,
        g.id, 
        g.homeTeam,
        g.awayTeam, 
        g.homeScore,
        g.awayScore,
        g.gameDate,
        g.gameStatus,
        p.predictedHomeScore,
        p.predictedAwayScore,
        p.id,
        up.points,
        up.id
        
        )
        FROM Game g
        
        LEFT JOIN Prediction p ON p.game.id = g.id AND p.user.id = :userId
        LEFT JOIN UserPoints up ON up.prediction.id = p.id AND up.user.id = :userId
        WHERE g.gameStatus <> pl.xxx.demo.Enum.GameStatus.ADMIN_VIEW
        ORDER BY g.gameDate DESC
""")
    List<GamePredictionResultDTO> findAllGamesWithUserPedictionAndPoints(@Param("userId") Long userId);

}
