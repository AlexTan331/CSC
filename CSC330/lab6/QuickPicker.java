package edu.cuny.csi.csc330.lab6;

import java.util.*;
import edu.cuny.csi.csc330.util.Randomizer;

public class QuickPicker {

	private final static int DEFAULT_GAME_COUNT = 1;
	private static String gameName;
	private static String storeName;
	private static String heading;
	private static String footer;
	
	private static int primaryPoolSize;
	private static int primaryPoolSelection;
	private static int[][] drawnNumbersForPrimary;
	
	private static int secondaryPoolSize;
	private static int secondaryPoolSelection;
	private static int[][] drawnNumbersForSecondary;
	
	private static int gameCount;
	private static int lengthOfAddedNumbers;

	private static boolean hasSecondPool = false;



	public QuickPicker(String fileName, int count)  throws QuickPickerException {
		gameCount = count;
		initPropBundle(fileName);
		init();
	}

	private static void initPropBundle(String fileName) throws QuickPickerException {
		
		ResourceBundle bundle = ResourceBundle.getBundle(fileName);

		//check if there is GameName in the file
		if (bundle.containsKey("GameName"))
			gameName = bundle.getString("GameName");
		else 
			throw new QuickPickerException("Game name not found: ", QuickPickerException.MISS_GAMENAME);
		
		//check if there is Vendor in the file
		if (bundle.containsKey("Vendor"))
			storeName = bundle.getString("Vendor");
		else 
			throw new QuickPickerException("Vendor name not found: ", QuickPickerException.MISS_STORENAME);
		
		//check if there is primary pool in the file
		if (bundle.containsKey("Pool1")) {
			String str = bundle.getString("Pool1");
			String[] pool1 = str.split("/", 2);
			primaryPoolSize = Integer.parseInt(pool1[1]);
			primaryPoolSelection = Integer.parseInt(pool1[0]);
		}
		else 
			throw new QuickPickerException("Primary pool not found: ", QuickPickerException.MISS_POOL1);

		//check if there is secondary pool in the file
		if (bundle.containsKey("Pool2")) {
			String str = bundle.getString("Pool2");
			String[] pool2 = str.split("/", 2);
			secondaryPoolSize = Integer.parseInt(pool2[1]);
			secondaryPoolSelection = Integer.parseInt(pool2[0]);
			if (secondaryPoolSize > 0)
				hasSecondPool = true;
		}
		else 
			throw new QuickPickerException("Secondary pool not found: ", QuickPickerException.MISS_POOL2);
		
	}

	private static void init() {

		drawnNumbersForPrimary = new int[gameCount][primaryPoolSelection + 1];
		
		if (hasSecondPool == true)
			drawnNumbersForSecondary = new int[gameCount][secondaryPoolSelection + 1];
		
		heading = "--------------------------------\n" 
				+ "-----  " + gameName + "  ------";
		
		footer = "\n---- " + storeName + " -----\n" 
				+ "--------------------------------";
	}

	/**
	 * 1. create a two dimensional array to store randomly generated numbers rows
	 * represent number of games, columns on each row contain randomly uniquely
	 * generated numbers 
	 * 2. generate a random number 
	 * 3. compare the number with every elements stored already in the array 
	 * if the number is not the same as the compared integer, go to the next 
	 * iteration if the number is repeated with the integer, generate another 
	 * random number and restart the for loop to compare between the randomized 
	 * number and the integers (col = 0) until the randomized number is unique 
	 * in the row 
	 * 4. store the random number into the array, and update the array 
	 * length (lengthOfAddedNumbers) 
	 * 5. repeat the steps to fill in numbers in the first row 
	 * 6. set the lengthOfAddedNumbers to zero and repeat the steps until 
	 * every row is filled in numbers
	 */
	private void drawnNumbers(int[][] pool, int selection, int poolSize) {
		
		lengthOfAddedNumbers = 0;

		for (int row = 0; row < gameCount; row++) {

			int num = Randomizer.generateInt(1, poolSize);
			pool[row][lengthOfAddedNumbers] = num;
			lengthOfAddedNumbers++;

			for (int i = 0; i < selection; i++) {
				num = Randomizer.generateInt(1, poolSize);

				for (int col = 0; col < lengthOfAddedNumbers; col++) {
					if (num != pool[row][col])
						continue;
					else {
						num = Randomizer.generateInt(1, poolSize);
						col = 0;
					}
				}
				pool[row][lengthOfAddedNumbers] = num;
				lengthOfAddedNumbers++;
			}

			lengthOfAddedNumbers = 0;
		}
		sortArray(pool, selection);
		
	}

