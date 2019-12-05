package bsu.edu.cs222.UIComponents;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StopwatchController {

    @FXML
    private Label stopwatchLabel;

    public AnimationTimer stopwatch;

    public void initialize() {
        stopwatch = makeTimer(stopwatchLabel);
    }

    public void resetStopwatch() {
        stopwatchLabel.setText("0.0");
        stopwatch = makeTimer(stopwatchLabel);
    }

    private AnimationTimer makeTimer(final Label label) {

        return new AnimationTimer() {
            private long timestamp;
            private long time = 0;
            private long fraction = 0;

            @Override
            public void start() {
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                fraction = System.currentTimeMillis() - timestamp;
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 100 <= newTime) {
                    long deltaT = (newTime - timestamp) / 100;
                    time += deltaT;
                    timestamp += 100 * deltaT;
                    label.setText(longToSeconds(time));
                }
            }
        };
    }

    private String longToSeconds(long l) {
        float seconds = (float) l / (float) 10;
        return Float.toString(seconds);
    }
}
