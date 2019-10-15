package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    GridPane ticTacToe;

    public void startTicTacToe(){
        try {
            Parent gameWindow = FXMLLoader.load(getClass().getResource("/fxml/TicTacToe.fxml"));
            ticTacToe.getChildren().setAll(gameWindow);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
