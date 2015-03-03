/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
    	
    	this.resize(1500,1500);
    	
    	//variables**********************************
    	String SW = setup(); //Secret Word
    	String GW = ""; //Guessed Word
    	String GW_TEMP = " ";//Temporary Guessed Word
    	String G = "  "; //Guess
		char c = G.charAt(0); //Swap string to char
    	int GUESSES_LEFT = 8; //number of guesses left
    	//to display dashes
    	for (int i=0; i < GUESSES_LEFT; i++) {
			GW += "-";
		}
    	//********************************************
    	
    	//the beginning
    	println("The Word now looks like this: " + GW);
    	
    	//starts the game
    	while((GUESSES_LEFT > 0) && (GW_TEMP != null)) {
    		while ((G.length() > 1) || ((Character.isLetter(c)) == false) || (G.length() == 0)) {
    				G = readLine("Your Guess: ");
    				G = G.toUpperCase();
        			c = G.charAt(0);
        			println(G);
    		}
    		
        	GW_TEMP = createDashes(SW, GW, G);
        	GUESSES_LEFT = howManyGuesses(SW, GW, GW_TEMP, G, GUESSES_LEFT);
    		GW = GW_TEMP;
    		
    		canvas.displayWord(SW, GW);
    		c= ' ';  
    	}
	}
    
    
    /*
     * setup method does the following:
     * 1. Generates the secret word and returns it
     * 2. prints welcome message
     */
    public String setup() {
       	// instance variables
    	HangmanLexicon lexi = new HangmanLexicon();
    	RandomGenerator rgen = new RandomGenerator();
    	
    	String SECRET_WORD = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    	
    	//bu iki satir silinecek
    	println(SECRET_WORD);
    	println(SECRET_WORD.length());
    	
    	println("Welcome to Hangman!"); // 2. above  	
    	return SECRET_WORD; // 1. above
    }
    
    /*
     * creates dashed words after user's guess
     */
    private String createDashes(String SECRET_WORD, String GUESSED_WORD, String GUESS) {
    	int NUM_DASHES = SECRET_WORD.length();
		boolean check = false;
    		
    	for (int i=0; i < NUM_DASHES; i++) {
    		String oneLetter = String.valueOf(SECRET_WORD.charAt(i));
    		if (oneLetter.equals(GUESS)){
    			GUESSED_WORD = GUESSED_WORD.substring(0, i) + GUESS + GUESSED_WORD.substring(i+1, NUM_DASHES);
    			check = true;	
    		}
    	}
    	
    	if (check == true) {
    		println("That guess is correct.");	
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
    private int howManyGuesses(String SW, String GW, String GW_TEMP, String G, int GUESSES_LEFT) {
    	if (GW_TEMP.equals(GW)){
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

    //creates canvas
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    	canvas.reset();
    }
 
    //instance variables
    private HangmanCanvas canvas = new HangmanCanvas();
}
