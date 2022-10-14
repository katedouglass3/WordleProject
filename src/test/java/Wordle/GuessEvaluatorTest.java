package Wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GuessEvaluatorTest {
    /** An instance of GuessEvaluator */
    private GuessEvaluator guessEvaluator;

    /**
     * Sets guessEvaluator to a new instance of GuessEvaluator before each test
     */
    @BeforeEach
    void setUp() {
        this.guessEvaluator = new GuessEvaluator();
    }

    /**
     * A test that checks that setSecretWord sets the secret word correctly
     */
    @Test
    void setSecretWord() {
        // Test that the secret word is an empty string to start
        assertEquals("", guessEvaluator.getSecretWord());

        // Set the secret word to "crown"
        guessEvaluator.setSecretWord("crown");

        // Test that the secret word is in fact "crown"
        assertEquals("crown", guessEvaluator.getSecretWord());
    }

    /**
     * A test that checks whether analyzeGuess returns the correct string
     */
    @Test
    void analyzeGuess() {
        // Set the secret word to "lemur"
        guessEvaluator.setSecretWord("lemur");

        // Test that analyzeGuess gets the expected string
        assertEquals("***--", guessEvaluator.analyzeGuess("lemon"));

        // Test that analyzeGuess gets the expected string
        assertEquals("+*+--", guessEvaluator.analyzeGuess("melon"));

        // Test that analyzeGuess gets the expected string
        assertEquals("*****", guessEvaluator.analyzeGuess("lemur"));
    }
}