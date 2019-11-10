package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private Controller mainController;

    void initialize(Controller controller) {
        this.mainController = controller;
    }

    public void clickA(){
        answer += "A";
        checkTurn();
    }

    public void clickB(){
        answer += "B";
        checkTurn();
    }

    public void clickC(){
        answer += "C";
        checkTurn();
    }

    public void clickD(){
        answer += "D";
        checkTurn();
    }

    private void checkTurn() {
        if (checkAnswers() && checkLevelsCompleted()) {
            endSimon();
        } else if (checkAnswers() && checkAnswerLength()) {
            nextLevel.setDisable(false);
        } else if (checkAnswerLength()) {
            restartSimon();
        }
    }

    private boolean checkAnswerLength() {
        return answer.length() == level;
    }

    private boolean checkAnswers(){
        return answer.equals(question);
    }

    private boolean checkLevelsCompleted() {
        return level == 6;
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
                question = question.concat("A");
            }
            else if (randomInt == 2){
                question = question.concat("B");
            }
            else if (randomInt == 3){
                question = question.concat("C");
            }
            else {
                question = question.concat("D");
            }
        }
    }

    public void nextLevel(){
        answer = "";
        enableOptions();
        level = level + 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }

    public void startNewGame(){
        mainController.restartStopwatch();
        answer = "";
        enableOptions();
        level = 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        labelOutput.setText("Question: " + question);
        nextLevel.setDisable(true);
    }

    private void restartSimon() {
        mainController.notifyLoss();
        level = 0;
        answer = "";
        question = "";
        currentLevelLabel.setText("");
    }

    private void endSimon() {
        mainController.notifyWin();
        mainController.getStopwatch().stop();
        saveWinToXML();
        mainController.startMinesweeper();
    }

    private void saveWinToXML(){
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        ArrayList<Game> gameProgress = fileIO.readXML(filePath);
        Game game = new Game("Simon", true);
        gameProgress.add(game);
        fileIO.saveToXML(filePath, gameProgress);
    }
}
