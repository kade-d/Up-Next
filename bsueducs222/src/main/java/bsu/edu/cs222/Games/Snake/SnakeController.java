package bsu.edu.cs222.Games.Snake;
import bsu.edu.cs222.Controller;
import bsu.edu.cs222.FileIO.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.BitSet;

public class SnakeController {

    @FXML
    private GridPane pane;

    private Controller mainController;

    private BitSet keyboardBitSet = new BitSet();

    private ChangeListener<Boolean> listener = null;

    private SnakeGameState gameState;

    private int boardWidth = 25;

    private int boardHeight = 25;

    private int gameSpeed = 50;

    private int goalLength = 30;

    private int mode;

    private boolean gameIsRunning = false;

    private enum KEY {
        UP(0),
        RIGHT(1),
        DOWN(2),
        LEFT(3);

        private final int value;

        KEY(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }

    public void initialize(Controller controller, int mode) {
        this.mainController = controller;
        this.mode = mode;
        if(listener != null) {
            pane.focusedProperty().removeListener(listener);
        }
        startSnake();
        listener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                gameIsRunning = newValue;
                if(gameIsRunning){
                    run();
                }
            }
        };
        pane.focusedProperty().addListener(listener);
    }

    private void startSnake(){
        gameState = new SnakeGameState(mainController, boardWidth, boardHeight);
        resetPane();
        addCellsToGridPane();
        pane.setFocusTraversable(true);
        getFocusForGame();
        getArrowInput();
    }

    private void resetPane(){
        pane.getChildren().clear();
    }

    private void addCellsToGridPane() {
        for (int i = 0; i < boardHeight; i++) {
            if(i < boardHeight - 1) {
                pane.addRow(0);
            }
            for (int j = 0; j < boardWidth; j++) {
                if(j < boardWidth - 1) {
                    pane.addColumn(0);
                }
                Rectangle cell = new Rectangle();
                cell.setWidth(Math.round(500.0 / boardWidth));
                cell.setHeight(Math.round(500.0 / boardHeight));
                pane.add(cell, j, i);
            }
        }
    }

    private void run() {
        AnimationTimer snakeTimer = makeSnakeTimer();
        snakeTimer.start();
        if(gameState.snake.size() >= goalLength){
            endSnake();
        }
    }

    private void endSnake(){
        if(mode == 0) {
            mainController.saveWinToXML(new Game("Snake", true, "0"));
            mainController.notifyGauntletCompleted();
            gameIsRunning = false;
        }
        if(mode == 1){
            mainController.notifyWin();
            startSnake();
        }
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
                if (timestamp + gameSpeed <= newTime) {
                    if(gameIsRunning) {
                        updateSnake();
                        run();
                    }
                    super.stop();
                }
            }
        };
    }

    private void getFocusForGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.requestFocus();
            }
        });
    }

    private void updateSnake(){
        gameState.moveSnake();
        updateGoalLabel();
        render();
    }

    private void updateGoalLabel(){
        mainController.gameNotificationLabel.setText("Goal: (" + gameState.snake.size() + "/" + goalLength + ")");
    }

    private void getArrowInput() {
        pane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (checkRightKeyCode(event)) {
                    keyboardBitSet.set(KEY.RIGHT.getValue());

                } else if (checkLeftKeyCode(event)) {
                    keyboardBitSet.set(KEY.LEFT.getValue());

                } else if (checkDownKeyCode(event)) {
                    keyboardBitSet.set(KEY.DOWN.getValue());

                } else if (checkUpKeyCode(event)) {
                    keyboardBitSet.set(KEY.UP.getValue());
                }
                setDirection();
                event.consume();
            }
        });
        pane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                final boolean noKeysInBitSet = checkNoKeysPressedInBitSet();
                removeKeyFromBitSet(event);
                if (checkRightKeyCode(event) && noKeysInBitSet) {
                    keyboardBitSet.set(KEY.RIGHT.getValue(), true);
                } else if (checkLeftKeyCode(event) && noKeysInBitSet) {
                    keyboardBitSet.set(KEY.LEFT.getValue(), true);
                } else if (checkDownKeyCode(event) && noKeysInBitSet) {
                    keyboardBitSet.set(KEY.DOWN.getValue(), true);
                } else if (checkUpKeyCode(event) && noKeysInBitSet) {
                    keyboardBitSet.set(KEY.UP.getValue(), true);
                }
                setDirection();
            }
        });
    }

    private void setDirection(){
        boolean upPressed = keyboardBitSet.get(KEY.UP.getValue());
        boolean rightPressed = keyboardBitSet.get(KEY.RIGHT.getValue());
        boolean downPressed = keyboardBitSet.get(KEY.DOWN.getValue());
        boolean leftPressed = keyboardBitSet.get(KEY.LEFT.getValue());

        int pressedKeys = 0;

        if(upPressed){
            pressedKeys++;
        }
        if(rightPressed){
            pressedKeys++;
        }
        if(downPressed){
            pressedKeys++;
        }
        if(leftPressed){
            pressedKeys++;
        }
        if(pressedKeys > 1){
            return;
        }

        if (upPressed && gameState.lastDirection != 2) {
            gameState.direction = 0;
        }
        if (rightPressed  && gameState.lastDirection != 3) {
            gameState.direction = 1;
        }
        if (downPressed  && gameState.lastDirection != 0) {
            gameState.direction = 2;
        }
        if (leftPressed  && gameState.lastDirection != 1) {
            gameState.direction = 3;
        }
    }

    private boolean checkNoKeysPressedInBitSet() {
        for (int i = 0; i < 4; i++) {
            if (keyboardBitSet.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    private void removeKeyFromBitSet(KeyEvent event) {
        if (checkRightKeyCode(event)) {
            keyboardBitSet.set(KEY.RIGHT.getValue(), false);
        } else if (checkLeftKeyCode(event)) {
            keyboardBitSet.set(KEY.LEFT.getValue(), false);
        } else if (checkDownKeyCode(event)) {
            keyboardBitSet.set(KEY.DOWN.getValue(), false);
        } else if (checkUpKeyCode(event)) {
            keyboardBitSet.set(KEY.UP.getValue(), false);
        }
    }
    

    private boolean checkRightKeyCode(KeyEvent event) {
        return event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D;
    }

    private boolean checkLeftKeyCode(KeyEvent event) {
        return event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A;
    }

    private boolean checkUpKeyCode(KeyEvent event) {
        return event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W;
    }

    private boolean checkDownKeyCode(KeyEvent event) {
        return event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S;
    }

    private void render(){
        ArrayList<Integer> snake = gameState.snake;
        for(int i = 0; i < pane.getChildren().size(); i++) {
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
