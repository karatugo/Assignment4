/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

    public void run() {

        this.resize(1500, 1500);

        println("Welcome to Hangman!");
        String secretWord = createSecretWord();
        String guessedWord = "";
        String tempGuessedWord = " ";
        String guess = "  ";
        char c = guess.charAt(0); //Swap string to char
        int guessesLeft = 8;

        for (int i = 0; i < guessesLeft; i++) {
            guessedWord += "-";
        }

        //the beginning
        println("The Word now looks like this: " + guessedWord);

        //starts the game
        while (doesGameContinue(guessesLeft, tempGuessedWord)) {
            while (isInvalidGuess(guess, c)) {
                guess = readLine("Your Guess: ");
                guess = guess.toUpperCase();
                c = guess.charAt(0);
            }

            tempGuessedWord = createDashes(secretWord, guessedWord, guess);
            guessesLeft = getNumberOfGuessesLeft(secretWord, guessedWord, tempGuessedWord, guess, guessesLeft);
            guessedWord = tempGuessedWord;

            canvas.displayWord(secretWord, guessedWord);
            c = ' ';
        }
    }

    public String createSecretWord() {
        HangmanLexicon lexi = new HangmanLexicon();
        RandomGenerator rgen = new RandomGenerator();
        return lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    }

    private String createDashes(String SECRET_WORD, String GUESSED_WORD, String GUESS) {
        int NUM_DASHES = SECRET_WORD.length();
        boolean isGuessCorrect = false;

        for (int i = 0; i < NUM_DASHES; i++) {
            String letterOfTheSW = String.valueOf(SECRET_WORD.charAt(i));

            if (letterOfTheSW.equals(GUESS)) {
                GUESSED_WORD = GUESSED_WORD.substring(0, i) + GUESS + GUESSED_WORD.substring(i + 1, NUM_DASHES);
                isGuessCorrect = true;
            }
        }

        if (isGuessCorrect == true) {
            println("Your guess is correct!");
        } else {
            canvas.noteIncorrectGuess(GUESS.charAt(0));
        }

        if (GUESSED_WORD.equals(SECRET_WORD)) {
            canvas.displayWord(SECRET_WORD, GUESSED_WORD);
            println("You guessed the word: " + SECRET_WORD);
            println("You win.");
            return null;
        } else {
            println("The word now looks like this: " + GUESSED_WORD);
            return GUESSED_WORD;
        }

    }

    /*
     * calculates how many guesses are left
     */
    private int getNumberOfGuessesLeft(String SW, String GW, String GW_TEMP, String G, int GUESSES_LEFT) {
        if (GW_TEMP.equals(GW)) {
            if (GUESSES_LEFT == 2) {
                println("You have only one guess left.\n");
                GUESSES_LEFT -= 1;
            } else if (GUESSES_LEFT == 1) {
                GUESSES_LEFT -= 1;
                println("You are completely hung.");
                println("The word was: " + SW);
                println("You lose.");
            } else {
                println("There are no " + G + "\'s in the word.");
                GUESSES_LEFT -= 1;
                println("You have " + GUESSES_LEFT + " guesses left. \n\n");
            }
        } else {
            if (GUESSES_LEFT == 1) {
                println("You have only one guess left.\n");
            } else {
                println("You have " + GUESSES_LEFT + " guesses left. \n\n");
            }
        }
        canvas.nextBodyPart(GUESSES_LEFT);
        return GUESSES_LEFT;
    }

    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
        canvas.reset();
    }

    //instance variables
    private HangmanCanvas canvas = new HangmanCanvas();

    private boolean doesGameContinue(int guessesLeft, String tempGuessedWord) {
        return (guessesLeft > 0) && (tempGuessedWord != null);
    }

    private boolean isInvalidGuess(String guess, char c) {
        return (guess.length() > 1) || ((Character.isLetter(c)) == false) || (guess.length() == 0);
    }
}

