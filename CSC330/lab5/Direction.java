/**
 * Partially completed Direction ENUM 
 */

package edu.cuny.csi.csc330.lab5;

import edu.cuny.csi.csc330.util.Randomizer;

public enum Direction {
	NONE, NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST;

	public Direction getFavorite() {
		return SOUTH; // It's getting cold! ...
	}

	public Direction getNextRandom() {

		int direction = Randomizer.generateInt(1, 8);

		// 1 = south, 2 = west, 3 = north, 4 = east
		if (direction == 1) { // south
			return SOUTH;
		} else if (direction == 2) { // west
			return WEST;
		} else if (direction == 3) { // north
			return NORTH;
		} else if (direction == 4) { // east
			return EAST;
		} else if (direction == 5) { // northeast
			return NORTHEAST;
		} else if (direction == 6) { // northwest
			return NORTHWEST;
		} else if (direction == 7) { // southeast
			return SOUTHEAST;
		} else { // southwest
			return SOUTHWEST;
		}

	}

	public static void main(String[] args) {

		int c = 0;
		while (c++ < 100) {
			System.out.println(Direction.NONE.getNextRandom());
		}

	}

}
