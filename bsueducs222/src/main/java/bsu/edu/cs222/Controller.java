package bsu.edu.cs222;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Controller extends MainMenu {

    @FXML
    Pane gamePane;

    @FXML
    Label gameName;

    @FXML
    private GauntletLevelPickerController levelPickerController; //appends "Controller" to id of included fxml file

    @FXML
    private StopwatchController stopwatchController;

    @FXML
    private TicTacToeController ticTacToeController;

    @FXML
    private SimonController simonController;

    @FXML
    private MinesweeperController minesweeperController;

    @FXML
    private Pane ticTacToe;

    @FXML
    private Pane simon;

    @FXML
    private Pane minesweeper;

    public void initialize(){
        levelPickerController.initialize(this); //Class is GauntletLevelPickerController
        stopwatchController.initialize();
        ticTacToeController.initialize(this);
        simonController.initialize(this);
        minesweeperController.initialize(this);
    }

    void startTicTacToe() {
        resetGamePane();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    void startMemoryCard() {
        resetGamePane();
        gameName.setText("Memory Match");
    }

    void startSimon() {
        resetGamePane();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    void startMinesweeper() {
        resetGamePane();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    AnimationTimer getStopwatch() {
        return this.stopwatchController.stopwatch;
    }
}
