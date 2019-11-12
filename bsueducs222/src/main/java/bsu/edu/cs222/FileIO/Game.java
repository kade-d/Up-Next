package bsu.edu.cs222.FileIO;

public class Game {
    private final String gameName;
    private final Boolean gameCompleted;


    public Game(String gameName, Boolean gameCompleted) {
        this.gameName = gameName;
        this.gameCompleted = gameCompleted;
    }

    public String getGameName() {
        return gameName;
    }

    public Boolean getGameCompleted() {
        return gameCompleted;
    }
}
