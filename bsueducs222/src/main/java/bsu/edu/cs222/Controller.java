package bsu.edu.cs222;

import bsu.edu.cs222.Games.Maze.MazeController;
import bsu.edu.cs222.Games.Minesweeper.MinesweeperController;
import bsu.edu.cs222.Games.Simon.SimonController;
import bsu.edu.cs222.Games.TicTacToe.TicTacToeController;
import bsu.edu.cs222.UIComponents.LevelPickerController;
import bsu.edu.cs222.UIComponents.StopwatchController;
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
    private Pane maze;

    @FXML
    private Button startGauntletButton;

    @FXML
    private Label gameNotificationLabel;

    @FXML
    public StopwatchController stopwatchController;

    @FXML
    private LevelPickerController levelPickerController;

    @FXML
    private TicTacToeController ticTacToeController;

    @FXML
    private SimonController simonController;

    @FXML
    private MinesweeperController minesweeperController;

    @FXML
    private MazeController mazeController;

    @FXML
    private Rectangle winBlink;

    @FXML
    private Rectangle loseBlink;

    public void initialize(){
        stopwatchController.initialize();
        levelPickerController.initialize(this);
        startGauntletButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameNotificationLabel.setText("");
                startTicTacToe(0);
            }
        });
    }

    public void startTicTacToe(int mode) {
        restartStopwatch();
        resetGamePane();
        ticTacToeController.initialize(this, mode);
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    public void startSimon(int mode) {
        if(mode == 1){
            restartStopwatch();
        }
        simonController.initialize(this, mode);
        resetGamePane();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    public void startMinesweeper(int mode) {
        if(mode == 1){
            restartStopwatch();
        }
        minesweeperController.initialize(this, mode);
        resetGamePane();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    public void startMaze(int mode) {
        if(mode == 1){
            restartStopwatch();
        }
        mazeController.initialize(this, mode);
        resetGamePane();
        maze.setVisible(true);
        gameName.setText("Maze");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper, maze};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    public void restartStopwatch() {
        stopwatchController.stopwatch.stop();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
    }

    public void notifyWin() {
        makeBlinkTimer(winBlink).start();
    }

    public void notifyLoss() {
        makeBlinkTimer(loseBlink).start();
    }

    public void notifyGauntletCompleted() {
        gameNotificationLabel.setText("Gauntlet completed!");
        makeBlinkTimer(winBlink).start();
        resetGamePane();
        stopwatchController.stopwatch.stop();
    }

    private AnimationTimer makeBlinkTimer(final Rectangle rectangle) {
        return new AnimationTimer() {
            private long timestamp;
            private int count = 0;
            private double opacity = 1;

            @Override
            public void start() {
                timestamp = System.currentTimeMillis();
                rectangle.setVisible(true);
                rectangle.setOpacity(opacity);
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if(timestamp + 100 <= newTime){
                    opacity = opacity * 0.85;
                    rectangle.setOpacity(opacity);
                }
                if (timestamp + 500 <= newTime) {
                    if (count == 0) {
                        count += 1;
                        rectangle.setVisible(false);
                        rectangle.setOpacity(1);
                    } else {
                        super.stop();
                    }
                }
            }
        };
    }
}
