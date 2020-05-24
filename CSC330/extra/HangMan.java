package edu.cuny.csi.csc330.extra;

import java.io.*;
import java.util.*;

public class HangMan {

	private final static String DEFAULT_FILE_NAME = "C://Words.txt";
	private String fileName;
	private final static int AVAILABLE_GUESS = 6;
	
	private List words;
	private StringBuilder hangMan;		
	private StringBuilder mystery;  // _ _ _ _ _ 
	private String chosenWord;		//choose a random word from the file
	
	private static StringBuilder incorrectGuess;		//keep track of incorrect characters guessed by user
	private static int remainingGuess = AVAILABLE_GUESS;		
	private int totalGuesses = 1;		//keep track of total guesses by user
	
	
	public HangMan(){
		init();
	}

	private void init() {
		
		
		fileName = DEFAULT_FILE_NAME;
		words = new LinkedList<String>();
		
		if(System.getProperty("SCRIPT_FILE") != null  ) {
			fileName = System.getProperty("SCRIPT_FILE"); 
		}
		
		hangMan = new StringBuilder();
		hangMan.append("___________________\n");	//index 0~20
		hangMan.append("|       |\n");				//index 21~30
		hangMan.append("|       |\n");				//index 31~40
		hangMan.append("|       \n");				//index 41~49
		hangMan.append("|       \n");				//index 50~58
		hangMan.append("|       \n");				//index 59~67
		hangMan.append("______________________________\n\n");		
		
		incorrectGuess = new StringBuilder();
		mystery = new StringBuilder();
		
		loadFile();
		pickRandomWord();
		
	}

	
	private void loadFile() {
		
		File scriptFile = new File(fileName);
		
		if (scriptFile.exists() == false) {
			System.err.println("Cannot open file: " + fileName);
			System.exit(0);
		}
		
		try {
	    	FileReader fileReader = new FileReader(scriptFile.getAbsolutePath());  
	    	BufferedReader bufferedReader = new BufferedReader(fileReader);
	    	String line; 
	    	while( ( line = bufferedReader.readLine() ) != null )  
	    	{
	    		words.add(line);
	    	}
	    	
	    	fileReader.close();
	    	
		}catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
		}
	    	

	}
	
	
	private void pickRandomWord() {

		Random rand = new Random();
		int index = rand.nextInt(words.size() + 1);
		chosenWord = (String) words.get(index);
		
		for (int i = 0; i < chosenWord.length(); i++) {
			mystery.append("_ ");
		}
		
	}

	private boolean isContain(char guessedChar) {

		int index = chosenWord.indexOf(guessedChar);
		
		if (index == -1)	//-1 if the word does not contain the character
			return false;

		return true;
	}
	
	
	private void guessing(char ch) throws HangManException {
		
		if (isContain(ch) == true) {
			for (int i=0; i<chosenWord.length(); i++) {
				// go through the guessed word using charAt
				// if it contains user's guessedletter at certain position, replace "_" with the letter, 
				// and return true
				if (chosenWord.charAt(i) == ch) {
					
					mystery.setCharAt(i*2, ch);
				}
			}
			if (mystery.indexOf("_") == -1) {
				//check if user guesses all letter right
				printCorrect();
				System.exit(0);
			}
		
		} 
		else {
			// if false, store the incorrect guesses letter;
			// update numbers of guess left;
			// update hangMan stringbuilder;
			// if no guess left, throw HangManException;
			incorrectGuess.append(ch);
			remainingGuess--;
			switch (remainingGuess) {
				case 5:
					hangMan.replace(40, 49, "|       O\n");
					break;
				case 4:
					hangMan.replace(50, 58, "|      /");
					break;
				case 3:
					hangMan.replace(50, 58, "|      /|");
					break;
				case 2:
					hangMan.replace(50, 59, "|      /|\\");
					break;
				case 1:
					hangMan.replace(61, 67, "|      /");
					break;
				case 0:
					hangMan.replace(61, 70, "|      / \\");
					throw new HangManException(hangMan, chosenWord, incorrectGuess, totalGuesses);
			default:
					break;
			}
			
		}
		
		totalGuesses++;
		
		System.out.print(toString());
	}
	
	public void printCorrect() {
		System.out.println(mystery + "\n" + "YOU WIN!!! After " + totalGuesses + " lettersGuessed.");
	}
	
	@Override
	public String toString() {
		return "" + hangMan + mystery + "\nGuesses left:" + remainingGuess 
				+ "\tGuess #" + totalGuesses + ":\t";
	}

	public static void main(String[] args) {

		HangMan instance = new HangMan();
		System.out.print(instance.toString());
		Scanner input = new Scanner(System.in);
		
		while (remainingGuess > 0 )
		{
			String guess = input.nextLine();
			try {
				instance.guessing(guess.toUpperCase().charAt(0));
			} catch (HangManException e) {
				System.err.println(e);
			}
		}

		
		
	}

}
