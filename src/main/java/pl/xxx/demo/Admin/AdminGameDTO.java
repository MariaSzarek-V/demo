package pl.xxx.demo.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 AdminGameDTO

 mimo, ze identyczny jak Game to nie chcę używac Entiity bezposrednio
 bo bezpieczenstwo
 mozna zerobic rozne widoki dla userow i dla admina
 niezaleznosc od bazy danych
 moze bedzie mi potrzebe tez UserGameDTO
 **/


@Getter
@Setter
public class AdminGameDTO {

    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime gameDate;

}
