package bsu.edu.cs222.UIComponents;

import bsu.edu.cs222.Controller;
import bsu.edu.cs222.FileIO.FileIO;
import bsu.edu.cs222.FileIO.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;


class GauntletLevelPickerController { //Implementation of this class will be used in the final iteration.

    @FXML
    private List<Button> levelButtons;

    public void initialize(Controller mainController) {
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
                    mainController.startMinesweeper();
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
        boolean[] gamesWonArray = new boolean[3];
        String[] gamesArray = new String[]{"TicTacToe", "Simon", "Minesweeper"};
        int unlockedLevels = 0;

        ArrayList<Game> gameProgress = getGameProgress();
        for (Game game : gameProgress) {
            int indexOfGame = stringSearch(gamesArray, game.getGameName()); //returns index of game name in games array.
            if (!gamesWonArray[indexOfGame]) {
                if (game.getGameCompleted()) {
                    unlockedLevels += 1;
                    gamesWonArray[indexOfGame] = true;
                }
            }
        }
        return unlockedLevels;
    }

    private int stringSearch(String[] array, String key) {
        int start = 0;
        int end = key.length();

        for (int i = start; i < end; i++) {
            if (array[i].compareTo(key) == 0) {
                return i;
            }
        }
        return 0;
    }

    private ArrayList<Game> getGameProgress() {
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        return fileIO.readXML(filePath);
    }
}
