package pl.xxx.demo.Game;

import org.springframework.stereotype.Component;
import pl.xxx.demo.Admin.AdminGameDTO;
import pl.xxx.demo.Prediction.Prediction;
import pl.xxx.demo.Prediction.PredictionResponseDTO;

import java.util.List;

@Component
public class GameResponseDTOMapper {

    public static GameResponseDTO convertToGameResponseDTO(Game game) {
        return GameResponseDTO.builder()
                .homeTeam(game.getHomeTeam())
                .awayTeam(game.getAwayTeam())
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .gameDate(game.getGameDate())
                .gameStatus(game.getGameStatus())
                .build();
    }

    public static List<GameResponseDTO> convertToGameResponseDTOList(List<Game> games) {
        return games.stream()
                .map(game -> convertToGameResponseDTO(game))
                .toList();
    }

}