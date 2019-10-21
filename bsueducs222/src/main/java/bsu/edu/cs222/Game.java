package bsu.edu.cs222;

class Game {
    private String gameName;
    private Boolean gameCompleted;


    Game(String gameName, Boolean gameCompleted) {
        this.gameName = gameName;
        this.gameCompleted = gameCompleted;
    }

    String getGameName() {
        return gameName;
    }

    Boolean getGameCompleted() {
        return gameCompleted;
    }
}
