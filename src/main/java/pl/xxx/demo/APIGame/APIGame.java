package pl.xxx.demo.APIGame;

import jakarta.persistence.*;

@Entity
public class APIGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private String matchDate;

}
