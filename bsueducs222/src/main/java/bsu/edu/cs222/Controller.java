package bsu.edu.cs222;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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
    private Label gameNotificationLabel;

    @FXML
    private StopwatchController stopwatchController; //Assigned but IDE doesn't recognize.

    @FXML
    private TicTacToeController ticTacToeController;

    @FXML
    private SimonController simonController;

    @FXML
    private MinesweeperController minesweeperController;

    @FXML
    private Rectangle winBlink;

    @FXML
    private Rectangle loseBlink;

    public void initialize(){
        stopwatchController.initialize();
        startGauntletButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameNotificationLabel.setText("");
                startTicTacToe();
            }
        });
    }

    void startTicTacToe() {
        resetGamePane();
        restartStopwatch();
        ticTacToeController.initialize(this);
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    void startSimon() {
        simonController.initialize(this);
        resetGamePane();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    void startMinesweeper() {
        minesweeperController.initialize(this);
        resetGamePane();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    void restartStopwatch() {
        stopwatchController.stopwatch.stop();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
    }

    void notifyWin() {
        gameNotificationLabel.setText("You won!");
        winBlink.setVisible(true);

    }

    void notifyLoss() {
        gameNotificationLabel.setText("You lost!");
    }

    void notifyGauntletCompleted() {
        gameNotificationLabel.setText("Gauntlet Completed!");
        stopwatchController.stopwatch.stop();
    }

    AnimationTimer getStopwatch() {
        return this.stopwatchController.stopwatch;
    }
}
