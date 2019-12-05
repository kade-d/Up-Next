package bsu.edu.cs222.FileIO;

public class Game {
    private final String gameName;
    private final Boolean gameCompleted;
    private final String score;
    private final String username;

    public Game(String gameName, Boolean gameCompleted, String score, String username) {
        this.gameName = gameName;
        this.gameCompleted = gameCompleted;
        this.score = score;
        this.username = username;
    }

    String getGameName() {
        return gameName;
    }

    Boolean getGameCompleted() {
        return gameCompleted;
    }

    public String getScore() {
        return score;
    }

    public String getUsername(){return username;}
}
