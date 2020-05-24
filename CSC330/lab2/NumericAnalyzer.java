package edu.cuny.csi.csc330.lab2;

import java.lang.Math;
import java.util.Arrays;

public class NumericAnalyzer {
	
	private static int [] number;
	private static final String PRING_FORMAT = "%-20s %-,20d\n";
	public NumericAnalyzer(int[] numbers) {
		// TODO Auto-generated constructor stub
		this.number = numbers;
	}

	public static boolean isNumeric(String args) {
		//check if command line arguments are numeric values
	    if (args == null) {
	        return false;
	    }
	    try {
	        int integer= Integer.parseInt(args);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public void sortedNumbers() {
		//sorted numbers ascendingly
		Arrays.sort(number);
		for (int i = 0; i < number.length; i++)
		{
			System.out.print(number[i] + " ");
		}
		System.out.println("");
	}
	
	public int count() {
		//the size of number list 
		 return number.length;
	}
	
	public int minValue() {
		//the min value
		return number[0];
	}
	
	public int maxValue() {
		//the max value
		 return number[count() - 1];
	}
	
	public int range() {
		//the range: difference betwee the max and min
		return (number[count() - 1] - number[0]);
	}
	
	public int sum() {
		//the sum
		int sum = 0; 
		for (int i = 0; i < number.length; i++)
		{
			sum += number[i];
		}
		return sum;
	}
	
	public int averageValue() {
		//the average or mean value 
		return sum() / count();
	}
	
	public int median() {
		//the median - the middle value of the set of numbers sorted
		int med;
	
		if (count() % 2 == 0)
			med = (number[count() / 2 - 1] + number[count() / 2]) / 2;
		else
			med = number[count() / 2];
		return med;

	}
	
	public int variance() {
		//the variance: get values by subtracting mean from each value in the list, 
		//				square each of these values, add all of the squres together,
		//				divide the sum by the number of values.
		int sumofSquare = 0;
		for (int i = 0; i < number.length; i++)
		{	
			sumofSquare += Math.pow(number[i] - averageValue(), 2);
		}
		return sumofSquare;
		
	}
	
	public double deviation() {
		//the standard deviation: squre root of the variance
		return Math.sqrt(variance());
	}
	
	
	public void display() {
		//display sorted numberlist and all the calculations 
		System.out.printf(PRING_FORMAT, "Count:", count());
		System.out.printf(PRING_FORMAT, "Min: ", minValue() );
		System.out.printf(PRING_FORMAT, "Max:", maxValue());
		System.out.printf(PRING_FORMAT, "Range:", range());
		System.out.printf(PRING_FORMAT, "Sum:", sum());
		System.out.printf(PRING_FORMAT, "Mean:", averageValue());
		System.out.printf(PRING_FORMAT, "Median:", median());
		System.out.printf(PRING_FORMAT, "Variance:", variance());
		System.out.printf("%-20s %-,20f\n", "Standard Deviation:", deviation());
	}
	
	public static void main(String[] args) {
		// main() method code fragment example 
		if(args.length == 0 ) {
			System.err.println("Requires at least 1 positive integer - exiting early."  ); 
		System.exit(1); 
		}
		
		// create an int array 
		int [] numbers = new int[args.length]; 
		
		for(int i = 0 ; i < args.length ; ++i ) {
		  ///////////////////////////
		  ///// EXTRA CREDIT FEATURE 
		  if(!NumericAnalyzer.isNumeric(args[i])) {
			System.err.println("Expecting Numeric Data: " + args[i]);
			System.exit(2); // exit code for invalid data 
		  }
		  //////////////////////////

		  numbers[i] = Integer.parseInt(args[i]); 
		}

		NumericAnalyzer analyzer = new NumericAnalyzer(numbers);
		analyzer.sortedNumbers(); 
		analyzer.display(); 
		System.exit(0);
	}

}
