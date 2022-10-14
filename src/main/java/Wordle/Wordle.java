/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 * Instructor: Prof. Brian King
 *
 * Name: Marion Duval and Kate Douglass
 * Section: 02 - 11:00 am
 * Date: 10/6/2022
 * Time: 7:35 PM
 *
 * Project: csci205_hw
 * Package: Wordle
 * Class: Wordle
 *
 * Description:
 * A Wordle game that reads in five-letter words from a dictionary and allows the user
 * to guess the word by revealing which letter of their guess are right and in the right
 * or wrong spot.
 *
 * *****************************************
 */

package Wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// TODO: Junit testing
// TODO: check that using the filepath like this can be accessed by them when they check the code?
/**
 * Instantiate the GameState enumeration states
 */
enum GameState{
    NEW_GAME,
    GAME_IN_PROGRESS,
    GAME_WINNER,
    GAME_LOSER
}

/**
 * A Wordle game that reads in five-letter words from a file and allows the user
 * to guess the word by revealing which letters of their guess are in the word and
 * whether the letters are in the right or wrong spot.
 */
public class Wordle {

    /** The number of guesses taken */
    private int guessNumber;

    /** The word to be guessed */
    private String secretWord;

    /** The most recent guess by the user */
    private String lastGuess;

    /** An instance of the GuessEvaluator class */
    private GuessEvaluator guessEvaluator;

    /** An instance of the enum state GameState */
    private GameState gameState;

    /** An instance of WordDictionary class */
    private WordDictionary wordDictionary;

    /**
     * Constructor that creates a Wordle game.
     *
     * @throws FileNotFoundException - thrown if the given file is not found
     */
    public Wordle() {
        this.wordDictionary = new WordDictionary();
        // Create the word set to be used
        this.wordDictionary.createWordDictionary("C:\\Users\\kate1\\csci205_hw\\src\\main\\java\\Wordle\\words.txt");
        // Pick a random word from the defined set and set it to secret word
        this.secretWord = wordDictionary.getRandomWord();

        this.guessNumber = 1;
        this.lastGuess = "";
        this.guessEvaluator = new GuessEvaluator();
        this.guessEvaluator.setSecretWord(this.secretWord);
        this.gameState = GameState.NEW_GAME;
    }

    /**
     * Allows the user to start a new game, make guesses when the game is in progress,
     * and report the winning and losing status
     *
     * @param args - the command line arguments
     * @throws IOException - thrown if there is an input or output exception
     *                       or thrown if the given file is not found
     */
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");

        // If there is a current words.txt file, give the option to use it or create a new one
        // If there is no current words.txt file, create a new work bank
        decideOldOrNewWordBank(scnr);

        // Instantiate a boolean representing whether the user wants to start a new game
        boolean playAgain = true;

