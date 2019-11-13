package bsu.edu.cs222.Games.Minesweeper;

import bsu.edu.cs222.Controller;
import bsu.edu.cs222.FileIO.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MinesweeperController {

    @FXML
    private List<Button> cellButtons;

    private Controller mainController;

    private Minesweeper game;

    private int mode;

    public void initialize(Controller controller, int mode) {
        this.mainController = controller;
        this.mode = mode;
        game = new Minesweeper();
        setCellButtonHandlers();
        resetBoard();
        game.startGame();
    }

    private void setCellButtonHandlers() {
        for (int i = 0; i < cellButtons.size(); i++) {
            final Button button = cellButtons.get(i);
            button.setOnMouseClicked(makeEventHandlerForCell(i));
        }
    }

    private EventHandler<MouseEvent> makeEventHandlerForCell(final int buttonIndex) {
        EventHandler<MouseEvent> eventHandler;
        eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if(button == MouseButton.PRIMARY) {
                    sweepCell(buttonIndex);
                }
                else if(button == MouseButton.SECONDARY){
                    flagCell(buttonIndex);
                }
            }
        };
        return eventHandler;
    }

    private void sweepCell(int index){
        int cell = game.gameState.cells[index];
        if(game.gameState.shownCells[index] || game.gameState.flaggedCells[index]){
            return;
        }
        if(cell == -1){
            revealAllBombCells();
            checkVictory();
        }
        else if(cell == 0){
            revealClearCells(index);
            checkVictory();
        }
        else{
            cellButtons.get(index).setText("" + cell);
            cellButtons.get(index).setStyle("-fx-background-color: #ffffff");
            game.gameState.revealCell(index);
            checkVictory();
        }
    }

    private void revealAllBombCells(){
        for(int i = 0; i < cellButtons.size(); i++){
            int cell = game.gameState.cells[i];
            if(cell == -1) {
                cellButtons.get(i).setText("X");
                cellButtons.get(i).setStyle("-fx-background-color: #ff0000");
            }
        }
        mainController.notifyLoss();
        restartGame();
        if (mode == 1) {
            mainController.restartStopwatch();
        }
    }

    private void restartGame(){
        resetBoard();
        game.gameState.reset();
        game.startGame();
    }

    private void resetBoard(){
        for(Button cell : cellButtons){
            cell.setText("");
            cell.setStyle("-fx-background-color: #dbdbdb");
        }
    }
    
    private void revealClearCells(int index){
        int[] cells = game.gameState.cells;
        boolean[] shownCells = game.gameState.shownCells;
        if(shownCells[index]){
            return;
        }
        cellButtons.get(index).setStyle("-fx-background-color: #ffffff");
        game.gameState.revealCell(index);
        revealSurroundingCells(index);
        if(index == 0){ //top left
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
        else if(index < 8){ //top middle
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
        else if(index == 8){ //top right
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
        else if(index == 72){ // bottom left
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
        }
        else if(index > 72 && index < 80){ // bottom middle
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
        }
        else if(index == 80){ // bottom left
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
        }
        else if(index % 9 == 0){ // left middle
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
        else if((index + 1) % 9 == 0){ // right middle
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
        else{ //center
            if(cells[index - 9] == 0){
                revealClearCells(index - 9);
            }
            if(cells[index - 1] == 0){
                revealClearCells(index - 1);
            }
            if(cells[index + 1] == 0){
                revealClearCells(index + 1);
            }
            if(cells[index + 9] == 0){
                revealClearCells(index + 9);
            }
        }
    }

    private void revealSurroundingCells(int index){
        if(index == 0){ //top left
            sweepCell(index + 1);
            sweepCell(index + 9);
            sweepCell(index + 10);

        }
        else if(index < 8){ //top middle
            sweepCell(index - 1);
            sweepCell(index + 1);
            sweepCell(index + 8);
            sweepCell(index + 9);
            sweepCell(index + 10);
        }
        else if(index == 8){ //top right
            sweepCell(index - 1);
            sweepCell(index + 8);
            sweepCell(index + 9);
        }
        else if(index == 72){ // bottom left
            sweepCell(index - 9);
            sweepCell(index - 8);
            sweepCell(index + 1);
        }
        else if(index > 72 && index < 80){ // bottom middle
            sweepCell(index - 10);
            sweepCell(index - 9);
            sweepCell(index - 8);
            sweepCell(index - 1);
            sweepCell(index + 1);
        }
        else if(index == 80){ // bottom left
            sweepCell(index - 10);
            sweepCell(index - 9);
            sweepCell(index - 1);
        }
        else if(index % 9 == 0){ // left middle
            sweepCell(index - 9);
            sweepCell(index - 8);
            sweepCell(index + 1);
            sweepCell(index + 9);
            sweepCell(index + 10);
        }
        else if((index + 1) % 9 == 0){ // right middle
            sweepCell(index - 10);
            sweepCell(index - 9);
            sweepCell(index - 1);
            sweepCell(index + 8);
            sweepCell(index + 9);
        }
        else{ //center
            sweepCell(index - 10);
            sweepCell(index - 9);
            sweepCell(index - 8);
            sweepCell(index - 1);
            sweepCell(index + 1);
            sweepCell(index + 8);
            sweepCell(index + 9);
            sweepCell(index + 10);
        }
    }

    private void flagCell(int index){
        boolean cellIsFlagged = game.gameState.flaggedCells[index];
        if(game.gameState.shownCells[index]){
            return;
        }
        if(cellIsFlagged){
            game.gameState.unflagCell(index);
            cellButtons.get(index).setText("");
        }
        else{
            game.gameState.flagCell(index);
            cellButtons.get(index).setText("F");
            checkVictory();
        }
    }

    private void checkVictory(){
        boolean[] bombCells = game.gameState.bombCells;
        boolean[] shownCells = game.gameState.shownCells;
        boolean[] flaggedCells = game.gameState.flaggedCells;
        int shownCellCount = 0;
        int flaggedBombs = 0;
        for(int i = 0; i < 81; i++){
            if(bombCells[i] && !flaggedCells[i]){
                return;
            }
            if(bombCells[i] && flaggedCells[i]){
                flaggedBombs++;
            }
            if(shownCells[i]){
                shownCellCount++;
            }
        }
        if(flaggedBombs + shownCellCount == 81){
            if (mode == 0) {
                endMinesweeper();
            } else if (mode == 1) {
                mainController.notifyWin();
                restartGame();
                mainController.restartStopwatch();
            }
        }
    }

    private void endMinesweeper() {
        mainController.notifyWin();
        mainController.saveWinToXML(new Game("Minesweeper", true, "0"));
        mainController.startMaze(0);
    }
}
