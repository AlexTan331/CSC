package edu.cuny.csi.csc330.lab5;

import java.util.*;

public class DrunkWalkTester {
	
	private Scanner input;

	public DrunkWalkTester() {
		init();  
	}
	
	private void init() {
		input = new Scanner(System.in);
	}
	
	public void runTest(int steps ) { 
		
		
		System.out.print("Enter the starting avenue value: ");
		int avenue = input.nextInt();
		System.out.print("Enter the starting street value: ");
		int street = input.nextInt();
		
		//////////////////////// start test 
		// make the Drunkard with initial position
		DrunkWalker billy = new DrunkWalker(avenue,street);

		// have him move/step 200 time 
		billy.fastForward(steps);
		
		// get his current location
		String location = billy.getLocation();
		
		// get distance from start
		int distance = billy.howFar();

		System.out.println("Billy's " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		billy.displayWalkDetails();
		
		//////////////////// end test 
		/** 
		 * Expand the test above to include the following ... 
		 * Create a 2nd instances of DrunkWalker - Harvey  
		 * Have then race each - which instance (billy or harvey)  
		 * manages to walk a greater distance with 200 steps?  
		 * 
		 * Also invoke the displayWalkDetails() on both instances.
 */
		System.out.println("\n***********************************************************\n");
		DrunkWalker Harvey = new DrunkWalker(avenue,street);
		Harvey.fastForward(steps);

		// get his current location
		String location1 = Harvey.getLocation();
		
		// get distance from start
		int distance1 = Harvey.howFar();

		System.out.println("Harvey's " + location1);
		System.out.println("That's " + distance1 + " blocks from start.");
		
		Harvey.displayWalkDetails();
		
		System.out.println("\n*************************************************************");
		System.out.println("*************************************************************\n");
		switch (Integer.compare(distance, distance1)) {
			case -1:
				System.out.println("\nHarvey walked a greater distance than Billy did!");
				break;
			case 0: 
				System.out.println("\nThey both walked the same distance!");
				break;
			case 1:
				System.out.println("\nBilly walked a greater distance than Harvey did");
				break;
			default:

		}
		
	}

	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args) {
		DrunkWalkTester tester = new DrunkWalkTester();
		
		int steps = 200; 
		if(args.length == 1) {
			steps = Integer.parseInt(args[0]);
		}
		
		tester.runTest(steps); 
		
		System.exit(0);

	}

}
