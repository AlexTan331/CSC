/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.util.*;

import edu.cuny.csi.csc330.util.Randomizer;

public class LottoQuickPicker {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Lotto"; 
	private final static int SELECTION_POOL_SIZE = 59; 
	private final static int SELECTION_COUNT = 6; 
	private final static int ARRAY_LENGTH = SELECTION_COUNT + 1;
	private static int lengthOfAddedNumbers;
	private int [][] numberPool;
	private int gamesCount;
	
	private static final String HEADING = 
			"--------------------------------\n"
			+ "-----------  " + GAME_NAME + "  ------------" ;
	
	
	private static final String FOOTER = 
			"\n------- S.I. Corner Deli --------\n"
			+ "--------------------------------" ;
	
	
	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public LottoQuickPicker(int games) {
		init(games); 
	}

	private void init(int games) {
		
		/*
		 * 1. create a two dimensional array to store randomly generated numbers
		 * rows represent number of games, columns on each row contain randomly uniquely generated numbers   
		 * 2. generate a random number
		 * 3. compare the number with every elements stored already in the array
		 * if the number is not the same as the compared integer, go to the next iteration
		 * if the number is repeated with the integer, generate another random number 
		 * and restart the for loop to compare between the randomized number and the integers (col = 0)
		 * until the randomized number is unique in the row  
		 * 4. store the random number into the array, and update the array length (lengthOfAddedNumbers)
		 * 5. repeat the steps to fill in numbers in the first row 	
		 * 6. set the lengthOfAddedNumbers to zero and repeat the steps until every row is filled in numbers 
		  */
		
		gamesCount = games;
		numberPool = new int[games][ARRAY_LENGTH];
		lengthOfAddedNumbers = 0;
		
		for (int row = 0; row < games; row++) {
			
			int num = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
			numberPool[row][lengthOfAddedNumbers] = num;
			lengthOfAddedNumbers++;
			
			for (int i = 0; i < SELECTION_COUNT; i++) {
				num = Randomizer.generateInt(1, SELECTION_POOL_SIZE);

				for (int col = 0; col < lengthOfAddedNumbers; col++) {
					if (num != numberPool[row][col])
						continue;
					else {
						num = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
						col = 0;
					}
				}
				numberPool[row][lengthOfAddedNumbers] = num;
				lengthOfAddedNumbers++;
			}
			
			lengthOfAddedNumbers = 0;
		}
		sortArray();
	}
	
	private void sortArray() {
		//sort the array from index 1 to SELECTION_COUNT
		for (int i = 0; i < numberPool.length; i++) {
			
	        int l = Math.min(1, SELECTION_COUNT); 
	        int r = Math.max(1, SELECTION_COUNT); 
	        Arrays.sort(numberPool[i], l, r+1); 
		}
	}


	/**
	 * 
	 */
	public void displayTicket() {
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
		
		// display ticket heading 
		displayHeading(); 
		
		
		/**
		 * Display selected numbers 
		 */
		for (int i = 0; i < gamesCount; i++)
		{
			System.out.printf("(%d) ", (i+1));
			for (int j = 1; j <= SELECTION_COUNT; j++)
			{
				if (numberPool[i][j] >= 1 && numberPool[i][j] <= 9)
				{
					System.out.printf(" %02d ", numberPool[i][j]);
				}
				else 
					System.out.printf(" %-3d", numberPool[i][j]);
			}
			System.out.println(" ");
		}
		
		System.out.printf("\nOdds of Winning: 1 in %,d", calculateOdds());
		
		// display ticket footer 
		displayFooter(); 
		
	}
	
	protected void displayHeading() {
		System.out.println(HEADING);
		Date today = new Date(); 
		// Display a message to standard output 
		System.out.println(today + "\n"); 
	}
	
	protected void displayFooter() {
		System.out.println(FOOTER);
		
	}
	
	
	/**
	 * 
	 * formula: (SELECTION_POOL_SIZE)! / ((SELECTION_POOL_SIZE - SELECTION_COUNT)! * (SELECTION_COUNT)!)
	 *  */
	private long calculateOdds() {
		//Extra credit
		
		long numerator = 1;
		long denominator = 1;
		long odd;
		for (int i = 1; i <= SELECTION_COUNT; i++)
		{
			denominator *= i;
		}
		
		for (int i = (SELECTION_POOL_SIZE - SELECTION_COUNT + 1); i <= SELECTION_POOL_SIZE; i++)
		{
			numerator *= i;
		}
		odd = numerator / denominator;
		return odd;
	}
  

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = DEFAULT_GAME_COUNT; 
		
		if(args.length > 0) {  // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]);  // [0] is the 1st element!
		}
		
		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);
		// now what 
		
		lotto.displayTicket(); 


	}

}
