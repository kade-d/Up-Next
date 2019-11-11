package bsu.edu.cs222;

public class Minesweeper {

    //TODO Author: Adam

    final MinesweeperGameState gameState = new MinesweeperGameState();

    void startGame(){
        gameState.makeNewBoard();
    }
}
