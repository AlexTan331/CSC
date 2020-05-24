package edu.cuny.csi.csc330.lab6;

import java.util.*;

public class QuickPickerException extends Exception {

	// static publicly defined error codes
	public static int MISS_GAMENAME = 0;
	public static int MISS_POOL1 = 1;
	public static int MISS_POOL2 = 2;
	public static int MISS_STORENAME = 3;
	public static int MISS_FILE = 4;
	protected int code;
	// static publicly defined exception messages
	public static String[] MESSAGE = { 
			"Missing the game name in file", 
			"Missing primary pool in file", 
			"Missing secondary pool in file",
			"Missing the store name in file",
			"Can't locate specified game"};

	public QuickPickerException(String message) {
		super(message);
	}
	
	public QuickPickerException(String message, int code) {
		super(message);
		this.code =code;
	}
	
	public int getCode() { 
		return code;
	}
	
	@Override
	public String toString() {
		return "QuickPickerException [getCode()=" + getCode() + ", "
				+ "toString()=" + super.toString() + "]\n" + MESSAGE[code];
	}

	public static void main(String[] args) {
		QuickPickerException qe = new QuickPickerException("error message: ...  ");
		System.out.println("Ex: " + qe);
		
		QuickPickerException qe1 = new QuickPickerException("error message: ...  ", QuickPickerException.MISS_FILE);
		System.out.println("Ex: " + qe1);
	}

}
