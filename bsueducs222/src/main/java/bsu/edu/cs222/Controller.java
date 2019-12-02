package bsu.edu.cs222;

import bsu.edu.cs222.FileIO.FileIO;
import bsu.edu.cs222.FileIO.Game;
import bsu.edu.cs222.Games.Maze.MazeController;
import bsu.edu.cs222.Games.Minesweeper.MinesweeperController;
import bsu.edu.cs222.Games.Simon.SimonController;
import bsu.edu.cs222.Games.Snake.SnakeController;
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
    public Label gameName;

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
    private Pane snake;

    @FXML
    private Pane hangman;

    @FXML
    private Button scoreboardButton;

    @FXML
    private Button startGauntletButton;

    @FXML
    public Label gameNotificationLabel;

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
    private SnakeController snakeController;

    @FXML
    private Rectangle winBlink;

    @FXML
    private Rectangle loseBlink;

    public ArrayList<String> gameList = new ArrayList<>();

    public void initialize(){
        addGamesToGameList();
        stopwatchController.initialize();
        levelPickerController.initialize(this);
        startGauntletButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameNotificationLabel.setText("");
                startTicTacToe(0);
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

    private void addGamesToGameList(){
        gameList.add("Tic Tac Toe");
        gameList.add("Simon");
        gameList.add("Minesweeper");
        gameList.add("Maze");
        gameList.add("Snake");
    }

    public void startTicTacToe(int mode) {
        resetGamePane();
        restartStopwatch();
        ticTacToeController.initialize(this, mode);
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    public void startSimon(int mode) {
        if (mode == 1) {
            restartStopwatch();
        }
        simonController.initialize(this, mode);
        resetGamePane();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    public void startMinesweeper(int mode) {
        if (mode == 1) {
            restartStopwatch();
        }
        minesweeperController.initialize(this, mode);
        resetGamePane();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    public void startMaze(int mode) {
        if (mode == 1) {
            restartStopwatch();
        }
        mazeController.initialize(this, mode);
        resetGamePane();
        maze.setVisible(true);
        gameName.setText("Maze");
    }

    public void startSnake(int mode) {
        if (mode == 1) {
            restartStopwatch();
        }
        snakeController.initialize(this, mode);
        resetGamePane();
        snake.setVisible(true);
        gameName.setText("Snake");
    }

    private void resetGamePane() {
        Pane[] gamePaneList = new Pane[]{ticTacToe, simon, minesweeper, maze, snake, scoreboard};
        for (Pane pane : gamePaneList) {
            pane.setVisible(false);
        }
    }

    public void restartStopwatch() {
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
        notifyWin();
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
                    } else {
                        super.stop();
                    }
                }
            }
        };
    }
}
