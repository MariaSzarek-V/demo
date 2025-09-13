package pl.xxx.demo.Admin;

import pl.xxx.demo.Game.Game;
import pl.xxx.demo.Game.GameResponseDTO;

import java.util.List;


public class AdminGameDTOMapper {

//aktualizacja encji an postawie DTO
    public static void updateGameFromDto(AdminGameDTO dto, Game game) {
        game.setId(dto.getId());
        game.setHomeTeam(dto.getHomeTeam());
        game.setAwayTeam(dto.getAwayTeam());
        game.setHomeScore(dto.getHomeScore());
        game.setAwayScore(dto.getAwayScore());
        game.setGameDate(dto.getGameDate());
        game.setGameStatus(dto.getGameStatus());
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
