package Wordle;

import Wordle.TextProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test class that tests TextProcessor
 */
class TextProcessorTest {
    /** An instance of TextProcessor */
    TextProcessor textProcessor;

    /** An instance of URL list */
    ArrayList<URL> urlList;

    /** A list of urls to be read in for their words */
    public String[] LIST_OF_TEXT_URLS = new String[]{"https://www.gutenberg.org/files/2701/2701-0.txt",
            "https://www.gutenberg.org/files/98/98-0.txt", "https://www.gutenberg.org/files/1342/1342-0.txt",
            "https://www.gutenberg.org/cache/epub/120/pg120.txt", "https://www.gutenberg.org/files/74/74-0.txt"};

    /**
     * A method that sets this.textProcessor to a new instance of TextProcessor
     *
     * @throws IOException if an invalid URL is entered
     */
    @BeforeEach
    void setUp() throws IOException {
        this.textProcessor = new TextProcessor();
        // Create an ArrayList of URLs to pass into TextProcessor
        this.urlList= new ArrayList<>();
        for (String urlStr : LIST_OF_TEXT_URLS){
            URL url = new URL(urlStr);
            urlList.add(url);
        }

        textProcessor.readInNovels(urlList);
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