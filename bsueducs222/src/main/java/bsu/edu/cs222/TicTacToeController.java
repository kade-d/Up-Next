package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class TicTacToeController {

    @FXML
    GridPane board;

    @FXML
    Button cell0;

    @FXML
    private void handleCellZero() {
        cell0.setLabel("X");
        System.out.println("Cell 0 selected");
        playerMove(0);
    }

    @FXML
    private void handleCellOne() {
        playerMove(1);
    }

    @FXML
    private void handleCellTwo() {
        playerMove(2);
    }

    @FXML
    private void handleCellThree() {
        playerMove(3);
    }

    @FXML
    private void handleCellFour() {
        playerMove(4);
    }

    @FXML
    private void handleCellFive() {
        playerMove(5);
    }

    @FXML
    private void handleCellSix() {
        playerMove(6);
    }

    @FXML
    private void handleCellSeven() {
        playerMove(7);
    }

    @FXML
    private void handleCellEight() {
        playerMove(8);
    }

    private void playerMove(int cell) {

    }
}
