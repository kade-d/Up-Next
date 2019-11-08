package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SimonController {
    //Author: Clay Grider
    @FXML
    Button startGame;

    @FXML
    Button nextLevel;

    @FXML
    Button yellowButton;

    @FXML
    Button blueButton;

    @FXML
    Button redButton;

    @FXML
    Button greenButton;

    @FXML
    Label currentLevelLabel;

    @FXML
    Label labelOutput;

    private int level = 0;

    private String answer = "";

    private String question = "";

    private boolean correctLength = false;

    private Controller mainController;

    public void initialize(Controller controller) {
        this.mainController = controller;
    }

    public void clickA(javafx.event.ActionEvent event){
        answer += "A";
        checkLength();
        if (correctLength == true){
            disableOptions();
            if(checkAnswers() == true){
                if(checkCompletion() == false) {
                    nextLevel.setDisable(false);
                } else {  //Winning Condition
                    endSimon();
                }
            }
            else{
                labelOutput.setText("Incorrect\nQuestion was : " + question + "\nAnswer given was: " + answer + "\nPlease start a new game.");
            }
        }
    }

    public void clickB(javafx.event.ActionEvent event){
        answer += "B";
        checkLength();
        if (correctLength == true){
            disableOptions();
            if(checkAnswers() == true){
                if(checkCompletion() == false) {
                    nextLevel.setDisable(false);
                }
                else{//Winning Condition
                    endSimon();
                }
            }
            else{
                labelOutput.setText("Incorrect\nQuestion was : " + question + "\nAnswer given was: " + answer + "\nPlease start a new game.");
            }
        }
    }

    public void clickC(javafx.event.ActionEvent event){
        answer += "C";
        checkLength();
        if (correctLength == true){
            disableOptions();
            if(checkAnswers() == true){
                if(checkCompletion() == false) {
                    nextLevel.setDisable(false);
                }
                else{
                    endSimon();
                }
            }
            else{
                labelOutput.setText("Incorrect\nQuestion was : " + question + "\nAnswer given was: " + answer + "\nPlease start a new game.");
            }
        }
    }

    public void clickD(javafx.event.ActionEvent event){
        answer += "D";
        checkLength();
        if (correctLength == true){
            disableOptions();
            if(checkAnswers() == true){
                if(checkCompletion() == false) {
                    nextLevel.setDisable(false);
                }
                else{
                    endSimon();
                }
            }
            else{
                labelOutput.setText("Incorrect\nQuestion was : " + question + "\nAnswer given was: " + answer + "\nPlease start a new game.");
            }
        }
    }

    private void endSimon() {
        mainController.getStopwatch().stop();
        saveWinToXML();
        refreshScene();
    }

    private boolean checkAnswers(){
        if(answer.equals(question)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkCompletion(){
        if (level < 8){
            return false;
        }
        else{
            return true;
        }
    }

    private void checkLength(){
        if(answer.length() == level){
            correctLength = true;
        }
        else{
            correctLength = false;
        }
    }

    private void disableOptions(){
        yellowButton.setDisable(true);
        blueButton.setDisable(true);
        redButton.setDisable(true);
        greenButton.setDisable(true);
    }

    private void enableOptions(){
        yellowButton.setDisable(false);
        blueButton.setDisable(false);
        redButton.setDisable(false);
        greenButton.setDisable(false);
    }

    private void generateOrder(){
        question = "";
        for(int i=0; i < level; i++){
            int randomInt = (int )(Math.random() * 4 + 1);
            if(randomInt == 1){
                question += "A";
            }
            else if (randomInt == 2){
                question += "B";
            }
            else if (randomInt == 3){
                question += "C";
            }
            else {
                question += "D";
            }
        }
    }

    public void nextLevel(javafx.event.ActionEvent event){
        answer = "";
        enableOptions();
        level = level + 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }

    public void startNewGame(javafx.event.ActionEvent event){
        answer = "";
        enableOptions();
        level = 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }

    private void saveWinToXML(){
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        ArrayList<Game> gameProgress = fileIO.readXML(filePath);
        Game game = new Game("Simon", true);
        gameProgress.add(game);
        fileIO.saveToXML(filePath, gameProgress);
    }

    private void refreshScene(){
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/fxml/MainMenu.fxml"));
        Stage stage = (Stage) nextLevel.getScene().getWindow();
        AnchorPane page = new AnchorPane();
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
    }
}
