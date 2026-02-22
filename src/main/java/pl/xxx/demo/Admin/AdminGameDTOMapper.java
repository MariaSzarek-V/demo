package pl.xxx.demo.Admin;

import pl.xxx.demo.Game.Game;

import java.util.List;


public class AdminGameDTOMapper {

//aktualizacja encji an postawie DTO
    // TODO: This method needs refactoring to use CountryRepository for team name lookups
    public static void updateGameFromDto(AdminGameDTO dto, Game game) {
        // Country updates removed - need CountryRepository to lookup countries by name
        // if (dto.getHomeTeam() != null) {
        //     game.setHomeCountry(...); // Need repository
        // }
        // if (dto.getAwayTeam() != null) {
        //     game.setAwayCountry(...); // Need repository
        // }
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
                .homeTeam(game.getHomeCountry() != null ? game.getHomeCountry().getName() : null)
                .awayTeam(game.getAwayCountry() != null ? game.getAwayCountry().getName() : null)
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .gameDate(game.getGameDate())
                .gameStatus(game.getGameStatus())
                .build();
    }

    // TODO: This method needs refactoring to use CountryRepository for team name lookups
    public static Game convertToAdminGame(AdminGameDTO dto) {
        return Game.builder()
                .id(dto.getId())
                // .homeCountry(...) // Need repository to lookup by name
                // .awayCountry(...) // Need repository to lookup by name
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
