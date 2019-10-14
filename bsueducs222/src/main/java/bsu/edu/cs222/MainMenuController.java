package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button gauntletModeButton;

    public void initialize(){
        setGauntletModeButtonAction();
    }

    public void setGauntletModeButtonAction(){
        gauntletModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }
}
