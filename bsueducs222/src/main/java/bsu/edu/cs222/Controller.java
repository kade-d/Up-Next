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

public class Controller extends MainMenu{

    @FXML
    Pane gamePane;

    @FXML
    private Button gauntletModeButton;

    @FXML
    private Button restartGauntletModeButton;

    @FXML
    Label gameName;

    private ArrayList<Game> gauntletProgress = new ArrayList<>();


    public void initialize(){
        setGauntletModeButtonAction();
        setRestartGauntletModeButtonAction();
        updateGauntletProgress();
    }

    private void updateGauntletProgress(){
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        gauntletProgress = fileIO.readXML(filePath);
    }

    private void setGauntletModeButtonAction(){
        gauntletModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateGauntletProgress();
                startGauntletMode();
                hideModeButtons();
            }
        });
    }

    private void setRestartGauntletModeButtonAction(){
        restartGauntletModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGauntletModeFromBeginning();
                hideModeButtons();
            }
        });
    }

    private void hideModeButtons(){
        gauntletModeButton.setVisible(false);
        restartGauntletModeButton.setVisible(false);
    }

    private void startGauntletMode(){
        if(gauntletProgress.size() == 0){
            System.out.println("Size = 0");
            startTicTacToe();
        }
        else{
            startMemoryCard();
        }
    }

    private void startGauntletModeFromBeginning(){
        startTicTacToe();
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
