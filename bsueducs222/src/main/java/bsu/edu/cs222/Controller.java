package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller {

    @FXML
    private Pane gamePane;

    @FXML
    private Button gauntletModeButton;


    public void initialize(){
        setGauntletModeButtonAction();
    }

    private void setGauntletModeButtonAction(){
        gauntletModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTicTacToe();
            }
        });
    }

    private void startTicTacToe(){
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/TicTacToe.fxml"));

        AnchorPane ticTacToePane = new AnchorPane();

        try{
            ticTacToePane = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        gamePane.getChildren().add(ticTacToePane);
    }

}
