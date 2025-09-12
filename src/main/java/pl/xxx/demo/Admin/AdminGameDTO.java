package pl.xxx.demo.Admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.xxx.demo.Enum.GameStatus;

import java.time.LocalDateTime;


/**
 AdminGameDTO

 mimo, ze identyczny jak Game to nie chcę używac Entiity bezposrednio
 bo bezpieczenstwo
 mozna zerobic rozne widoki dla userow i dla admina
 niezaleznosc od bazy danych
 moze bedzie mi potrzebe tez UserGameDTO
 **/

@Builder
@Getter
@Setter
public class AdminGameDTO {

    @NotBlank(message = "Nazwa drużyny nie może być pusta")
    private String homeTeam;
    @NotBlank(message = "Nazwa drużyny nie może być pusta")
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    @NotNull(message = "Data nie może być pusta")
    private LocalDateTime gameDate;
    @NotNull(message = "Status nie może być pusty")
    private GameStatus gameStatus;

}
