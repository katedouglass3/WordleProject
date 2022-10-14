/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 * Instructor: Prof. Brian King
 *
 * Name: Marion Duval and Kate Douglass
 * Section: 02 - 11:00 am
 * Date: 10/6/2022
 * Time: 7:48 PM
 *
 * Project: csci205_hw
 * Package: Wordle
 * Class: GuessEvaluator
 *
 * Description: Compares the secretWord to the user's guess and outputs a String representing which
 * letters are right, wrong, or in the wrong place
 *
 * *****************************************
 */

package Wordle;

/**
 * Compares the secretWord to the user's guess and outputs a String representing which letters are
 * right, wrong, or in the word but in the wrong place
 */
public class GuessEvaluator {
    /** The word to be guessed */
    private String secretWord;

    /** The current guess being analyzed */
    private String currentGuess;

    /** The encoded String representing which letters in the guess are right, wrong spot, or wrong */
    private String guessAnalysis;

    /**
     * Creates a new GuessEvaluator instance
     */
    public GuessEvaluator(){
        this.secretWord = "";
        this.currentGuess = "";
        this.guessAnalysis = "";
    }

    /**
     * A method that sets the secret word to be guessed to the given secret word
     *
     * @param secretWord - the word to be guessed
     */
    public void setSecretWord(String secretWord){
        this.secretWord = secretWord;
    }

    /**
     * Getter method for testing setSecretWord method
     *
     * @return - the secret word
     */
    public String getSecretWord(){
        return this.secretWord;
    }

    /**
     * A method that returns an encoded String representing which letters from a guess
     * are in the right place, in the wrong place, or not in the word.
     *
     * @param guess - the user inputted guess
     * @return a String with -, +, and * characters
     */
    public String analyzeGuess(String guess){
        // Set current guess to the given guess
        this.currentGuess = guess;

        // Clear the guessAnalysis
        this.guessAnalysis = "";

        // Loop through the currentGuess, comparing it to the secretWord
        for (int i = 0; i < this.currentGuess.length(); i ++){
            // If the letter is in the right spot, add a star to guessAnalysis
            if (this.currentGuess.charAt(i) == this.secretWord.charAt(i)) {
                this.guessAnalysis += "*";
            }
            // If the letter is in the word, but in the wrong spot, add a + to guessAnalysis
            else if (this.secretWord.indexOf(this.currentGuess.charAt(i)) != -1){

                this.guessAnalysis += "+";
            }
            // Otherwise, the letter is not in the word, add a - to guessAnalysis
            else {
                this.guessAnalysis += "-";
            }

        }
        return this.guessAnalysis;
    }

}