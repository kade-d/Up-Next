package bsu.edu.cs222;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Controller extends MainMenu {

    @FXML
    Pane gamePane;

    @FXML
    Label gameName;

    @FXML
    private Pane ticTacToe;

    @FXML
    private Pane simon;

    @FXML
    private Pane minesweeper;

    @FXML
    private Button startGauntletButton;

    @FXML
    private Button restartGauntletButton;

    @FXML
    private StopwatchController stopwatchController; //Assigned but IDE doesn't recognize.

    @FXML
    private TicTacToeController ticTacToeController;

    @FXML
    private SimonController simonController;

    @FXML
    private MinesweeperController minesweeperController;


    public void initialize(){
        stopwatchController.initialize();
        startGauntletButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTicTacToe();
            }
        });
        restartGauntletButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetGamePane();
                startTicTacToe();
            }
        });
    }

    void startTicTacToe() {
        ticTacToeController.initialize(this);
        resetGamePane();
        restartStopwatch();
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    void startMemoryCard() {
        resetGamePane();
        gameName.setText("Memory Match");
    }

    void startSimon() {
        simonController.initialize(this);
        resetGamePane();
        restartStopwatch();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    void startMinesweeper() {
        minesweeperController.initialize(this);
        resetGamePane();
        restartStopwatch();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    private void restartStopwatch() {
        stopwatchController.stopwatch.stop();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
    }

    AnimationTimer getStopwatch() {
        return this.stopwatchController.stopwatch;
    }
}
