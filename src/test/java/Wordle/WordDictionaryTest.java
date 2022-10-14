package Wordle;

import Wordle.WordDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the WordDictionary class
 */
class WordDictionaryTest {
    /** An instance of WordDictionary */
    WordDictionary wordDictionary;

    /** An instance of URL list */
    ArrayList<URL> urlList;

    /** A list of urls to be read in for their words */
    public String[] LIST_OF_TEXT_URLS = new String[]{"https://www.gutenberg.org/files/2701/2701-0.txt",
            "https://www.gutenberg.org/files/98/98-0.txt", "https://www.gutenberg.org/files/1342/1342-0.txt",
            "https://www.gutenberg.org/cache/epub/120/pg120.txt", "https://www.gutenberg.org/files/74/74-0.txt"};

    /**
     * A method that sets wordDictionary to a new instance of WordDictionary,
     * then calls createWordDictionary with a small test file
     *
     * @throws MalformedURLException - not thrown
     */
    @BeforeEach
    void setUp() throws MalformedURLException {
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

    /**
     * A simple method that checks that a word not in the testWords.txt returns false
     * and a word in testWords.txtx returns true
     *
     * @throws IOException - not thrown
     */
    @Test
    void checkGuess() throws IOException {
        // Test that a word that isn't in the wordSet returns false
        assertFalse(wordDictionary.checkGuess("hat"));

        // Test that a word that is in the wordSet returns true
        assertTrue(wordDictionary.checkGuess("ghost"));
    }
}