package pl.xxx.demo.Enum;

public enum GameResult {
    HOME_WIN,
    AWAY_WIN,
    DRAW;

    public static GameResult getGameResult(int homeScore, int awayScore) {
        if (homeScore > awayScore) return HOME_WIN;
        else if (awayScore > homeScore) return AWAY_WIN;
        else return DRAW;
    }

}
