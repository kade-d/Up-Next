package bsu.edu.cs222.Games.Minesweeper;

public class Minesweeper {

    //Author: Adam

    private int bombNumber;

    public Minesweeper(int bombCount){
        this.bombNumber = bombCount;
    }

    public final MinesweeperGameState gameState = new MinesweeperGameState();

    void startGame(){
        gameState.makeNewBoard(bombNumber);
    }
}
