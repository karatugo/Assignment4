/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		GLine SCAFFOLD = new GLine(SCAFFOLD_START_X, SCAFFOLD_START_Y, SCAFFOLD_START_X, SCAFFOLD_START_Y + SCAFFOLD_HEIGHT);
		add(SCAFFOLD);
		
		GLine BEAM = new GLine(SCAFFOLD_START_X, SCAFFOLD_START_Y, SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y);
		add(BEAM);
		
		GLine ROPE = new GLine(SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y, SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH);
		add(ROPE);	
		
		//add(new GLabel("hello, world"), 10, 75);
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String secret_word, String word) {
		
		GObject REMOVE_LABEL = getElementAt(WORD_X, WORD_Y);
		
		if (REMOVE_LABEL != null) {
			remove (REMOVE_LABEL);
		}
		
		if (word.equals(secret_word)) {
			removeAll();
			add(new GLabel (secret_word), getWidth()/2, getHeight()/2);
		} else {
			GLabel lab_word = new GLabel(word, WORD_X, WORD_Y);
			add(lab_word);
			
		}
	
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		String one_more_letter = String.valueOf(letter);
		INCORRECT_GUESSES += one_more_letter;
		add(new GLabel (INCORRECT_GUESSES), WORD_X, WORD_Y + 20);	
	}
	
	
	public void nextBodyPart(int i) {
		switch(i) {
		case 7: 
			GArc HEAD = new GArc(HEAD_RADIUS,HEAD_RADIUS,0,360);
			HEAD.setLocation(SCAFFOLD_START_X + BEAM_LENGTH - HEAD_RADIUS/2, SCAFFOLD_START_Y + ROPE_LENGTH);
			add(HEAD);
			break;
		case 6:
			GLine BODY = new GLine(SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS, SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			add(BODY);
			break;
		case 5:
			GLine UPPER_LEFT_ARM = new GLine(SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, SCAFFOLD_START_X + BEAM_LENGTH - UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			add(UPPER_LEFT_ARM);
			GLine LOWER_LEFT_ARM = new GLine(SCAFFOLD_START_X + BEAM_LENGTH - UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, SCAFFOLD_START_X + BEAM_LENGTH - UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(LOWER_LEFT_ARM);
			break;
		case 4:
			GLine UPPER_RIGHT_ARM = new GLine(SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, SCAFFOLD_START_X + BEAM_LENGTH + UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			add(UPPER_RIGHT_ARM);
			GLine LOWER_RIGHT_ARM = new GLine(SCAFFOLD_START_X + BEAM_LENGTH + UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, SCAFFOLD_START_X + BEAM_LENGTH + UPPER_ARM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(LOWER_RIGHT_ARM);
			break;
		case 3:
			GLine LEFT_HIP = new GLine (SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH - HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			add(LEFT_HIP);
			GLine LEFT_LEG = new GLine(SCAFFOLD_START_X + BEAM_LENGTH - HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH - HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(LEFT_LEG);
			break;
		case 2:
			GLine RIGHT_HIP = new GLine (SCAFFOLD_START_X + BEAM_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH + HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			add(RIGHT_HIP);
			GLine RIGHT_LEG = new GLine(SCAFFOLD_START_X + BEAM_LENGTH + HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH + HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(RIGHT_LEG);
			break;
		case 1:
			GLine LEFT_FOOT = new GLine (SCAFFOLD_START_X + BEAM_LENGTH - HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH - HIP_WIDTH/2 - FOOT_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(LEFT_FOOT);
			break;
		case 0:
			GLine RIGHT_FOOT = new GLine (SCAFFOLD_START_X + BEAM_LENGTH + HIP_WIDTH/2, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, SCAFFOLD_START_X + BEAM_LENGTH + HIP_WIDTH/2 + FOOT_LENGTH, SCAFFOLD_START_Y + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			add(RIGHT_FOOT);
			break;
		default:
			break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_START_X = 100;
	private static final int SCAFFOLD_START_Y = 200;	
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int WORD_X = 100;
	private static final int WORD_Y = 100;
	
	String INCORRECT_GUESSES = "";
	

}
