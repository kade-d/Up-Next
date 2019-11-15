package bsu.edu.cs222.Games.Snake;

import bsu.edu.cs222.Controller;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SnakeController {

    @FXML
    private GridPane pane;

    private Controller mainController;

    private Snake game;

    private SnakeGameState gameState = new SnakeGameState();

    private int mode;

    private boolean gameIsRunning = true;

    public void initialize(Controller controller, int mode) {
        this.mainController = controller;
        this.mode = mode;
        addCellsToGridPane();
        run();
    }

    private void addCellsToGridPane() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Rectangle cell = new Rectangle();
                cell.setWidth(25);
                cell.setHeight(25);
                pane.add(cell, j, i);
            }
        }
    }

    public void run() {
        AnimationTimer snakeTimer = makeSnakeTimer();
        snakeTimer.start();
    }


    private AnimationTimer makeSnakeTimer() {
        return new AnimationTimer() {
            private long timestamp;

            @Override
            public void start() {
                timestamp = System.currentTimeMillis();
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 1 <= newTime) {
                    updateSnake();
                    run();
                    super.stop();
                }
            }
        };
    }

    private void updateSnake(){
        gameState.moveSnake(0);
        render();
    }

    private void render(){
        ArrayList<Integer> snake = gameState.snake;
        for(int i = 1; i <= 400; i++) {
            Node cell = pane.getChildren().get(i);

            if(snake.contains(i)) {
                cell.setStyle("-fx-fill: #00ff00");
            }
            else if(gameState.food == i){
                cell.setStyle("-fx-fill: #ff0000");
            }
            else{
                cell.setStyle("-fx-fill: #000000");
            }
        }
    }
}
