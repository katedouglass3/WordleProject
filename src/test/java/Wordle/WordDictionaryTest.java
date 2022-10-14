package Wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordDictionaryTest {
    /** An instance of WordDictionary */
    WordDictionary wordDictionary;

    /**
     * A method that sets wordDictionary to a new instance of WordDictionary,
     * then calls createWordDictionary with a small test file
     */
    @BeforeEach
    void setUp() {
        this.wordDictionary = new WordDictionary();
        wordDictionary.createWordDictionary("C:\\Users\\kate1\\csci205_hw\\src\\test\\java\\Wordle\\testWords.txt");
    }

    /**
     * A simple test that checks whether the wordSet created and the wordList created
     * (called in createWordDictionary in setUp) have the correct size
     */
    @Test
    void createWordDictionary() {
        // Test that the size of the created set is as expected
        assertEquals(5,wordDictionary.getWordSet().size());

        // Test that the size of the created list is as expected
        assertEquals(5,wordDictionary.getWordList().size());
    }

    /**
     * A simple test that checks that generateNewWordSet successfully created/overrode the existing file
     */
    @Test
    void generateNewWordSet() throws IOException {
        // Test that words.txt exists after calling generateNewWordSet
        wordDictionary.generateNewWordSet();
        boolean fileExists = new File("C:\\Users\\kate1\\csci205_hw\\src\\main\\java\\Wordle\\words.txt").isFile();
        assertTrue(fileExists);
    }

    @Test
    void checkGuess() {
        // Test that a word that isn't in the wordSet returns false
        assertFalse(wordDictionary.checkGuess("hat"));

        // Test that a word that is in the wordSet returns true
        assertTrue(wordDictionary.checkGuess("would"));
    }
}