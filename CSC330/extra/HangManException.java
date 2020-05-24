package edu.cuny.csi.csc330.extra;

public class HangManException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StringBuilder hangMan;
	private String chosenWord;
	private StringBuilder inCorrectGuess;
	private int totalGuess;
	
	public HangManException() {	; }
	
	public HangManException(StringBuilder hangMan, String chosenWord, StringBuilder inCorrectGuess, int totalGuess) {
		this.hangMan = hangMan;
		this.chosenWord = chosenWord;
		this.inCorrectGuess = inCorrectGuess;
		this.totalGuess = totalGuess;
	}

	
	public HangManException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return "HangManGame:\n" + hangMan + "\nThe word: " + chosenWord 
				+ "\nIncorrect Guesses: " + inCorrectGuess + "\nTotal Guesses: " + totalGuess;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
