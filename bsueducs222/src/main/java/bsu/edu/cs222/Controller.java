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
import javafx.scene.control.Alert;
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
    private Button scoreboardButton;

    @FXML
    private Button startGauntletButton;

    @FXML
    private Button infoButton;

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

    private String currentGame = "Main Menu";

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
                gameNotificationLabel.setText("");
                currentGame = "Main Menu";
                gameName.setText("Main Menu");
            }
        });
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showInformation();
            }
        });
        infoButton.setFocusTraversable(false);
    }

    private void addGamesToGameList(){
        gameList.add("Tic Tac Toe");
        gameList.add("Simon");
        gameList.add("Minesweeper");
        gameList.add("Maze");
        gameList.add("Snake");
    }

    private void showInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(400);
        alert.setHeight(300);
        alert.setTitle("Pro Tip");
        alert.setHeaderText(currentGame + " Help");
        switch (currentGame){
            case "TicTacToe":
                alert.setContentText("Click the boxes to place Xs and get 3 in a row to win.");
                break;
            case "Simon":
                alert.setContentText("Watch which boxes light up and then click them\nin the correct order.");
                break;
            case "Minesweeper":
                alert.setContentText("Left click boxes to clear, right click to flag.\nNumbers in cleared boxes indicate how many mines\nare in the 8 surrounding tiles.\nFlag all bombs and clear all remaining spaces to win.");
                break;
            case "Maze":
                alert.setContentText("Use the arrow keys or WASD to move.\nDodge the red circles and reach the yellow coin to win.");
                break;
            case "Snake":
                alert.setContentText("Use the arrow keys or WASD to turn.\nEat the red apples to increase in size.\nDon't run into your tail.");
                break;
            case "Main Menu":
                alert.setContentText("This button can be pressed during any of the games\nto give helpful tips.\nIf you get confused by one of the games please click it.");
                break;
        }
        alert.show();
    }

    public void startTicTacToe(int mode) {
        currentGame = "TicTacToe";
        resetGamePane();
        restartStopwatch();
        ticTacToeController.initialize(this, mode);
        ticTacToe.setVisible(true);
        gameName.setText("Tic Tac Toe");
    }

    public void startSimon(int mode) {
        currentGame = "Simon";
        if (mode == 1) {
            restartStopwatch();
        }
        simonController.initialize(this, mode);
        resetGamePane();
        simon.setVisible(true);
        gameName.setText("Simon");
    }

    public void startMinesweeper(int mode) {
        currentGame = "Minesweeper";
        if (mode == 1) {
            restartStopwatch();
        }
        minesweeperController.initialize(this, mode);
        resetGamePane();
        minesweeper.setVisible(true);
        gameName.setText("Minesweeper");
    }

    public void startMaze(int mode) {
        currentGame = "Maze";
        if (mode == 1) {
            restartStopwatch();
        }
        mazeController.initialize(this, mode);
        resetGamePane();
        maze.setVisible(true);
        gameName.setText("Maze");
    }

    public void startSnake(int mode) {
        currentGame = "Snake";
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
        currentGame = "Main Menu";
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
