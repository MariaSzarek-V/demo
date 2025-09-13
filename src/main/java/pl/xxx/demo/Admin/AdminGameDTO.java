package pl.xxx.demo.Admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
 a adminGameDTO moze rozbuduje sie o created at modified at
 **/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminGameDTO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "Nazwa drużyny nie może być pusta")
    private String homeTeam;
    @NotBlank(message = "Nazwa drużyny nie może być pusta")
    private String awayTeam;
    @Min(value = 0, message = "Wynik nie może być ujemny")
    private Integer homeScore;
    @Min(value = 0, message = "Wynik nie może być ujemny")
    private Integer awayScore;
    @NotNull(message = "Data nie może być pusta")
    private LocalDateTime gameDate;
    @NotNull(message = "Status nie może być pusty")
    private GameStatus gameStatus;

    public boolean isScheduled() {
        return GameStatus.SCHEDULED.equals(this.gameStatus);
    }

}