	private void drawing() {

		// draw primary pool
		drawnNumbers(drawnNumbersForPrimary, primaryPoolSelection, primaryPoolSize);

		// draw secondary pool
		if (hasSecondPool == true)
			drawnNumbers(drawnNumbersForSecondary, secondaryPoolSelection, secondaryPoolSize);

	}

	private void sortArray(int[][] pool, int selection) {
		// sort the array from index 1 to SELECTION_COUNT
		for (int i = 0; i < pool.length; i++) {

			int l = Math.min(1, selection);
			int r = Math.max(1, selection);
			Arrays.sort(pool[i], l, r + 1);
		}
	}


	public void displayTicket() {
		/**
		 * display heading
		 * 
		 * for i in gameCount generate selectionCount number of unique random selections
		 * in ascending order
		 * 
		 * display footer
		 */

		// display ticket heading
		displayHeading();

		/**
		 * Display selected numbers
		 */
		//print out primary and secondary pool
		if (hasSecondPool == true) {
			for (int i = 0; i < gameCount; i++) {
				int colSecondaryPool = 1;
				System.out.printf("(%d) ", (i + 1));
				
				for (int j = 1; j <= primaryPoolSelection + secondaryPoolSelection ; j++) {
					
					if (j <= primaryPoolSelection)
						System.out.printf(" %02d ", drawnNumbersForPrimary[i][j]);
					else 
					{
						System.out.printf("(%02d)", drawnNumbersForSecondary[i][colSecondaryPool]);
						colSecondaryPool++;
					}

				}
				System.out.println(" ");
			}

		}
		//print out primary pool if no secondary pool provided 
		else {
			for (int i = 0; i < gameCount; i++) {
				System.out.printf("(%d) ", (i + 1));
				for (int j = 1; j <= primaryPoolSelection; j++) {

					System.out.printf(" %02d ", drawnNumbersForPrimary[i][j]);

				}
				System.out.println(" ");
			}
		}


		System.out.printf("\nOdds of Winning: 1 in %,d", calculateOdds());

		// display ticket footer
		displayFooter();

	}
	
	protected void displayHeading() {
		System.out.println(heading);  
		Date today = new Date();
		// Display a message to standard output
		System.out.println(today + "\n");
	}

	protected void displayFooter() {
		System.out.println(footer);
		
	}

	
	/**
	 * 
	 * formula: (poolSize)! / ((poolSize - selection)! * (selection)!)
	 *  */
	private long calculateOdds() {
		//Extra credit
		long numerator = 1;
		long denominator = 1;
		long totalOdd;
		long oddOfPrimaryPool;
		long oddOfSecondaryPool = 1;
		
		//Calculate primary pool 
		for (int i = 1; i <= primaryPoolSelection; i++)
		{
			denominator *= i;
		}
		
		for (int i = (primaryPoolSize - primaryPoolSelection + 1); i <= primaryPoolSize; i++)
		{
			numerator *= i;
		}
		oddOfPrimaryPool = numerator / denominator;
		
		
		//Calculate secondary pool
		if (hasSecondPool == true)
		{
			numerator = 1;
			denominator = 1;
			for (int i = 1; i <= secondaryPoolSelection; i++)
			{
				denominator *= i;
			}
			
			for (int i = (secondaryPoolSize - secondaryPoolSelection + 1); i <= secondaryPoolSize; i++)
			{
				numerator *= i;
			}
			oddOfSecondaryPool = numerator / denominator;
		}
			
		totalOdd = oddOfPrimaryPool * oddOfSecondaryPool;
		
		return totalOdd;
	}
	
	
	public static void main(String[] args) throws QuickPickerException{

		String gameName;
		int numberOfGames = DEFAULT_GAME_COUNT;
		
		if (args.length == 0)
		{
			System.err.println("Game name not found! Argument is needed!");
			System.exit(1);
		}
		
		gameName = args[0];

		if (args.length > 1)
			numberOfGames = Integer.parseInt(args[1]);
		
		try {
			QuickPicker lotto = new QuickPicker(gameName, numberOfGames);
			lotto.drawing();
			lotto.displayTicket();
		}
		catch (MissingResourceException qpe) {
			throw new QuickPickerException("Unknown game name: " + gameName, QuickPickerException.MISS_FILE);
		}

	}

}
