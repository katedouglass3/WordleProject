# CSCI 205 - Software Engineering and Design
Bucknell University  
Lewisburg, PA

### Course Info
Instructor: Brian King  
Semester: Fall 2022

## Team Information
Authors: Marion Duval and Kate Douglass  
Marion is a sophomore Cell Biology/Biochemistry major and Computer Science minor from Massachusetts.  
Kate is a sophomore Computer Science Engineering major from West Chester, Pennsylvania.

## Project Information
To create our text-based Wordle game, we created four different classes, each of which carry out a main part of our
program. We made our design so that the Wordle class is the main class that creates instances of our other classes.
An instance of WordDictionary is created first and then the methods within the class are invoked. In WordDictionary,
an instance of TextProcessor is created which invokes methods to create the word bank of common words to be used
throughout the game. Finally, WordDictionary gets a word to use as the secret word and validates a user's guess.
Back in Wordle, a main loop is entered which carries out each turn until the game is over. Each guess is analyzed in
the GuessEvaluator class.

## How to run it
To run our Wordle game, you should run Wordle.main(). Next, follow the directions on the screen to choose whether
you want to use an existing word bank or generate a new one to use. Then, you have six guesses to correctly guess
the secret five-letter word. After each guess, a series of characters will be printed. A * represents that the
letter is correct and in the correct place, a + represents a letter that is contained in the word but is not in the
correct place, and a - represents a letter that is not in the word.