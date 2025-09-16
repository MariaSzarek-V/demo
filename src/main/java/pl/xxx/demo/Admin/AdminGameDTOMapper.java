package pl.xxx.demo.Admin;

import pl.xxx.demo.Game.Game;

import java.util.List;


public class AdminGameDTOMapper {

//aktualizacja encji an postawie DTO
    public static void updateGameFromDto(AdminGameDTO dto, Game game) {
        if (dto.getHomeTeam() != null) {
            game.setHomeTeam(dto.getHomeTeam());
        }
        if (dto.getAwayTeam() != null) {
            game.setAwayTeam(dto.getAwayTeam());
        }
        if (dto.getHomeScore() != null) {
            game.setHomeScore(dto.getHomeScore());
        }
        if (dto.getAwayScore() != null) {
            game.setAwayScore(dto.getAwayScore());
        }
        if (dto.getGameDate() != null) {
            game.setGameDate(dto.getGameDate());
        }
        if (dto.getGameStatus() != null) {
            game.setGameStatus(dto.getGameStatus());
        }
    }
    public static AdminGameDTO convertToAdminGameDTO(Game game) {
        return AdminGameDTO.builder()
                .id(game.getId())
                .homeTeam(game.getHomeTeam())
                .awayTeam(game.getAwayTeam())
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .gameDate(game.getGameDate())
                .gameStatus(game.getGameStatus())
                .build();
    }

    public static Game convertToAdminGame(AdminGameDTO dto) {
        return Game.builder()
                .id(dto.getId())
                .homeTeam(dto.getHomeTeam())
                .awayTeam(dto.getAwayTeam())
                .homeScore(dto.getHomeScore())
                .awayScore(dto.getAwayScore())
                .gameDate(dto.getGameDate())
                .gameStatus(dto.getGameStatus())
                .build();
    }


    public static List<AdminGameDTO> convertToAdminGameDTOList(List<Game> games) {
        return games.stream()
                .map(game -> convertToAdminGameDTO(game))
                .toList();
    }
}
