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
    private AnchorPane mazePane;

    private Controller mainController;

    private double circleSpeed = 150;
    private DoubleProperty circleXVelocity = new SimpleDoubleProperty();
    private DoubleProperty circleYVelocity = new SimpleDoubleProperty();
    private LongProperty lastUpdateTime = new SimpleLongProperty();

    public void initialize(Controller controller) {
        this.mainController = controller;
        mazePane.setFocusTraversable(true);
        AnimationTimer ballMovementTimer = makeBallMovementTimer(ball, circleXVelocity, circleYVelocity, lastUpdateTime);
        ballMovementTimer.start();
        moveBallOnArrowPress();
        getFocusForGame();
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
                    final double minX = -230;
                    final double maxX = 230;
                    final double minY = -230;
                    final double maxY = 230;
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
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                    circleXVelocity.set(0);
                    //Pauses animation... Check if the other key is pressed before setting to 0.
                } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
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

}
