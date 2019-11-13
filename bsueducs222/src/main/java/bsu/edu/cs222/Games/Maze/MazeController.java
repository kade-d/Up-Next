package bsu.edu.cs222.Games.Maze;

import bsu.edu.cs222.Controller;
import bsu.edu.cs222.FileIO.Game;
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

import java.util.concurrent.ThreadLocalRandom;

public class MazeController {

    @FXML
    private Circle ball;

    @FXML
    private Circle coin;

    @FXML
    private AnchorPane mazePane;

    private Controller mainController;

    private int mode;

    private final double circleSpeed = 150;
    private double minX = -230;
    private double maxX = 230;
    private double minY = -230;
    private double maxY = 230;
    private int coinsEaten = 0;
    private DoubleProperty circleXVelocity = new SimpleDoubleProperty();
    private DoubleProperty circleYVelocity = new SimpleDoubleProperty();
    private LongProperty lastUpdateTime = new SimpleLongProperty();

    public void initialize(Controller controller, int mode) {
        this.mainController = controller;
        this.mode = mode;
        startMaze();
    }

    private void startMaze(){
        mazePane.setFocusTraversable(true);
        makeBallAnimationLoop(ball, circleXVelocity, circleYVelocity, lastUpdateTime).start();
        moveBallOnArrowPress();
        resetSprites();
        makeCoinCollisionLoop(ball, coin).start();
        getFocusForGame();
    }

    private void resetSprites(){
        minX = -230;
        maxX = 230;
        minY = -230;
        maxY = 230;
        coinsEaten = 0;
        coin.setVisible(true);
        coin.setTranslateX(-100);
        coin.setTranslateY(100);
        circleXVelocity.set(0);
        circleYVelocity.set(0);
        ball.setTranslateX(0);
        ball.setRadius(20);
        ball.setTranslateY(0);
    }

    private AnimationTimer makeBallAnimationLoop(final Circle circle, final DoubleProperty circleXVelocity, final DoubleProperty circleYVelocity, final LongProperty lastUpdateTime) {
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

    private AnimationTimer makeCoinCollisionLoop(final Circle ball, final Circle coin) {
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                final double ballRadius = ball.getRadius();
                final double coinRadius = coin.getRadius();
                final double ballCenterX = minX - ballRadius + ball.getTranslateX();
                final double ballCenterY = minY - ballRadius + ball.getTranslateY();
                final double coinCenterX = minX - coinRadius + coin.getTranslateX();
                final double coinCenterY = minY - coinRadius + coin.getTranslateY();
                double distance = findDistanceBetweenPoints(ballCenterX + ballRadius, ballCenterY + ballRadius, coinCenterX + coinRadius, coinCenterY + coinRadius);
                if(distance < ballRadius + coinRadius){
                    notifyCoinWasEaten();
                }
            }
        };
    }

    private void notifyCoinWasEaten() {
        coinsEaten += 1;
        growBall(ball);
        coin.setVisible(false);
        double newX = ThreadLocalRandom.current().nextDouble(minX, maxX);
        double newY = ThreadLocalRandom.current().nextDouble(minY, maxY);
        coin.setTranslateX(newX);
        coin.setTranslateY(newY);
        coin.setVisible(true);
        if (coinsEaten >= 5) {
            winMaze();
        }
    }

    private void moveBallOnArrowPress() {
        mazePane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (checkRightKeyCode(event)) {
                    circleXVelocity.set(circleSpeed);
                } else if (checkLeftKeyCode(event)) {
                    circleXVelocity.set(-circleSpeed);
                } else if (checkDownKeyCode(event)) {
                    circleYVelocity.set(circleSpeed);
                } else if (checkUpKeyCode(event)) {
                    circleYVelocity.set(-circleSpeed);
                }
                event.consume();
            }
        });
        mazePane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Must check velocity because left key could be pressed as well. Without this, velocity would be set to 0 when it shouldn't.
                if (checkRightReleasedAndPositiveVelocity(event)) {
                    circleXVelocity.set(0);
                } else if (checkLeftReleasedAndNegativeVelocity(event)) {
                    circleXVelocity.set(0);
                } else if (checkDownReleasedAndPositiveVelocity(event)) {
                    circleYVelocity.set(0);
                } else if (checkUpReleasedAndNegativeVelocity(event)) {
                    circleYVelocity.set(0);
                }
            }
        });
    }

    private void growBall(final Circle ball) {
        double radius = ball.getRadius() * 1.25;
        ball.setRadius(radius);
        minX = minX + radius / 4;
        minY = minY + radius / 4;
        maxX = maxX - radius / 4;
        maxY = maxY - radius / 4;
    }

    private double findDistanceBetweenPoints(double firstX, double firstY, double secondX, double secondY) {
        double dBetweenX = secondX - firstX;
        double dBetweenY = secondY - firstY;
        return Math.sqrt((dBetweenX * dBetweenX) + (dBetweenY * dBetweenY));
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

    private boolean checkRightReleasedAndPositiveVelocity(KeyEvent event) {
        return (checkRightKeyCode(event) && circleXVelocity.get() > 0);
    }

    private boolean checkLeftReleasedAndNegativeVelocity(KeyEvent event) {
        return (checkLeftKeyCode(event) && circleXVelocity.get() < 0);
    }

    private boolean checkDownReleasedAndPositiveVelocity(KeyEvent event) {
        return (checkDownKeyCode(event) && circleYVelocity.get() > 0);
    }

    private boolean checkUpReleasedAndNegativeVelocity(KeyEvent event) {
        return (checkUpKeyCode(event) && circleYVelocity.get() < 0);
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
        mainController.saveWinToXML(new Game("Maze", true, "0"));
        if(mode == 0) {
            mainController.notifyWin();
            mainController.notifyGauntletCompleted();
        }
        else if(mode == 1){
            mainController.restartStopwatch();
            mainController.notifyWin();
            resetSprites();
        }
    }

}
