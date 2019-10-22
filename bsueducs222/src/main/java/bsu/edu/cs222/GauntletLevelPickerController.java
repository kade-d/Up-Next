package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

public class GauntletLevelPickerController {

    @FXML
    private List<Button> levelButtons;

    void initialize(final Controller mainController) {
        System.out.println("initialized");
        int i = 0;
        for (i = 0; i < levelButtons.size(); i++) {
            final Button button = levelButtons.get(i);
            final int j = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleLevelButtonClicks(button, j, mainController);
                }
            });
        }
    }

    private void handleLevelButtonClicks(Button button, final int buttonIndex, final Controller mainController) {
        switch (buttonIndex) {
            case 0:
                mainController.startTicTacToe();

            case 1:
                mainController.startMemoryCard();
        }
    }
}
