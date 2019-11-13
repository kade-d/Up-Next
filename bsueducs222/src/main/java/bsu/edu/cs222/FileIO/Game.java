package bsu.edu.cs222.FileIO;

public class Game {
    private final String gameName;
    private final Boolean gameCompleted;
    private final String score;

    public Game(String gameName, Boolean gameCompleted, String score) {
        this.gameName = gameName;
        this.gameCompleted = gameCompleted;
        this.score = score;
    }

    public String getGameName() {
        return gameName;
    }

    public Boolean getGameCompleted() {
        return gameCompleted;
    }

    public String getScore() {
        return score;
    }
}
