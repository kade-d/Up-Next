package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

public class GauntletLevelPickerController {

    @FXML
    private List<Button> levelButtons;

    void initialize(final Controller mainController) {
        int i;
        for (i = 0; i < levelButtons.size(); i++) {
            final Button button = levelButtons.get(i);
            final int j = i + 1;
            button.setOnAction(makeEventHandlerForLevel(j, mainController));
        }
    }

    private EventHandler<ActionEvent> makeEventHandlerForLevel(int buttonIndex, final Controller mainController) {
        EventHandler eventHandler = null;
        if (buttonIndex == 1) {
            eventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {
                    mainController.startTicTacToe();
                }
            };
        } else if (buttonIndex == 2) {
            eventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {
                    mainController.startSimon();
                }
            };
        } else if (buttonIndex == 3) {
            eventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {
                    mainController.startMemoryCard();
                }
            };
        }
        return eventHandler;
    }
}
