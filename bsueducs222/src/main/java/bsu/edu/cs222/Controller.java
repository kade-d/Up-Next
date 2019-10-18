package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    @FXML
    private Pane gamePane;

    @FXML
    private Button gauntletModeButton;

    @FXML
    public Button nextGameButton;

    @FXML
    private Label gameName;

    private HashMap<String, Boolean> gauntletProgress = new HashMap<>();


    public void initialize(){
        setGauntletModeButtonAction();
    }

    void addGauntletProgress(String game, Boolean gameCompleted) {
        this.gauntletProgress.put(game, gameCompleted);
    }

    private void setGauntletModeButtonAction(){
        gauntletModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGauntletMode();
            }
        });
    }

    void startGauntletMode(){
        System.out.println(gauntletProgress);
        if(gauntletProgress.isEmpty()){
            startTicTacToe();
        }
        else if (gauntletProgress.get("TicTacToe").equals(true)){
            startMemoryCard();
        }
        else{
            startTicTacToe();
        }
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
        gameName.setText("Tic Tac Toe");
    }

    private void startMemoryCard(){
        nextGameButton.setVisible(true);
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/MemoryMatch.fxml"));
        AnchorPane memoryCardPane = new AnchorPane();

        try{
            memoryCardPane = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        gamePane.getChildren().removeAll();
        gamePane.getChildren().add(memoryCardPane);
        gameName.setText("Memory Match");
    }

}
