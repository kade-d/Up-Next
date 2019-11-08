package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class HangmanController  implements Initializable {

    @FXML Button startGame;
    @FXML HBox Buttonlayout;
    @FXML private ImageView hangmanImage;
    @FXML Button Rules;
    @FXML Button Difficulty;
    @FXML Button Hint;
    @FXML Button Music;
    @FXML Button aBut;
    @FXML Button bBut;
    @FXML Button cBut;
    @FXML Button dBut;
    @FXML Button eBut;
    @FXML Button fBut;
    @FXML Button gBut;
    @FXML Button hBut;
    @FXML Button iBut;
    @FXML Button jBut;
    @FXML Button kBut;
    @FXML Button lBut;
    @FXML Button mBut;
    @FXML Button nBut;
    @FXML Button oBut;
    @FXML Button pBut;
    @FXML Button qBut;
    @FXML Button rBut;
    @FXML Button sBut;
    @FXML Button tBut;
    @FXML Button uBut;
    @FXML Button vBut;
    @FXML Button wBut;
    @FXML Button xBut;
    @FXML Button yBut;
    @FXML Button zBut;

    private boolean gameIsNotAlreadyStarted = true;


    public HangmanController() throws IOException {
    }
    public static void main(String [] args){
        launch(args);
    }

    public void startGame(){
        if (gameIsNotAlreadyStarted)
            startGame();
    }
    public void OpenRulesPage(javafx.event.ActionEvent event){

        System.out.println("Welcome to hangman.\\n\"\n" +
                "                + \"Here are the rules to play.\"\n" +
                "                + \"I will pick a word, and you will try to guess it character by character.\"\n" +
                "                + \"If you guess correctly you win.\\n\"\n" +
                "                +\"You can change the difficulty using the right button.\"\n" +
                "                + \"I can go Easy on you since you're new to the game.\"\n" +
                "                +\"I'll give 5 minutes to guess the word\"\n" +
                "                + \", or if you think you got some guts to go go with that experience I dare you to challenge me and press Experienced.\"\n" +
                "                +\"Here you will only be allowed 3 minutes and 30 seconds to guess the word\"\n" +
                "                + \". If you think you're the best press Hard button. Here you will only have 45 second to guess the \"\n" +
                "                +\"You can guess as many times as you want but if you guess wrong 5 times, You lose\"\n" +
                "                + \"if you guess the correct word before then, you are truly better than me\" + \"Users Name\"\n" +
                "                +\" I have picked my word, can you guess it.\"\n" +
                "                + \"Below is a picture, and below that is your current guess, which starts off as nothing.\"\n" +
                "                + \"Everytime, you guess incorrectly, I add a body part to the picture. When it is full\"\n" +
                "                + \"you lose!");
    }
    public void handleButtonClick(){
        System.out.println("sa");
    }
    public void OpenDifficultyMenu(){

    }
    public void showHints(){

    }
    public void ToggleMusic(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SpaceBar(KeyEvent keyEvent) {

    }
}
