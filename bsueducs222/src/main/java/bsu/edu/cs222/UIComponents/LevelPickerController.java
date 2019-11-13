package bsu.edu.cs222.UIComponents;

import bsu.edu.cs222.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;


public class LevelPickerController { //Implementation of this class will be used in the final iteration.

    @FXML
    private List<Button> levelButtons;

    public void initialize(Controller mainController) {
        setLevelButtonHandlers(mainController);
    }

    private void setLevelButtonHandlers(Controller mainController) {
        int i;
        for (i = 0; i < levelButtons.size(); i++) {
            final Button button = levelButtons.get(i);
            final int j = i + 1;
            button.setOnAction(makeEventHandlerForLevel(j, mainController));
        }
    }

    private EventHandler<ActionEvent> makeEventHandlerForLevel(int buttonIndex, final Controller mainController) {
        EventHandler<ActionEvent> eventHandler = null;
        switch (buttonIndex) {
            case 1:
                eventHandler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mainController.startTicTacToe();
                    }
                };
                break;

            case 2:
                eventHandler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mainController.startSimon(1);
                    }
                };
                break;

            case 3:
                eventHandler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mainController.startMinesweeper(1);
                    }
                };
                break;

            case 4:
                eventHandler = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mainController.startMaze(1);
                    }
                };
                break;
        }
        return eventHandler;
    }
}
