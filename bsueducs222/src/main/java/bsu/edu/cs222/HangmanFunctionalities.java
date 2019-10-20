package bsu.edu.cs222;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class HangmanFunctionalities {
    String wordChosen;
    StringBuilder usersCurrentGuess;
    ArrayList<Character> previousGuesses= new ArrayList<>();
    int maxMissedGuesses=6;
    int currentTry=0;

    ArrayList<String> dictionary=new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader bufferedFileReader;

    public HangmanFunctionalities() throws IOException{
        initializeStreams();
        wordChosen=pickWord();
        usersCurrentGuess=initializeUsersCurrentGuess();
    }
    public void initializeStreams() throws IOException{
        try {
            File inFile = new File("dictionary.txt");
            fileReader = new FileReader(inFile);
            bufferedFileReader = new BufferedReader(fileReader);
            String currentLine = bufferedFileReader.readLine();
            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine= bufferedFileReader.readLine();
            }
            bufferedFileReader.close();
            fileReader.close();
        }catch (IOException e) {
            System.out.println("Could not initialize streams");
        }

        }
        public String pickWord() {
            Random random = new Random();
            int wordIndex = Math.abs(random.nextInt()) % dictionary.size();
            return dictionary.get(wordIndex);
        }
        public StringBuilder initializeUsersCurrentGuess(){
        StringBuilder current= new StringBuilder();
        for (int i=0; i<wordChosen.length() *2; i++){
            if(i % 2 == 0){
                current.append("_");
            } else {
                current.append(" ");
            }
        }
        return current;
    }
    public String getFormalCurrentGuess(){
        return "Current Guess" + usersCurrentGuess.toString();

    }
    public boolean gameOver(){
        if (didWeWin()){
            System.out.println();
            System.out.println("You Win! Great guess"+ "(//user)");
            return true;
        }else if (didWeLose()){
            System.out.println();
            System.out.println(" You reach your maximum chance to guess wrong so therefore, You Lose! "
                    +wordChosen+ "was the Word. Good luck next time!");
            return true;
        }
        return false;
    }
    public boolean didWeWin(){
        String guess= getCondensedCurrentGuess();
        return guess.equals(wordChosen);
    }
    public boolean didWeLose(){
        return currentTry>= maxMissedGuesses;
    }
    public String getCondensedCurrentGuess(){
        String guess=usersCurrentGuess.toString();
        return guess.replace(" ", "");
    }
    public boolean isGuessedAlready(char guess){
        return previousGuesses.contains(guess);
    }
    public boolean playGuess(char guess){
        boolean isTheGuessGood= false;
                previousGuesses.add(guess);
        for (int word=0; word< wordChosen.length(); word++){
            if (wordChosen.charAt(word)== guess){
                usersCurrentGuess.setCharAt(word * 2, guess);
                isTheGuessGood=true;
            }
        }
        if (!isTheGuessGood){
            currentTry++;
        }
        return isTheGuessGood;
    }

    public  String drawPicture(){
         switch (currentTry){
             case 0: return noPersonDrawn();
             case 1: return Head();
             case 2: return HeadandBody();
             case 3: return HeadBodyandFirstArm();
             case 4: return HeadBodyandBothArms();
             case 5: return HeadBodyArmandOneLeg();
             default: return fullBodyDrawn();

         }
    }
    private String noPersonDrawn() {
       return   " - - - - -\n"+
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
}
