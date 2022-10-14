package Wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextProcessorTest {
    /** An instance of TextProcessor */
    TextProcessor textProcessor;

    /**
     * A method that sets this.textProcessor to a new instance of TextProcessor
     *
     * @throws IOException if an invalid URL is entered
     */
    @BeforeEach
    void setUp() throws IOException {
        this.textProcessor = new TextProcessor();
    }

    /**
     * A simple tests that checks whether getSetOfWords works as expected by
     * checking that the size of the set is as expected
     */
    @Test
    void getSetOfWords() {
        // Call getSetOfWords
        textProcessor.getSetOfWords();

        // Check that the size is as expected
        assertEquals(1552,textProcessor.getTotalUniqueWords());
    }

    /**
     * A simple test that checks that words.txt was written to by making
     * sure it exists
     *
     * @throws FileNotFoundException - not thrown
     */
    @Test
    void writeListOfWords() throws FileNotFoundException {
        // Test that words.txt exists after calling generateNewWordSet
        // and therefore, the file has successfully been written to
        textProcessor.writeListOfWords();
        boolean fileExists = new File("C:\\Users\\kate1\\csci205_hw\\src\\main\\java\\Wordle\\words.txt").isFile();
        assertTrue(fileExists);
    }
}