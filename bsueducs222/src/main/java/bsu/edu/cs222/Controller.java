package bsu.edu.cs222;

import bsu.edu.cs222.FileIO.FileIO;
import bsu.edu.cs222.FileIO.Game;
import bsu.edu.cs222.Games.Maze.MazeController;
import bsu.edu.cs222.Games.Minesweeper.MinesweeperController;
import bsu.edu.cs222.Games.Simon.SimonController;
import bsu.edu.cs222.Games.TicTacToe.TicTacToeController;
import bsu.edu.cs222.UIComponents.LevelPickerController;
import bsu.edu.cs222.UIComponents.ScoreboardController;
import bsu.edu.cs222.UIComponents.StopwatchController;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Controller extends MainMenu {

    @FXML
    Pane gamePane;

    @FXML
    Label gameName;

    @FXML
    private Pane scoreboard;

    @FXML
    private Pane ticTacToe;

    @FXML
    private Pane simon;

    @FXML
    private Pane minesweeper;

    @FXML
    private Pane maze;

    @FXML
    private Button scoreboardButton;

    @FXML
    private Button startGauntletButton;

    @FXML
    private Label gameNotificationLabel;

    @FXML
    private ScoreboardController scoreboardController;

    @FXML
    private StopwatchController stopwatchController;

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
                startTicTacToe();
            }
        });
        scoreboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetStopwatch();
                resetGamePane();
                scoreboard.setVisible(true);
                scoreboardController.initialize();
            }
        });
    }

    public void startTicTacToe() {
        resetGamePane();
        restartStopwatch();
        ticTacToeController.initialize(this);
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    public void startSimon() {
        simonController.initialize(this);
        resetGamePane();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    public void startMinesweeper() {
        minesweeperController.initialize(this);
        resetGamePane();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    public void startMaze() {
        mazeController.initialize(this);
        resetGamePane();
        maze.setVisible(true);
        gameName.setText("Maze");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper, maze, scoreboard};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    private void restartStopwatch() {
        stopwatchController.stopwatch.stop();
        stopwatchController.resetStopwatch();
        stopwatchController.stopwatch.start();
    }

    private void resetStopwatch() {
        stopwatchController.stopwatch.stop();
        stopwatchController.resetStopwatch();
    }

    public void notifyWin() {
        makeBlinkTimer(winBlink).start();
    }

    public void saveWinToXML(Game game) {
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        ArrayList<Game> gameProgress = fileIO.readXML(filePath);
        gameProgress.add(game);
        fileIO.saveToXML(filePath, gameProgress);
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

            @Override
            public void start() {
                timestamp = System.currentTimeMillis();
                rectangle.setVisible(true);
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 500 <= newTime) {
                    if (count == 0) {
                        count += 1;
                        rectangle.setVisible(false);
                    } else {
                        super.stop();
                    }
                }
            }
        };
    }
}
