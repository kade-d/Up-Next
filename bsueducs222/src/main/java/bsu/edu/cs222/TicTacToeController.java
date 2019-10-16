package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TicTacToeController {

    @FXML
    GridPane board;

    @FXML
    Button cell0;

    @FXML
    Button cell1;

    @FXML
    Button cell2;

    @FXML
    Button cell3;

    @FXML
    Button cell4;

    @FXML
    Button cell5;

    @FXML
    Button cell6;

    @FXML
    Button cell7;

    @FXML
    Button cell8;

    private ArrayList<Button> cellList = new ArrayList<>();

    private int turnNumber = 0;

    private boolean gameNotStarted = true;

    private boolean gameIsPlaying = false;

    private TicTacToe game;

    private void populateCellList(){
        cellList.add(cell0);
        cellList.add(cell1);
        cellList.add(cell2);
        cellList.add(cell3);
        cellList.add(cell4);
        cellList.add(cell5);
        cellList.add(cell6);
        cellList.add(cell7);
        cellList.add(cell8);
    }

    @FXML
    private void handleCellZero() {
        if(gameNotStarted){
            startGame();
        }
        if(cell0.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell0.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellOne() {
        if(gameNotStarted){
            startGame();
        }
        if(cell1.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell1.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellTwo() {
        if(gameNotStarted){
            startGame();
        }
        if(cell2.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell2.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellThree() {
        if(gameNotStarted){
            startGame();
        }
        if(cell3.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell3.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellFour() {
        if(gameNotStarted){
            startGame();
        }
        if(cell4.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell4.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellFive() {
        if(gameNotStarted){
            startGame();
        }
        if(cell5.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell5.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellSix() {
        if(gameNotStarted){
            startGame();
        }
        if(cell6.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell6.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellSeven() {
        if(gameNotStarted){
            startGame();
        }
        if(cell7.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell7.setText("X");
            updateGameState();
            computerMove();
        }
    }

    @FXML
    private void handleCellEight() {
        if(gameNotStarted){
            startGame();
        }
        if(cell8.getText().equals("") && gameIsPlaying) {
            turnNumber++;
            cell8.setText("X");
            updateGameState();
            computerMove();
        }
    }

    private void startGame(){
        gameNotStarted = false;
        gameIsPlaying = true;
        System.out.println("Game started!");
        game = new TicTacToe();
        turnNumber = 0;
        populateCellList();
    }

    private void computerMove() {
        ArrayList<Integer> openCells = game.gameState.findOpenCells();
        turnNumber++;
        if(openCells.size() > 0 && gameIsPlaying) {
            int cell = game.computerMove();
            if(cell == -1) {
                int randomInt = (int) (Math.random() * openCells.size());
                Button chosenButton = cellList.get(openCells.get(randomInt));
                chosenButton.setText("O");
            }
            else{
                Button chosenButton = cellList.get(cell);
                chosenButton.setText("O");
            }
            updateGameState();
        }
    }

    private void updateGameState(){
        game.gameState.addMove(0, convertTextToPlayerNumber(cell0.getText()));
        game.gameState.addMove(1, convertTextToPlayerNumber(cell1.getText()));
        game.gameState.addMove(2, convertTextToPlayerNumber(cell2.getText()));
        game.gameState.addMove(3, convertTextToPlayerNumber(cell3.getText()));
        game.gameState.addMove(4, convertTextToPlayerNumber(cell4.getText()));
        game.gameState.addMove(5, convertTextToPlayerNumber(cell5.getText()));
        game.gameState.addMove(6, convertTextToPlayerNumber(cell6.getText()));
        game.gameState.addMove(7, convertTextToPlayerNumber(cell7.getText()));
        game.gameState.addMove(8, convertTextToPlayerNumber(cell8.getText()));
        if(game.gameState.checkBoard() != 0 || turnNumber == 9){
            String winner = convertPlayerNumberToString(game.gameState.checkBoard());
            System.out.println(winner + " won!");
            gameIsPlaying = false;
        }
    }

    private int convertTextToPlayerNumber(String text){
        if(text.equals("")){
            return 0;
        }
        else if(text.equals("X")){
            return 1;
        }
        else if(text.equals("O")){
            return 2;
        }
        return 0;
    }

    private String convertPlayerNumberToString(int playerNum){
        if(playerNum == 0) {
            return "Nobody";
        }
        else if(playerNum == 1){
            return "Player";
        }
        else if(playerNum == 2){
            return "Computer";
        }
        return null;
    }
}
