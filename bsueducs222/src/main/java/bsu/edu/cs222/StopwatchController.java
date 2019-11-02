package bsu.edu.cs222;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StopwatchController {

    @FXML
    private Label stopwatchLabel;

    AnimationTimer stopwatch;

    void initialize() {
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
                if (timestamp + 1000 <= newTime) {
                    long deltaT = (newTime - timestamp) / 1000;
                    time += deltaT;
                    timestamp += 1000 * deltaT;
                    label.setText(Long.toString(time));
                }
            }
        };
    }
}
