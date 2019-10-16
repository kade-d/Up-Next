package bsu.edu.cs222;

public class TicTacToe {

    //TODO Author: Adam

    public TicTacToeGameState gameState = new TicTacToeGameState();
    public String victor = null;

    public void startGame(){

    }

    public void playerMove(int cell){
        gameState.addMove(cell, 1);
        checkVictory();
    }

    public void computerMove(int cell){
        gameState.addMove(cell, 2);
        checkVictory();
    }

    public void checkVictory(){
        if(gameState.checkBoard() != 0){
            declareVictory(gameState.checkBoard());
        }
    }

    private void declareVictory(int victor){
        if(victor == 1) {
            System.out.println("Player wins!");
            this.victor = "Player";
        }
        else if(victor == 2) {
            System.out.println("Computer wins!");
            this.victor = "Computer";
        }
    }
}
