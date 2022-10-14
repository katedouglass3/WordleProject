/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 * Instructor: Prof. Brian King
 *
 * Name: Marion Duval and Kate Douglass
 * Section: 02 - 11:00 am
 * Date: 10/6/2022
 * Time: 8:03 PM
 *
 * Project: csci205_hw
 * Package: Wordle
 * Class: WordDictionary
 *
 * Description: Class file that creates a Set and ArrayList containing five-letter words drawn from five novels.
 *
 * *****************************************
 */

package Wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Class that creates a Set and ArrayList containing five-letter words drawn from five novels.
 */
public class WordDictionary {

    /** A list of urls to be read in for their words */
    public String[] LIST_OF_TEXT_URLS = new String[]{"https://www.gutenberg.org/files/2701/2701-0.txt",
            "https://www.gutenberg.org/files/98/98-0.txt", "https://www.gutenberg.org/files/1342/1342-0.txt",
            "https://www.gutenberg.org/cache/epub/120/pg120.txt", "https://www.gutenberg.org/files/74/74-0.txt"};


    /** A set containing five-letter words to be used in the game */
    private Set<String> wordSet;

    /** A list containing five-letter words to be used in the game */
    private ArrayList<String> wordList;

    /**
     * Constructs a WordDictionary instance by setting the class variables to new sets/lists
     */
    public WordDictionary() {
        this.wordSet = new HashSet<>();
        this.wordList = new ArrayList<>();
    }

    /**
     * Creates a wordSet and wordList from a given file
     *
     * @param fileName - the file to be read in from to create the wordSet and wordList
     */
    public void createWordDictionary(String fileName) {
        try {
            // Instantiate a File and Scanner to read from the file
            File inputFile = new File(fileName);
            Scanner scnr = new Scanner(inputFile);

            // Add words from the file to the wordSet
            while (scnr.hasNext()) {
                String word = scnr.next();
                this.wordSet.add(word);
            }
            // Add all the words from the wordSet into the ArrayList
            addWordsToList();
        }
        // If the file is not found, prints a message
        catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
    }

    /**
     * Generate a file containing the set of five-letter words from the words in urls
     *
     * @throws IOException - thrown if there is an input or output exception
     */
    public void generateNewWordSet() throws IOException {
        // Create an ArrayList of URLs to pass into TextProcessor
        ArrayList<URL> urlList = new ArrayList<>();
        for (String urlStr : LIST_OF_TEXT_URLS){
            URL url = new URL(urlStr);
            urlList.add(url);
        }
        // Write the words from the urls to words.txt file
        TextProcessor textProcessor = new TextProcessor();
        textProcessor.readInNovels(urlList);
        textProcessor.writeListOfWords();
    }

    /**
     * Creates a wordList as an ArrayList from the wordSet
     */
    private void addWordsToList(){
        this.wordList.addAll(this.wordSet);
    }

    /**
     * Returns a random word from the wordList to be used as the secretWord
     *
     * @return - a String for the random word
     */
    public String getRandomWord(){
        // Generate a random number
        Random rndm = new Random();

        // Get a random index from 0 to the size of the list of word
        int rndmIndex = rndm.nextInt(this.wordList.size());

        // Print the secret word to make it easier to test the code
        System.out.println("Secret Word: " + this.wordList.get(rndmIndex));

        // Return the word at the random index
        return this.wordList.get(rndmIndex);
    }

    /**
     * A method that checks if a user's word guess is in the dictionary
     *
     * @return a boolean representing if the user's guess was in the word set
     */
    public boolean checkGuess(String guess) {
        // If the guess is in the wordSet, return true
        return (wordSet.contains(guess));
    }

    /**
     * A getter method that gets the wordSet for testing purposes
     *
     * @return wordSet a set of strings of all the words
     */
    public Set<String> getWordSet(){
        return wordSet;
    }

    /**
     * A getter method gets the wordList for testing purposes
     *
     * @return wordList a list of strings of all the words
     */
    public ArrayList<String> getWordList() {
        return wordList;
    }
}