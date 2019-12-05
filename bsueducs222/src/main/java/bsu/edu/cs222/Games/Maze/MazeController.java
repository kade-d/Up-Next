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

import java.util.ArrayList;
import java.util.BitSet;

public class MazeController {

    @FXML
    private AnchorPane mazePane;

    private Ball ball;

    private Coin coin;

    private ArrayList<EnemyBall> enemyBalls;

    private Controller mainController;

    private int mode;

    private final double ballSpeed = 150;
    private final double enemySpeed = 300;
    private final double firstEnemyStartingX = 0;
    private final int distanceBetweenEnemies = 85;
    private final int enemyBallCount = 5;
    private double minX = 0;
    private double maxX = 500;
    private double minY = 0;
    private double maxY = 500;
    private boolean coinIsEaten = false;
    private BitSet keyboardBitSet = new BitSet();
    private DoubleProperty enemyBallVelocity = new SimpleDoubleProperty();
    private DoubleProperty circleXVelocity = new SimpleDoubleProperty();
    private DoubleProperty circleYVelocity = new SimpleDoubleProperty();
    private LongProperty lastUpdateTime = new SimpleLongProperty();

    private enum KEY {
        RIGHT(0),
        LEFT(1),
        UP(2),
        DOWN(3);

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
        startMaze();
    }

    private void startMaze(){
        ball = new Ball();
        coin = new Coin();
        makeEnemyBalls(); //ArrayList of EnemyBalls based on count.
        mazePane.setFocusTraversable(true);
        makeAnimationLoop(lastUpdateTime).start();
        moveBallOnArrowPress();
        resetSprites();
        makeCollisionLoop().start();
        getFocusForGame();
    }

    private void makeEnemyBalls() {
        enemyBalls = new ArrayList<>();
        for (int i = 0; i < enemyBallCount; i++) {
            enemyBalls.add(new EnemyBall());
        }
    }

    private void resetSprites(){
        mazePane.getChildren().clear();
        circleXVelocity.set(0);
        circleYVelocity.set(0);
        enemyBallVelocity.set(enemySpeed);
        setCoinPosition(coin);
        setBallStartingPosition(ball);
        setEnemyBallsStartingPosition(enemyBalls);
    }

    private void setEnemyBallsStartingPosition(ArrayList<EnemyBall> circles) {
        int count = 0;
        int deltaX;
        for (EnemyBall circle : circles) {
            count += 1;
            deltaX = count * distanceBetweenEnemies;
            circle.setTranslateX(firstEnemyStartingX + deltaX);
            circle.setTranslateY(250);
            circle.setParent(mazePane);
            circle.setVisible(true);
        }
    }

    private void setBallStartingPosition(Ball circle) {
        circle.setTranslateX(minX + 40);
        circle.setTranslateY(250);
        circle.setParent(mazePane);
        circle.setVisible(true);
    }

    private void setCoinPosition(Coin circle) {
        circle.setParent(mazePane);
        circle.setVisible(true);
        circle.setTranslateX(480);
        circle.setTranslateY(250);
    }

