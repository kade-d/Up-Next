package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.Event;
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

    private void disableLockedLevels() {
        String unlockedLevelsString = getLevelNumbersForUnlockedLevels();
        int lengthOfLevelString = unlockedLevelsString.length();
        for (int j = 0; j < lengthOfLevelString + 1; j++) {
            levelButtons.get(j).setDisable(false);
        }
        for (int i = lengthOfLevelString + 1; i < levelButtons.size(); i++) {
            levelButtons.get(i).setDisable(true);
        }
    }

    private String getLevelNumbersForUnlockedLevels() {
        String unlockedLevelsString = "";
        ArrayList<Game> gameProgress = getGameProgress();
        for (Game game : gameProgress) {
            if (game.getGameName().equals("TicTacToe")) {
                if (game.getGameCompleted()) {
                    unlockedLevelsString = unlockedLevelsString.concat("1"); //Level 1 Completed
                }
            }
        }
        System.out.println(unlockedLevelsString);
        return unlockedLevelsString;
    }

    private ArrayList<Game> getGameProgress() {
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        return fileIO.readXML(filePath);
    }
}
