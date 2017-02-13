/*
 * File: HangmanLexicon.java
 * -------------------------
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import acm.util.*;
import java.io.File;

public class HangmanLexicon {

    //creates and ArrayList
    ArrayList<String> strList = new ArrayList<String>();

    public HangmanLexicon() {

        //Using BufferedReader open the file and read it line by line
        BufferedReader rd = null;

        try {
            String filePath = new File("").getAbsolutePath();
            String concat = filePath.concat("/build/classes/bin/HangmanLexicon.txt");

            rd = new BufferedReader(new FileReader(concat));

            while (true) {
                String line = rd.readLine();
                if (line == null) {
                    break;
                }
                //Read the lines from the file into an ArrayList 
                strList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns the number of words in the lexicon.
     */
    public int getWordCount() {
        return strList.size();
    }

    /**
     * Returns the word at the specified index.
     */
    public String getWord(int index) {
        return strList.get(index);
    }
}

