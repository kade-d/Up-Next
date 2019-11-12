package bsu.edu.cs222.Games.Maze;

import bsu.edu.cs222.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class MazeController {

    @FXML
    private Circle ball;

    @FXML
    private Circle coin;

    @FXML
    private AnchorPane mazePane;

    private Controller mainController;

    private final double circleSpeed = 150;
    private final double minX = -230;
    private final double maxX = 230;
    private final double minY = -230;
    private final double maxY = 230;
    private DoubleProperty circleXVelocity = new SimpleDoubleProperty();
    private DoubleProperty circleYVelocity = new SimpleDoubleProperty();
    private LongProperty lastUpdateTime = new SimpleLongProperty();

    public void initialize(Controller controller) {
        this.mainController = controller;
        startMaze();

    }

    private void startMaze(){
        mazePane.setFocusTraversable(true);
        makeBallMovementTimer(ball, circleXVelocity, circleYVelocity, lastUpdateTime).start();
        moveBallOnArrowPress();
        resetSprites();
        makeCoinDetectionTimer(ball, coin, ball.getRadius(), coin.getRadius()).start();
        getFocusForGame();
    }

    private void resetSprites(){
        coin.setVisible(true);
        coin.setTranslateX(-100);
        coin.setTranslateY(100);
        circleXVelocity.set(0);
        circleYVelocity.set(0);
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }

    private AnimationTimer makeBallMovementTimer(final Circle circle, final DoubleProperty circleXVelocity, final DoubleProperty circleYVelocity, final LongProperty lastUpdateTime) {
        return new AnimationTimer() {

            @Override
            public void start() {
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
            }

            @Override
            public void handle(long now) {
                if (lastUpdateTime.get() > 0) {
                    final double elapsedSeconds = (now - lastUpdateTime.get()) / 1000000000.0;
                    final double deltaX = elapsedSeconds * circleXVelocity.get();
                    final double deltaY = elapsedSeconds * circleYVelocity.get();
                    final double oldX = circle.getTranslateX();
                    final double oldY = circle.getTranslateY();
                    final double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
                    final double newY = Math.max(minY, Math.min(maxY, oldY + deltaY));
                    circle.setTranslateX(newX);
                    circle.setTranslateY(newY);
                }
                lastUpdateTime.set(now);
            }
        };
    }

    private AnimationTimer makeCoinDetectionTimer(final Circle ball, final Circle coin, final double ballRadius, final double coinRadius){
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                final double ballCenterX = 230 + ball.getTranslateX();
                final double ballCenterY = 230 + ball.getTranslateY();
                final double coinCenterX = 239 + coin.getTranslateX();
                final double coinCenterY = 239 + coin.getTranslateY();
                double distance = findDistanceBetweenPoints(ballCenterX + ballRadius, ballCenterY + ballRadius, coinCenterX + coinRadius, coinCenterY + coinRadius);
                if(distance < ballRadius + coinRadius){
                    winMaze();
                    //Check distance between X and Y. Then compare to radius.
                }
            }
        };
    }

    private double findDistanceBetweenPoints(double firstX, double firstY, double secondX, double secondY){
        double dBetweenX = secondX - firstX;
        double dBetweenY = secondY - firstY;
        return Math.sqrt((dBetweenX*dBetweenX) + (dBetweenY*dBetweenY));
    }

    private void moveBallOnArrowPress() {
        mazePane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    circleXVelocity.set(circleSpeed);
                } else if (event.getCode() == KeyCode.LEFT) {
                    circleXVelocity.set(-circleSpeed);
                } else if (event.getCode() == KeyCode.UP) {
                    circleYVelocity.set(-circleSpeed);
                } else if (event.getCode() == KeyCode.DOWN) {
                    circleYVelocity.set(circleSpeed);
                }
                event.consume();
            }
        });
        mazePane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT && circleXVelocity.get() > 0) { //Must check velocity because left key could be pressed as well.
                    circleXVelocity.set(0);
                } else if (event.getCode() == KeyCode.LEFT && circleXVelocity.get() < 0){
                    circleXVelocity.set(0);
                } else if (event.getCode() == KeyCode.UP && circleYVelocity.get() < 0) {
                    circleYVelocity.set(0);
                } else if (event.getCode() == KeyCode.DOWN && circleYVelocity.get() > 0) {
                    circleYVelocity.set(0);
                }
            }
        });
    }

    private void getFocusForGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mazePane.requestFocus();
            }
        });
    }

    private void winMaze(){
        coin.setVisible(false);
        mainController.notifyGauntletCompleted();
    }

}
