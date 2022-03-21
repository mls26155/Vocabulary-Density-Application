//---------------------------------------------------------------------------
// VocabularyDensity.java       by Dale/Joyce/Weems                 Chapter 5
//
// Displays the number of total words, unique words in the input text file,
// and the resulting vocabulary density.
// Input file indicated by a command line argument.
//---------------------------------------------------------------------------

import java.io.*;
import java.util.*;

public class VocabularyDensity
{
  public static void main(String[] args) throws IOException 
  {
	 Scanner keyboard = new Scanner(System.in); 
	 
	 //Note: This version includes parts (a, b, c and d)
	 //Note: This version will analyze and report each file separately and then output one combined report
	 
	 System.out.println("Enter how many files you want to process. (Ex: 3)");
	 int numOfFiles = keyboard.nextInt();
	 
	 String[] fname = new String[numOfFiles];      // input file of text
	 
	 for(int i = 0; i < numOfFiles; i++) {
		 fname[i] = args[i];
	 }
	 
	 
	 for(int i = 0; i < fname.length; i++) {
		 
		final int CAPACITY = 1000;   // capacity of collection
		String word;                 // current word
		int numWords = 0;            // total number of words
		int uniqWords;               // number of unique words
		double density;              // vocabulary density
		final int THRESHOLD = 2;	 // minimum word length
		int skippedWords = 0;

    	CollectionInterface<String> words = new ArrayCollection<String>(CAPACITY);
    	
    	// Set up file reading
    	FileReader fin = new FileReader(fname[i]);
    	Scanner wordsIn = new Scanner(fin);
    	wordsIn.useDelimiter("[^a-zA-Z']+");  // delimiters are nonletters,'
    	
    	while (wordsIn.hasNext())      // while more words to process
    	{
    		if(words.isFull()) {
    			System.out.println("Collection is full!");
    			System.exit(0);
    		}
    		word = wordsIn.next();          
    		word = word.toLowerCase();
    		
    		if(word.length() < THRESHOLD) {
    			skippedWords++;
    			continue;
    		}
    		
    		if (!words.contains(word))
    			words.add(word);
    		
    		numWords++;
    	}
    	
    	density = (double)numWords/words.size();
    	System.out.println("Analyzed file " + fname[i]);
    	System.out.println("\n\tTotal words:  " + numWords);
    	System.out.println("\tSkipped Words: " + skippedWords);
    	if (words.size() == CAPACITY)
    		System.out.println("\tUnique words: at least " + words.size());
    	else
    	{
    		System.out.println("\tUnique words: " + words.size());
    		System.out.printf("\n\tVocabulary density: %.2f", density);
    	}   
    	System.out.println();
    	System.out.println();
    }

	// ------------ Combined Analysis and Report -------
    
	final int CAPACITY = 10000;   // capacity of collection
	String word;                 // current word
	int numWords = 0;            // total number of words
	int uniqWords;               // number of unique words
	double density;              // vocabulary density
	final int THRESHOLD = 2;	 // minimum word length
	int skippedWords = 0;		 // counts number of words skipped
	
	CollectionInterface<String> words = new ArrayCollection<String>(CAPACITY);
		
	for(int i = 0; i < fname.length; i++) {
		
		// Set up file reading
		FileReader fin = new FileReader(fname[i]);
		Scanner wordsIn = new Scanner(fin);
		wordsIn.useDelimiter("[^a-zA-Z']+");  // delimiters are nonletters,'
		
		while (wordsIn.hasNext())      // while more words to process
		{
			if(words.isFull()) {
				System.out.println("Collection is full!");
				System.exit(0);
			}
			word = wordsIn.next();          
			word = word.toLowerCase();
			
			if(word.length() < THRESHOLD) {
				skippedWords++;
				continue;
			}
			
			if (!words.contains(word))
				words.add(word);
			
			numWords++;
		}
		
	}

	density = (double)numWords/words.size();
	System.out.println("Combined Analylsis of a total of " + numOfFiles + " files:");
	System.out.println("\n\tTotal words:  " + numWords);
	System.out.println("\tSkipped Words: " + skippedWords);
	if (words.size() == CAPACITY)
		System.out.println("\tUnique words: at least " + words.size());
	else
	{
		System.out.println("\tUnique words: " + words.size());
		System.out.printf("\n\tVocabulary density: %.2f", density);
	}   

  } 
} 