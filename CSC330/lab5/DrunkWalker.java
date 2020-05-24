package edu.cuny.csi.csc330.lab5;

import java.math.*;
import java.util.*;


public class DrunkWalker {
	
	private Intersection startIntersection;
	private Intersection currentIntersection;
	private static int steps;
	private LinkedList<Intersection> recordedSteps;
	private TreeMap<Intersection, Integer> counterMap; 

	public DrunkWalker() {
		init(0, 0);
	}
	
	/**
	 * 
	 * @param avenue
	 * @param street
	 */
	public DrunkWalker(int avenue, int street) {	
		init(avenue, street);
	}

	private void init(int avenue, int street) {
		startIntersection = new Intersection(avenue, street);
		currentIntersection = new Intersection(avenue, street);
		steps = 0;
		recordedSteps = new LinkedList<Intersection>(); 
		counterMap = new TreeMap<Intersection,Integer>(); 

	}
	
	/**
	 * step in a random direction   
	 */
	public void step() {
		
		takeAStep(); 
		
		/**  !!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * Now, update the Collections that manage the following:
		 * 
		 *  1) add this next step/intersection to a "history" List that will contain the sequence of all 
		 *  steps taken by this DrunkWalker instance 
		 *  
		 *  2) add this next step to a Intersection -> Counter Map ... The Map 
		 *  Collection can and should be of Generics "Type" <Intersection, Integer> 
		 *  key = Intersection 
		 *  value = Count Value  
		 *  Need to do something like this: 
		 *  if intersection is not saved in Map yet as a key yet, add a key->value pair of Intersection->1 
		 *  else if intersection value is there, the existing key->value pair need to be replaced as 
		 *   Intersection->existing_count+1 
		 *   
		 */	
		if (recordedSteps.contains(currentIntersection) == false)   
			recordedSteps.add(currentIntersection);

		
		if (counterMap.get(currentIntersection) == null)	
			counterMap.put(currentIntersection, 1);
		else	
			counterMap.put(currentIntersection, (counterMap.get(currentIntersection) + 1));
		
	}
	
	
	private void takeAStep() {
		Direction dir = Direction.NONE.getNextRandom(); 
		
		switch (dir) {
			case NORTH: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() , currentIntersection.getStreet() + 1);
				break;
			case SOUTH: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() , currentIntersection.getStreet() - 1);
				break;
			case EAST: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() - 1, currentIntersection.getStreet());
				break;
			case WEST: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() + 1, currentIntersection.getStreet());
				break;
			case NORTHWEST: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() + 1, currentIntersection.getStreet() + 1);
				break;
			case NORTHEAST: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() - 1, currentIntersection.getStreet() + 1);
				break;
			case SOUTHWEST: 
				currentIntersection = new Intersection(currentIntersection.getAvenue() + 1, currentIntersection.getStreet() - 1);
				break;
			default:
				currentIntersection = new Intersection(currentIntersection.getAvenue() - 1, currentIntersection.getStreet() - 1);
				break;
			}
		steps++;
	}


	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * toString() 
	 * @return
	 */
	@Override
	public String toString() {
		return "DrunkWalker [startIntersection=" + startIntersection + ", currentIntersection=" + currentIntersection
				+ ", recordedSteps=" + recordedSteps + ", counterMap=" + counterMap + "]";
	}

	/**
	 * generate string that contains current intersection/location info 
	 */
	public String getLocation() {
		return  String.format("Current location: DrunkWalker [avenue=%d, street=%d]", 
				currentIntersection.getAvenue(), currentIntersection.getStreet() );  
	}



	/**
	 * Take N number of steps 
	 * @param steps
	 */
	public void fastForward(int steps) {
		for (int i = 0; i < steps; i++)
			step();
	}
	
	/**
	 * Display information about this current walker instance 
	 */
	public void displayWalkDetails() {
		/**
		 * This method needs to display the following information in a neat, readable format 
		 * using calls to System.out.println() or System.out.printf()
		 * 
		 * 1 - starting location 
		 * 2 - current/ending location 
		 * 3 - distance (value returned by howFar() ) 
		 * 4 - number of steps taken - which collection would be able to provide that information, and how?  
		 * 5 - number of unique intersections visited - 
		 * 		which collection would be able to provide that information, and how? 
		 * 6 - Intersections visited more than once 
		 * 
		 */
		
		System.out.println("Starting Location: Intersection [avenue=" + startIntersection.getAvenue() 
				+ ", street=" + startIntersection.getStreet() + "]");
		System.out.println("Current/Ending Location: Intersection [avenue=" + currentIntersection.getAvenue() 
				+ ", street=" + currentIntersection.getStreet() + "]");
		System.out.println("Distance (blocks): " + howFar());
		System.out.println("Number of steps taken: " + steps);
		System.out.println("Number of unique intersections visited: " + recordedSteps.size() + "\n");

		for (Map.Entry<Intersection, Integer> m : counterMap.entrySet()) {
			if (m.getValue() >= 2)
				System.out.println("Visited Intersection " + m.getKey() + " " + m.getValue() + " times!");
		}
	}
	
	/**
	 * X Y Coordinate distance formula 
	 *  |x1 - x2| + |y1 - y2|   
	 * @return
	 */
	public int howFar() {
		/** |x1 - x2| + |y1 - y2|.
		startAvenue = x1 
		avenue = x2 
		startStreet = y1 
		street = y2 
	 
		return (Math.abs(startAvenue - avenue) ) + (Math.abs(startStreet - street)); 
		 * 
		 */
		
		int differenceOfAvenue = Math.abs(currentIntersection.getAvenue() - startIntersection.getAvenue()); 
		int differenceOfStreet = Math.abs(currentIntersection.getStreet() - startIntersection.getStreet());
		
		return Math.abs(differenceOfAvenue + differenceOfStreet); 
	}
	

	public static void main(String[] args) {
		
		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6,23);
		
		for(int i = 1 ; i <= 3 ; ++i ) {
			billy.step(); 
			System.out.printf("billy's location after %d steps: %s\n",
					i, billy.getLocation() );
		}
			
		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();
		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		System.out.println(" ");
		
		billy = new DrunkWalker(10, 42);
		// have him move 100  random intersections
		billy.fastForward(100);
		billy.displayWalkDetails();

	}

}
