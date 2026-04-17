package pl.xxx.demo.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.xxx.demo.Country.Country;
import pl.xxx.demo.Country.CountryRepository;
import pl.xxx.demo.Game.Game;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AdminGameDTOMapper {

    private final CountryRepository countryRepository;

    public void updateGameFromDto(AdminGameDTO dto, Game game) {
        if (dto.getHomeCountryId() != null) {
            Country homeCountry = countryRepository.findById(dto.getHomeCountryId())
                    .orElseThrow(() -> new RuntimeException("Home country not found"));
            game.setHomeCountry(homeCountry);
        }
        if (dto.getAwayCountryId() != null) {
            Country awayCountry = countryRepository.findById(dto.getAwayCountryId())
                    .orElseThrow(() -> new RuntimeException("Away country not found"));
            game.setAwayCountry(awayCountry);
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

    public AdminGameDTO convertToAdminGameDTO(Game game) {
        return AdminGameDTO.builder()
                .id(game.getId())
                .homeTeam(game.getHomeCountry() != null ? game.getHomeCountry().getName() : null)
                .awayTeam(game.getAwayCountry() != null ? game.getAwayCountry().getName() : null)
                .homeCountryId(game.getHomeCountry() != null ? game.getHomeCountry().getId() : null)
                .awayCountryId(game.getAwayCountry() != null ? game.getAwayCountry().getId() : null)
                .homeCountryCode(game.getHomeCountry() != null ? game.getHomeCountry().getCode() : null)
                .awayCountryCode(game.getAwayCountry() != null ? game.getAwayCountry().getCode() : null)
                .homeScore(game.getHomeScore())
                .awayScore(game.getAwayScore())
                .gameDate(game.getGameDate())
                .gameStatus(game.getGameStatus())
                .build();
    }

    public Game convertToAdminGame(AdminGameDTO dto) {
        Country homeCountry = countryRepository.findById(dto.getHomeCountryId())
                .orElseThrow(() -> new RuntimeException("Home country not found"));
        Country awayCountry = countryRepository.findById(dto.getAwayCountryId())
                .orElseThrow(() -> new RuntimeException("Away country not found"));

        return Game.builder()
                .id(dto.getId())
                .homeCountry(homeCountry)
                .awayCountry(awayCountry)
                .homeScore(dto.getHomeScore())
                .awayScore(dto.getAwayScore())
                .gameDate(dto.getGameDate())
                .gameStatus(dto.getGameStatus())
                .build();
    }


    public List<AdminGameDTO> convertToAdminGameDTOList(List<Game> games) {
        return games.stream()
                .map(this::convertToAdminGameDTO)
                .toList();
    }
}
