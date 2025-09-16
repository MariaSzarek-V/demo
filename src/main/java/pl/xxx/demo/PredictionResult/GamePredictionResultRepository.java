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
        u.username,
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
        LEFT JOIN p.user u
        LEFT JOIN UserPoints up ON up.prediction.id = p.id AND up.user.id = :userId
        WHERE g.gameStatus <> pl.xxx.demo.Enum.GameStatus.ADMIN_VIEW
        ORDER BY g.gameDate DESC
""")
    List<GamePredictionResultDTO> findAllGamesWithUserPedictionAndPoints(@Param("userId") Long userId);



    @Query("""
    SELECT new pl.xxx.demo.PredictionResult.GamePredictionResultDTO(
    u.id,
    u.username,
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
    FROM Prediction p
    JOIN p.game g
    JOIN p.user u
    LEFT JOIN UserPoints up WITH up.prediction = p AND up.user = u
    WHERE g.id = :gameId
    ORDER BY u.username ASC
""")
    List<GamePredictionResultDTO> findGameWithAllUserPredictionsAndPoints(@Param("gameId") Long gameId);


    @Query("""
    SELECT new pl.xxx.demo.PredictionResult.GamePredictionResultDTO(
        u.id,
        u.username,
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
    FROM Prediction p
    JOIN p.user u
    LEFT JOIN p.game g
    LEFT JOIN UserPoints up ON up.prediction.id = p.id AND up.user.id = u.id
    WHERE u.id = :userId
    ORDER BY g.gameDate DESC
""")
    List<GamePredictionResultDTO> findAllPredictionsByUserWithResults(@Param("userId") Long userId);


}