    private AnimationTimer makeAnimationLoop(final LongProperty lastUpdateTime) {
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
            public void handle(long now) { //called every frame
                if (lastUpdateTime.get() > 0) {
                    moveBallFromElapsedTime(now);
                    moveEnemyBallFromElapsedTime(now);
                }
                lastUpdateTime.set(now);
            }
        };
    }

    private AnimationTimer makeCollisionLoop() {
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                checkDistanceToCoin();
                checkDistanceToEnemy();
            }
        };
    }

    private void checkDistanceToCoin() {
        final double ballRadius = ball.getRadius();
        final double coinRadius = coin.getRadius();
        final double ballCenterX = minX - ballRadius + ball.getTranslateX();
        final double ballCenterY = minY - ballRadius + ball.getTranslateY();
        final double coinCenterX = minX - coinRadius + coin.getTranslateX();
        final double coinCenterY = minY - coinRadius + coin.getTranslateY();
        double distanceToCoin = findDistanceBetweenPoints(ballCenterX + ballRadius, ballCenterY + ballRadius, coinCenterX + coinRadius, coinCenterY + coinRadius);
        if (distanceToCoin < ballRadius + coinRadius) {
            if(!coinIsEaten) {
                notifyCoinWasEaten();
            }
        }
    }

    private void checkDistanceToEnemy() {
        final double ballRadius = ball.getRadius();
        for (EnemyBall enemyBall : enemyBalls) {
            final double enemyRadius = enemyBall.getRadius();
            final double ballCenterX = minX - ballRadius + ball.getTranslateX();
            final double ballCenterY = minY - ballRadius + ball.getTranslateY();
            final double enemyCenterX = minX - enemyRadius + enemyBall.getTranslateX();
            final double enemyCenterY = minY - enemyRadius + enemyBall.getTranslateY();
            double distanceToEnemy = findDistanceBetweenPoints(ballCenterX + ballRadius, ballCenterY + ballRadius, enemyCenterX + enemyRadius, enemyCenterY + enemyRadius);
            if (distanceToEnemy < ballRadius + enemyRadius) {
                notifyEnemyWasHit();
            }
        }

    }

    private void moveEnemyBallFromElapsedTime(long now) {
        EnemyBall firstEnemyBall = enemyBalls.get(0);
        double elapsedSeconds = (now - lastUpdateTime.get()) / 1000000000.0;
        double deltaY = elapsedSeconds * enemyBallVelocity.get();
        double oldY = firstEnemyBall.getTranslateY();
        double radius = firstEnemyBall.getRadius();
        double newFirstY = Math.max(minY + radius, Math.min(maxY - radius, oldY + deltaY));
        if (newFirstY == maxY - radius | newFirstY == minY + radius) {
            enemyBallVelocity.set(enemyBallVelocity.get() * -1);
            deltaY = elapsedSeconds * enemyBallVelocity.get();
            newFirstY = Math.max(minY + radius, Math.min(maxY - radius, oldY + deltaY));
        }
        int count = 0;
        for (EnemyBall enemyBall : enemyBalls) {
            if (count % 2 == 0) {
                enemyBall.setTranslateY(newFirstY);
            } else {
                enemyBall.setTranslateY(maxY - newFirstY);
            }
            count += 1;
        }
    }

    private void moveBallFromElapsedTime(long now) {
        final double elapsedSeconds = (now - lastUpdateTime.get()) / 1000000000.0;
        final double deltaX = elapsedSeconds * circleXVelocity.get();
        final double deltaY = elapsedSeconds * circleYVelocity.get();
        final double oldX = ball.getTranslateX();
        final double oldY = ball.getTranslateY();
        final double radius = ball.getRadius();
        final double newX = Math.max(minX + radius, Math.min(maxX - radius, oldX + deltaX));
        final double newY = Math.max(minY + radius, Math.min(maxY - radius, oldY + deltaY));
        ball.setTranslateX(newX);
        ball.setTranslateY(newY);
    }

    private void notifyCoinWasEaten() {
        coinIsEaten = true;
        winMaze();
    }

    private void notifyEnemyWasHit() {
        loseMaze();
    }

    private void moveBallOnArrowPress() {
        mazePane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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
                setBallVelocityFromBitSet();
                event.consume();
            }
        });
        mazePane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Must use BitSet because left key could be pressed as well. Without this,
                // velocity would be set to 0 when it shouldn't.
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
                setBallVelocityFromBitSet();
            }
        });
    }

    private void setBallVelocityFromBitSet() {
        boolean rightPressed = keyboardBitSet.get(KEY.RIGHT.getValue());
        boolean leftPressed = keyboardBitSet.get(KEY.LEFT.getValue());
        boolean upPressed = keyboardBitSet.get(KEY.UP.getValue());
        boolean downPressed = keyboardBitSet.get(KEY.DOWN.getValue());

        if (rightPressed && !leftPressed) {
            circleXVelocity.set(ballSpeed);
        }
        if (leftPressed && !rightPressed) {
            circleXVelocity.set(-ballSpeed);
        }
        if (downPressed && !upPressed) {
            circleYVelocity.set(ballSpeed);
        }
        if (upPressed && !downPressed) {
            circleYVelocity.set(-ballSpeed);
        }
        if (!rightPressed && !leftPressed || rightPressed && leftPressed) {
            circleXVelocity.set(0);
        }
        if (!downPressed && !upPressed || downPressed && upPressed) {
            circleYVelocity.set(0);
        }
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

    private boolean checkNoKeysPressedInBitSet() {
        for (int i = 0; i < 4; i++) {
            if (keyboardBitSet.get(i)) {
                return false;
            }
        }
        return true;
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

    private void getFocusForGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mazePane.requestFocus();
            }
        });
    }

    private void winMaze(){
        if(mode == 0) {
            resetSprites();
            mainController.notifyWin();
            mainController.startSnake(0);
        }
        else if(mode == 1){
            mainController.restartStopwatch();
            mainController.notifyWin();
            resetSprites();
        }
    }

    private void loseMaze() {
        mainController.notifyLoss();
        resetSprites();
    }

}
