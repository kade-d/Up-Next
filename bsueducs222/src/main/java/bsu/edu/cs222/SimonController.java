package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class SimonController {
    //Author: Clay Grider
    @FXML
    Button startGame;

    @FXML
    Button nextGame;

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

    private Simon game;

    private int level = 0;

    private String answer = "";

    private String question = "";

    private boolean correctLength = false;

    public void clickA(javafx.event.ActionEvent event){
        answer += "A";
        checkLength();
        if (correctLength == true){
            disableOptions();
            if(checkAnswers() == true){
                if(checkCompletion() == false) {
                    nextLevel.setDisable(false);
                }
                else{
                    nextGame.setDisable(false);
                }            }
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
                else{
                    nextGame.setDisable(false);
                }            }
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
                    nextGame.setDisable(false);
                }            }
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
                    nextGame.setDisable(false);
                }
            }
            else{
                labelOutput.setText("Incorrect\nQuestion was : " + question + "\nAnswer given was: " + answer + "\nPlease start a new game.");
            }
        }
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



    public void nextGameLoad(javafx.event.ActionEvent event){
    }

    public void nextLevel(javafx.event.ActionEvent event){
        answer = "";
        enableOptions();
        level = level++;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }

    public void saveWinToXML(){
    }

    public void startNewGame(javafx.event.ActionEvent event){
        answer = "";
        enableOptions();
        game = new Simon();
        level = 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }
}
