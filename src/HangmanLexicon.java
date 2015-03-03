/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import acm.util.*;

public class HangmanLexicon {
	
	//creates and ArrayList
	ArrayList<String> strList = new ArrayList<String>();

	public HangmanLexicon() {
		
		//Using BufferedReader open the file and read it line by line
		BufferedReader rd = null;
		
		try {
			rd = new BufferedReader (new FileReader ("HangmanLexicon.txt"));
			
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				//Read the lines from the file into an ArrayList 
				strList.add(line);
			}
		} catch (IOException e) {
				e.printStackTrace();
		} 
		finally {
			try {
				rd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	

	
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}
	
	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
	}
}
