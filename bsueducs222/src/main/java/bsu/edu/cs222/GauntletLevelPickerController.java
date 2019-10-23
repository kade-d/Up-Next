package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class GauntletLevelPickerController {

    @FXML
    private List<Button> levelButtons;

    void initialize(Controller mainController) {
        setLevelButtonHandlers(mainController);
        disableLockedLevels();
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
        if (buttonIndex == 1) {
            eventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mainController.startTicTacToe();
                }
            };
        } else if (buttonIndex == 2) {
            eventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mainController.startSimon();
                }
            };
        } else if (buttonIndex == 3) {
            eventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    mainController.startMemoryCard();
                }
            };
        }
        return eventHandler;
    }

    private void disableLockedLevels() {
        int unlockedLevels = getLevelNumbersForUnlockedLevels();
        for (int j = 0; j < unlockedLevels + 1; j++) {
            levelButtons.get(j).setDisable(false);
        }
        for (int i = unlockedLevels + 1; i < levelButtons.size(); i++) {
            levelButtons.get(i).setDisable(true);
        }
    }

    private int getLevelNumbersForUnlockedLevels() {
        int unlockedLevels = 0;
        ArrayList<Game> gameProgress = getGameProgress();
        gameProgress.size();
        for (Game game : gameProgress) {
            if (game.getGameCompleted()) {
                unlockedLevels += 1;
            }
        }
        return unlockedLevels;
    }

    private ArrayList<Game> getGameProgress() {
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        return fileIO.readXML(filePath);
    }
}
