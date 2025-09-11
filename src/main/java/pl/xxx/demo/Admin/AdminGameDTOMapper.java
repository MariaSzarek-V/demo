package pl.xxx.demo.Admin;

import pl.xxx.demo.Game.Game;


public class AdminGameDTOMapper{

    public static Game toUpdatedEntity(AdminGameDTO dto, Game game){
        return Game.builder()
                .id(game.getId())
                .homeTeam(dto.getHomeTeam())
                .awayTeam(dto.getAwayTeam())
                .homeScore(dto.getHomeScore())
                .awayScore(dto.getAwayScore())
                .gameDate(dto.getGameDate())
                .gameStatus(dto.getGameStatus())
                .build();
    }

    public static Game toCreateEntity(AdminGameDTO dto){
        return Game.builder()
                .homeTeam(dto.getHomeTeam())
                .awayTeam(dto.getAwayTeam())
                .homeScore(dto.getHomeScore())
                .awayScore(dto.getAwayScore())
                .gameDate(dto.getGameDate())
                .gameStatus(dto.getGameStatus())
                .build();
    }
}
