package bsu.edu.cs222.Games.Hangman;

import bsu.edu.cs222.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class HangmanController{

    @FXML
    Button startGame;

    @FXML
    TextArea HangmanImage;
    @FXML
    Button Rules;
    @FXML
    Button Difficulty;
    @FXML
    RadioButton easy;
    @FXML
    RadioButton medium;
    @FXML
    RadioButton hard;
    @FXML
    Button Hint;
    @FXML
    Button Music;
    @FXML
    Button aBut;
    @FXML
    Button bBut;
    @FXML
    Button cBut;
    @FXML
    Button dBut;
    @FXML
    Button eBut;
    @FXML
    Button fBut;
    @FXML
    Button gBut;
    @FXML
    Button hBut;
    @FXML
    Button iBut;
    @FXML
    Button jBut;
    @FXML
    Button kBut;
    @FXML
    Button lBut;
    @FXML
    Button mBut;
    @FXML
    Button nBut;
    @FXML
    Button oBut;
    @FXML
    Button pBut;
    @FXML
    Button qBut;
    @FXML
    Button rBut;
    @FXML
    Button sBut;
    @FXML
    Button tBut;
    @FXML
    Button uBut;
    @FXML
    Button vBut;
    @FXML
    Button wBut;
    @FXML
    Button xBut;
    @FXML
    Button yBut;
    @FXML
    Button zBut;

    private String wordChosen;
    private StringBuilder usersCurrentGuess;
    private ArrayList<Character> previousGuesses = new ArrayList<>();
    private int currentTry = 0;
    private ArrayList<String> dictionary = new ArrayList<>();

    private boolean gameIsNotAlreadyStarted = true;
    private HangmanController game = new HangmanController();
    private Controller mainController;
    public void initialize(Controller controller) {
        this.mainController = controller;
        startGame();
    }
    public void startGame() {
        while (gameIsNotAlreadyStarted)
            startGame();
        while (!game.gameOver()) {
            HangmanImage.setText("If You Want to Play again, Click start");
        }

    }

    private HangmanController() throws IOException {
        initializeStreams();
        wordChosen = pickWord();
        usersCurrentGuess = initializeUsersCurrentGuess();
    }

    private void initializeStreams() {
        try {
            File inFile = null;
            if(easy.isSelected()){ inFile = new File("easydictionary.txt"); }
            else if (medium.isSelected()){inFile = new File("mediumdictionary.txt");}
            else{ inFile = new File("dictionary.txt");}
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedFileReader = new BufferedReader(fileReader);
            String currentLine = bufferedFileReader.readLine();
            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine = bufferedFileReader.readLine();
            }
            bufferedFileReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Could not initialize streams");
        }

    }

    private String pickWord() {
        Random random = new Random();
        int wordIndex = Math.abs(random.nextInt()) % dictionary.size();
        return dictionary.get(wordIndex);
    }

    private String getFormalCurrentGuess() {
        return "Current Guess" + usersCurrentGuess.toString();

    }

    private StringBuilder initializeUsersCurrentGuess() {
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < wordChosen.length() * 2; i++) {
            if (i % 2 == 0) {
                current.append("_");
            } else {
                current.append(" ");
            }
        }
        return current;
    }

    public boolean handleButtonClick() {
        char guess = ' ';
        boolean correctGuess;
        //if buttonoption get button and print texxt
        if (aBut.isPressed()) {
            HangmanImage.setText(String.valueOf(guess));
        }
        if (bBut.isPressed()) {
            HangmanImage.setText(String.valueOf(guess));
        }
        if (cBut.isPressed()) {
            HangmanImage.setText(String.valueOf(guess));
        }
        if (dBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (fBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (eBut.isPressed()) {
            HangmanImage.setText(String.valueOf(guess));
        }
        if (gBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (hBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if(kBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (lBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (mBut.isPressed()) {
            HangmanImage.setText(String.valueOf(guess));
        }
        if (nBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (oBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (pBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (qBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (rBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (sBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (tBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (uBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (vBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (wBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (xBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (yBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (zBut.isPressed()){
            HangmanImage.setText(String.valueOf(guess));
        }
        if (Rules.isPressed()) {
            OpenRulesPage();
        }
        if (playGuess(guess)) {
            HangmanImage.setText("Nice guess, you got one more in it.");
            correctGuess = true;
        } else {
            HangmanImage.setText("Nope that is wrong.");
            correctGuess = false;
        }
        return correctGuess;
    }

    private boolean playGuess(char guess) {
        boolean isTheGuessGood = false;
        previousGuesses.add(guess);
        for (int word = 0; word < wordChosen.length(); word++) {
            if (wordChosen.charAt(word) == guess) {
                usersCurrentGuess.setCharAt(word * 2, guess);
                isTheGuessGood = true;
            } else{
                DisplayHangmanImage();
            }
        }
        if(isGuessedAlready(guess)){
            HangmanImage.setText("Try again! You've already guessed that letter.");
        }
        if (!isTheGuessGood) {
            currentTry++;
        }
        return isTheGuessGood;
    }

    private boolean isGuessedAlready(char guess){
        return previousGuesses.contains(guess);
    }
    private String hangmanImage() {
        switch (currentTry) {
            case 0:
                return noPersonDrawn();
            case 1:
                return Head();
            case 2:
                return HeadandBody();
            case 3:
                return HeadBodyandFirstArm();
            case 4:
                return HeadBodyandBothArms();
            case 5:
                return HeadBodyArmandOneLeg();
            default:
                return fullBodyDrawn();

        }
    }
    private String noPersonDrawn() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|       \n" +
                "|       \n"+
                "|       \n" +
                "|\n" +
                "|\n";
    }
    private String Head() {
        return  " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      \n"+
                "|      \n" +
                "|\n" +
                "|\n";
    }
    private String HeadandBody() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|        |  \n"+
                "|        \n" +
                "|\n" +
                "|\n";
    }
    private String HeadBodyandFirstArm() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \n"+
                "|      \n" +
                "|\n" +
                "|\n";
    }
    private String HeadBodyandBothArms() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|     \n" +
                "|\n" +
                "|\n";
    }
    private String HeadBodyArmandOneLeg() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|       /  \n" +
                "|\n" +
                "|\n";
    }
    private String fullBodyDrawn() {
        return   " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }
    private void DisplayHangmanImage() {
            if (gameIsNotAlreadyStarted) {
                startGame();
            }
            HangmanImage.setText(game.hangmanImage());
            HangmanImage.setText(game.getFormalCurrentGuess());
            HangmanImage.setText(game.wordChosen);
    }
    private boolean gameOver(){
        if (didWeWin()){
            System.out.println();
            HangmanImage.setText("You Win! Great guess");
            mainController.notifyWin();
            mainController.startMaze(0);
            return true;
        }else if (didWeLose()){
            System.out.println();
            HangmanImage.setText(" You reach your maximum chance to guess wrong so therefore, You Lose! "
                    +wordChosen+ "was the Word. Good luck next time!");
            return true;
        }
        return false;
    }
    private boolean didWeWin(){
        String guess= getCondensedCurrentGuess();
        return guess.equals(wordChosen);

    }
    private boolean didWeLose(){
        int maxMissedGuesses = 6;
        return currentTry>= maxMissedGuesses;
    }
    private String getCondensedCurrentGuess(){
        String guess=usersCurrentGuess.toString();
        return guess.replace(" ", "");
    }
    public void OpenRulesPage(){
        HangmanImage.setText("Welcome to hangman.\\n\"\n" +
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


    public void OpenDifficultyMenu(){
        if (Difficulty.isPressed()){
            //create a choicebox, 3 items within choicebox and they hold a value and whatever that value determine those statements
            //dropdown the radio buttons
        }
    }
    public void showHints(){
        HangmanImage.setText("Guess a letter in the word");
        if (Hint.isPressed()){
            for (int word = 0; word < wordChosen.length(); word++) {
                HangmanImage.setText(String.valueOf(wordChosen.charAt(word)));
            }
        }
    }
    public void ToggleMusic() {
        while (gameIsNotAlreadyStarted) {
            startGame();
            if (Music.isPressed())
                playMusic();
        }
    }
    private void playMusic() {

    }
}

