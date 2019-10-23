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

public class Controller extends MainMenu {

    @FXML
    Pane gamePane;

    @FXML
    private Button gauntletModeButton;

    @FXML
    private Button restartGauntletModeButton;

    @FXML
    Label gameName;

    @FXML
    private GauntletLevelPickerController levelPickerController; //appends "Controller" to id of included fxml file

    private ArrayList<Game> gauntletProgress = new ArrayList<>();


    public void initialize(){
        levelPickerController.initialize(this);
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
        int levelsCompleted = 0;
            if(gauntletProgress.size() == 0){
                System.out.println("Tic Tac Toe not completed");
                startTicTacToe();
            }
            if(gauntletProgress.size() > 0){
                if(gauntletProgress.get(0).getGameCompleted()){
                    System.out.println("Tic Tac Toe completed");
                    levelsCompleted += 1;
                }
                else{
                    startMemoryCard();
                }
            }
        }

    private void startGauntletModeFromBeginning(){
        startTicTacToe();
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