        // Loop through while the user wants to play again
        while (playAgain) {
            // Instantiate a new game
            System.out.println("Ready to play Wordle! You have 6 guesses.");
            Wordle wordleGame = new Wordle();
            wordleGame.gameState = GameState.GAME_IN_PROGRESS;

            // While the game is not over yet, let the player play their next turn
            while (wordleGame.gameState == GameState.GAME_IN_PROGRESS) {
                wordleGame.playNextTurn();
            }
            System.out.println();

            // Ask the user if they want to play again and record response
            System.out.print("Would you like to play again? [Y/N]: ");
            String decision = scnr.next();

            // If the user does not input yes, they don't want to play again, set the boolean to false
            if (!decision.equals("Y") && !decision.equals("y")){
                playAgain = false;
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * If there is a current words.txt file, give the option to use it or create a new one and
     * if there is no current words.txt file, create a new work bank
     *
     * @param scnr - a Scanner object to read input from user
     * @throws IOException - thrown if there is an input or output exception
     */
    private static void decideOldOrNewWordBank(Scanner scnr) throws IOException {
        // Check if there is a words.txt file to use as the word bank
        boolean fileExists = new File("C:\\Users\\kate1\\csci205_hw\\src\\main\\java\\Wordle\\words.txt").isFile();

        // Check if the words.txt file exists
        if (fileExists) {
            // Ask the user if they want to use words.txt or generate a new word bank and get the response
            System.out.println("Use existing words.txt or generate a new word bank?");
            System.out.print("Enter \"W\" to use words.txt or \"N\" to use a new word bank: ");
            String userChoice = scnr.next();

            // If the user wants to generate a new word bank
            if (!(userChoice.equals("W") || userChoice.equals("w"))){
                System.out.println("Generating a new set of words.");
                System.out.println("Reading master word set from Webster's Unabridged Dictionary");

                // Create a new WordDictionary to generate the new word set
                WordDictionary wordDictionary1 = new WordDictionary();
                wordDictionary1.generateNewWordSet();

                System.out.println("Finding common words from novels: ");
            }
        }
        // If the file does not exist
        else{
            System.out.println("words.txt: NOT FOUND! Generating a new set of words.");
            System.out.println("Reading master word set from Webster's Unabridged Dictionary");
            System.out.println("Finding common words from novels: ");

            // Create a new WordDictionary to generate the new word set
            WordDictionary wordDictionary1 = new WordDictionary();
            wordDictionary1.generateNewWordSet();
        }
    }

    /**
     * User guesses a word, it is compared to secretWord, and result is outputted.
     */
    public void playNextTurn(){
        // Instantiate Scanner to read user input
        Scanner scnr = new Scanner(System.in);

        // Instantiate a boolean to check validity of guess
        boolean validGuess = false;

        while(!validGuess){
            // Get the user's guess and make it lowercase
            System.out.print("Guess " + this.guessNumber + ": ");
            this.lastGuess = scnr.next();
            this.lastGuess = this.lastGuess.toLowerCase();

            // If the word is contained in the dictionary, make validGuess true
            if (wordDictionary.checkGuess(lastGuess)) {
                validGuess = true;
            }
            // If the word is not contained in the dictionary, print an error message
            else{
                System.out.println("Word not contained in our dictionary. Try again");
            }
        }

        // Print the character result for the letters guessed (*+-) and print turn message
        printTurnResult();

        // Increment guess number
        this.guessNumber ++;
    }

    /**
     * Prints the character result for the letters guessed (*+-) and prints the turn message
     * for if the user won, lost, or how many turns they have left
     */
    private void printTurnResult() {
        // Get the resulting string to print
        String result = this.guessEvaluator.analyzeGuess(this.lastGuess);

        // If the game is over and the user won, change the state to GAME_WINNER and print a congratulatory message
        if (isGameOver() && this.guessNumber != 6) {
            this.gameState = GameState.GAME_WINNER;
            System.out.println("  -->    " + result + "    YOU WON! You guessed the word in " + this.guessNumber + " guesses!");
        }
        // If the game is over and the user lost, change the state to GAME_LOSER and print a loser message
        else if (isGameOver() && this.guessNumber == 6){
            this.gameState = GameState.GAME_LOSER;
            System.out.println("  -->    " + result + "    YOU LOST :( The word was " + this.secretWord + ".");
        }
        // If the game is not over, print the result and the number of guesses remaining
        else{
            this.gameState = GameState.GAME_IN_PROGRESS;
            System.out.println("  -->    " + result + "    Try again. " + (6 - this.guessNumber) + " guesses left.");
        }
    }

    /**
     * A method that checks if the game has been won or lost
     *
     * @return a boolean representing whether the user correctly guessed the word
     */
    public boolean isGameOver(){
        // Game is over if the word is guessed correctly, or you ran out of guesses
        return (this.lastGuess.equals(this.secretWord) || this.guessNumber == 6);
    }

    public int getGuessNumber() {
        return this.guessNumber;
    }

}