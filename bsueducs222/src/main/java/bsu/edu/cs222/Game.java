package bsu.edu.cs222;

public class Game {
    private String gameName;
    private Boolean gameCompleted;


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
