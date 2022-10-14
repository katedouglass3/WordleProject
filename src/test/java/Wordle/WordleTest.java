package Wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {
    /** Instance of Wordle */
    Wordle wordle;

    /**
     * A method that sets this.wordle to a new instance of Wordle before each test
     */
    @BeforeEach
    void setUp() {
        this.wordle = new Wordle();
    }

    /**
     * A simple test that checks if the guess number correctly implements after each guess
     * and therefore playNextTurn works as expected
     *
     * (Can't tell if this test works or is correct because we can't run our JUnit tests)
     */
    @Test
    void playNextTurn() {
        // Test that the guess number increments after playNextTurn
        // (therefore the next Turn is successfully played)
        wordle.playNextTurn();
        assertEquals(1, wordle.getGuessNumber());

        // Test that the guess number increments after playNextTurn
        // (therefore the next Turn is successfully played)
        wordle.playNextTurn();
        assertEquals(2, wordle.getGuessNumber());
    }

    /**
     * A simple test that checks if isGameOver returns the correct boolean after a various number
     * of turns
     *
     * (Can't tell if this test works or is correct because we can't run our JUnit tests)
     */
    @Test
    void isGameOver() {
        // Test that the game is not over after 1 turn
        wordle.playNextTurn();
        assertFalse(wordle.isGameOver());

        // Test that the game is not over after 2 turns
        wordle.playNextTurn();
        assertFalse(wordle.isGameOver());

        // Test that the game is not over after 3 turns
        wordle.playNextTurn();
        assertFalse(wordle.isGameOver());

        // Test that the game is not over after 4 turns
        wordle.playNextTurn();
        assertFalse(wordle.isGameOver());

        // Test that the game is not over after 5 turns
        wordle.playNextTurn();
        assertFalse(wordle.isGameOver());

        // Test that the game is over after 6 turns
        wordle.playNextTurn();
        assertTrue(wordle.isGameOver());
    }
}