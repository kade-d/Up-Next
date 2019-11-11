package bsu.edu.cs222;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

public class SimonController {

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
    Label goalLabel;

    private int level = 0;

    private String answer = "";

    private String question = "";

    private Controller mainController;

    private HashMap<String, Button> buttonHashMap = new HashMap<>();

    void initialize(Controller controller) {
        this.mainController = controller;
        populateHashMap();
        setButtonActions();
        startNewGame();
    }

    private void populateHashMap() {
        buttonHashMap.put("A", yellowButton);
        buttonHashMap.put("B", blueButton);
        buttonHashMap.put("C", redButton);
        buttonHashMap.put("D", greenButton);
    }

    private void setButtonActions() {
        setYellowButtonAction();
        setBlueButtonAction();
        setRedButtonAction();
        setGreenButtonAction();
    }

    private void setYellowButtonAction() {
        yellowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer += "A";
                checkTurn();
            }
        });
    }

    private void setBlueButtonAction() {
        blueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer += "B";
                checkTurn();
            }
        });
    }

    private void setRedButtonAction() {
        redButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer += "C";
                checkTurn();
            }
        });
    }

    private void setGreenButtonAction() {
        greenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer += "D";
                checkTurn();
            }
        });
    }

    private void checkTurn() {
        if (checkAnswers() && checkLevelsCompleted()) {
            endSimon();
        } else if (checkAnswers() && checkAnswerLength()) {
            nextLevel();
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

    private void generateOrder(){
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

    private void playQuestion(){
        disableSimonButtons();
        ArrayList<Button> questionButtons = new ArrayList<>();
        for(char letter: question.toCharArray()){
            Button tempButton = buttonHashMap.get(Character.toString(letter));
            questionButtons.add(tempButton);
        }
        AnimationTimer playQuestionTimer = makeShowButtonsTimer(questionButtons);
        playQuestionTimer.start();
    }

    private void enableSimonButtons() {
        for (String key : buttonHashMap.keySet()) {
            buttonHashMap.get(key).setDisable(false);
        }
    }

    private void disableSimonButtons() {
        for (String key : buttonHashMap.keySet()) {
            buttonHashMap.get(key).setDisable(true);
        }
    }

    private AnimationTimer makeShowButtonsTimer(final ArrayList<Button> questionButtons) {
        return new AnimationTimer() {
            private long timestamp;
            private long fraction = 0;
            private int count = 0;

            @Override
            public void start() {
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                fraction = System.currentTimeMillis() - timestamp;
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 500 <= newTime) {
                    count += 1;
                    if (count % 2 == 1) {
                        disableSimonButtons();
                        long deltaT = (newTime - timestamp) / 500;
                        timestamp += 500 * deltaT;
                    } else {
                        if (count / 2 <= questionButtons.size()) {
                            questionButtons.get((count / 2) - 1).setDisable(false);
                            long deltaT = (newTime - timestamp) / 500;
                            timestamp += 500 * deltaT;
                        } else {
                            enableSimonButtons();
                            super.stop();
                        }
                    }
                }
            }
        };
    }

    private void nextLevel() {
        answer = "";
        level = level + 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        playQuestion();
    }

    private void startNewGame() {
        answer = "";
        question = "";
        level = 1;
        currentLevelLabel.setText("Current Level: " + level);
        generateOrder();
        playQuestion();
    }

    private void restartSimon() {
        mainController.notifyLoss();
        level = 1;
        answer = "";
        question = "";
        currentLevelLabel.setText("");
        generateOrder();
        playQuestion();
    }

    private void endSimon() {
        mainController.notifyWin();
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
