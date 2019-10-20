package bsu.edu.cs222;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Scanner;

public class HangManDetails {
    //rules for how to play the game
    //gameplay that allows user to keep on playing
    public static void main (String[] arg) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to hangman. " + "Here are the rules to play"
                + " I will pick a word and you will try to guess it character by character"
                + "If you guess correctly you win."
                + "I can go Easy on you since you're new to the game"
                + ", or if you think you got some guts to go go with that experience I can challenge me and press Experienced"
                + ". If you think you're the best press Hard button to");
        System.out.println("You can guess as many times as you want but if you guess wrong 5 times, You lose"
                + "if you guess the correct word before then, you are truly better than me" + "Users Name");
        System.out.println(" I have picked my word, can you guess it."
                + "Below is a picture, and below that is your current guess, which starts off as nothing."
                + "Everytime, you guess incorrectly, I add a body part to the picture. When it is full"
                + "you lose!");

        boolean doYouWantToPlay = true;
        while (doYouWantToPlay) {
            //sets up the game
            System.out.println("Start Game");
            Hangman game = new Hangman();
            do {
                //draw the things..
                System.out.println();
                System.out.println(game.drawPicture());
                System.out.println();
                System.out.println(game.getFormalCurrentGuess());
                System.out.println(game.wordChosen);

                System.out.println("Guess a letter in the word");
                char guess = (scanner.next().toLowerCase()).charAt(0);
                System.out.println();

                while (game.isGuessedAlready(guess)) {
                    System.out.println("Try again! You've already guessed that letter.");
                    guess = (scanner.next().toLowerCase()).charAt(0);
                }
                if (game.playGuess(guess)) {
                    System.out.println("Nice guess, you got one more in it.");
                } else {
                    System.out.println("Nope that is wrong.");
                }
            }
            while (!game.gameOver());
            {
                //Button to click to replay the game. will do for GUI

                //play again or not local
                System.out.println("Do you want to play again? Click the yes button");
                Character response = (scanner.next().toUpperCase()).charAt(0);
                doYouWantToPlay = (response == 'Y');
            }
        }
    }
}

