package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller extends MainMenu {

    @FXML
    Pane gamePane;

    @FXML
    Label gameName;

    @FXML
    private GauntletLevelPickerController levelPickerController; //appends "Controller" to id of included fxml file


    public void initialize(){
        levelPickerController.initialize(this); //Class is GauntletLevelPickerController
    }

    void startTicTacToe() {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/TicTacToe.fxml"));

        AnchorPane ticTacToePane = new AnchorPane();

        try{
            ticTacToePane = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        gamePane.getChildren().clear();
        gamePane.getChildren().add(ticTacToePane);
        gameName.setText("Tic Tac Toe");
    }

    void startMemoryCard() {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/MemoryMatch.fxml"));
        AnchorPane memoryCardPane = new AnchorPane();

        try{
            memoryCardPane = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        gamePane.getChildren().clear();
        gamePane.getChildren().add(memoryCardPane);
        gameName.setText("Memory Match");
    }

    void startSimon() {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/Simon.fxml"));
        AnchorPane simonPane = new AnchorPane();

        try{
            simonPane = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        gamePane.getChildren().clear();
        gamePane.getChildren().add(simonPane);
        gameName.setText("Simon");
    }
}
