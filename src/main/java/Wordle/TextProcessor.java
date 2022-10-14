/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 * Instructor: Prof. Brian King
 *
 * Name: Marion Duval and Kate Douglass
 * Section: 02 - 11:00 am
 * Date: 10/6/2022
 * Time: 8:38 PM
 *
 * Project: csci205_hw
 * Package: Wordle
 * Class: TextProcessor
 *
 * Description: Reads through the text from a URL and picks out the valid 5-letter words from it
 *
 * *****************************************
 */

package Wordle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads through the text from a URL and processes it by picking out the common, valid 5-letter words from it.
 * Prints a report of the results.
 */
public class TextProcessor {

    /** A Map object storing words and their frequencies? */
    private Map<String, Integer> wordMap;

    /** The HashSet of all the 5-letter words in the dictionary */
    private HashSet<String> dictionarySet;

    /**
     * Constructs a new TextProcessor by
     */
    public TextProcessor() throws IOException {
        // Initialize the wordMap and dictionarySet
        this.wordMap = new HashMap<>();
        this.dictionarySet = processDictionary();
    }

    /**
     * Reads in the novels, checks the words, gets all the valid common words, and prints the report
     * of the number of words
     *
     * @param urlList - a list of URLs to be read in
     * @throws IOException - thrown if an input or output exception occurs
     */
    public void readInNovels(ArrayList<URL> urlList) throws IOException {
        // Go through list of urls for the novels
        for (int i = 0; i < urlList.size(); i++){
            // Print out which novel is being read in
            if (i == 0){
                System.out.print(" - Reading in Moby-Dick by Herman Melville......");
            }
            else if (i == 1){
                System.out.print(" - Reading in A Tale of Two Cities by Charles Dickens......");
            }
            else if (i == 2){
                System.out.print(" - Reading in Pride and Prejudice by Jane Austen......");
            }
            else if (i == 3){
                System.out.print(" - Reading in Treasure Island by Robert Louis Stevenson......");
            }
            else{
                System.out.print(" - Reading in The Adventures of Tom Sawyer by Mark Twain......");
            }

            // Read in the text from the url and adds valid words to wordMap
            processTextAtURL(urlList.get(i));
            System.out.println("done");
        }
        // Print the number of unique five-letter words to be used for the game
        printReport();
    }

    /**
     * Reads in the words from a url, checks that it is a valid 5-letter word,
     * and adds it to a wordMap or increment the value for that word
     *
     * @param url - URL to be processed
     * @throws IOException - thrown if there is an input or output exception
     */
    private void processTextAtURL(URL url) throws IOException {
        // Instantiate a scanner to read in the text from the url
        Scanner scnr = new Scanner(url.openStream());

        while (scnr.hasNext()){
            String word = scnr.next();

            // Strip the word of any end/beginning punctuation
            word = word.replaceAll("[\",.!?]","");

            // Checks if the word has only lower case letters (no punctuation), and is 5 letters long
            if (isWordValid(word)){
                // Check that the word is in the dictionary
                if (this.dictionarySet.contains(word)) {
                    // If the key is already in the map, increment the value
                    if (this.wordMap.containsKey(word)) {
                        this.wordMap.put(word, this.wordMap.get(word) + 1);
                    }
                    // Add the word as a key to the map with value 1
                    else {
                        this.wordMap.put(word, 1);
                    }
                }
            }

        }

    }

    /**
     * A method that processes all the words in a dictionary and builds a set of
     * all the 5-letter words.
     *
     * @return a dictionarySet containing all 5-letter words in the dictionary
     * @throws IOException - thrown if there is a malformed/invalid URL
     */
    private HashSet<String> processDictionary() throws IOException {
        // Instantiate a URL for the dictionary URL
        URL urlDic = new URL ("https://www.gutenberg.org/cache/epub/29765/pg29765.txt");
        // Instantiate a scanner to read in the text from the url
        Scanner scnr = new Scanner(urlDic.openStream());
        // Instantiate a new HashSet to store the words in the dictionary
        this.dictionarySet = new HashSet<>();

        while (scnr.hasNext()) {
            String word = scnr.next();
            // Check that the word is exactly 5 letters, and if so, add it to the dictionary set
            Pattern p = Pattern.compile("^[a-z]{5}$");
            Matcher m = p.matcher(word);
            if (m.matches()){
                dictionarySet.add(word);
            }
        }
        return dictionarySet;
    }

    /**
     * Checks if the word is a five-letter word with no punctuation
     *
     * @param word - word to be checked
     * @return a boolean representing if the word is five letters long
     */
    private boolean isWordValid(String word){
        // Make sure it only has lower case letters (no punctuation), and is 5 letters long
        Pattern p = Pattern.compile("^[a-z]{5}$");
        Matcher m = p.matcher(word);
        return (m.matches());
    }

    /**
     * Getter method that returns the integer representing the number of words used
     *
     * @return - the number of unique words used in the game
     */
    private int getTotalUniqueWords() {
        return getSetOfWords().size();
    }

    /**
     * Report how many words are to be used in the game
     */
    public void printReport(){
        System.out.println("Keeping " + getTotalUniqueWords() + " valid words for the game..." );
    }

    /**
     * A getter method that iterates through the wordMap and generates a set
     * of all the common words found
     *
     * @return a Set of five-letter words to be used for the game
     */
    public Set<String> getSetOfWords(){
        // Instantiate a new HashSet
        Set<String> finalSet = new HashSet<>();
        // For every word in the HashSet, if it occurs more than once add it to the finalSet of words
        for (String key : this.wordMap.keySet()) {
            if (this.wordMap.get(key) > 1){
                finalSet.add(key);
            }
        }
        return finalSet;
    }

    /**
     * Writes the words to the words.txt file
     *
     * @throws FileNotFoundException - if the file at the filepath is not found
     */
    public void writeListOfWords() throws FileNotFoundException {
        // Instantiate a new PrintWriter object
        PrintWriter out = new PrintWriter("C:\\Users\\kate1\\csci205_hw\\src\\main\\java\\Wordle\\words.txt");

        // Print the words in finalSet to the PrintWriter object file
        Set<String> finalSet = getSetOfWords();
        for (String word : finalSet) {
            out.println(word);
        }
        out.close();
    }
}