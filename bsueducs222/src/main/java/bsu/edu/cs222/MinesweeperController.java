package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperController {

    @FXML
    private List<Button> cellButtons;

    @FXML
    private GridPane board;

    Minesweeper game = new Minesweeper();

    @FXML
    public void initialize() {
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
        System.out.println("Cell " + index + " swept!");
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
        System.out.println("You lose!");
        for(int i = 0; i < cellButtons.size(); i++){
            int cell = game.gameState.cells[i];
            if(cell == -1) {
                cellButtons.get(i).setText("X");
                cellButtons.get(i).setStyle("-fx-background-color: #ff0000");
            }
        }
        restartGame();
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
            System.out.println("Cell " + index + " unflagged!");
            game.gameState.unflagCell(index);
            cellButtons.get(index).setText("");
        }
        else{
            System.out.println("Cell " + index + " flagged!");
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
            declareVictory();
        }
    }

    private void declareVictory(){
        System.out.println("You win!");
        saveWinToXML();
        refreshScene();
    }

    private void saveWinToXML(){
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        ArrayList<Game> gameProgress = fileIO.readXML(filePath);
        Game game = new Game("Minesweeper", true);
        gameProgress.add(game);
        fileIO.saveToXML(filePath, gameProgress);
    }

    private void refreshScene(){
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/MainMenu.fxml"));
        Stage stage = (Stage) board.getScene().getWindow();
        AnchorPane page = new AnchorPane();
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
    }
}
