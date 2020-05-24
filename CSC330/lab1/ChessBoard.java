package edu.cuny.csi.csc330.lab1;
  
/**
 * Generates a unicode textual chessboard 
 * Reference:  https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode 
 * @author lji
 *
 */
public class ChessBoard {
	
	// Array of pieces values Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook 
	static private final  char [] WHITE_PIECES = {
		'\u2656', '\u2658', '\u2657', '\u2655', '\u2654', '\u2657', '\u2658', '\u2656'
	};
	
	// unicode value of a pawn 
	static private  final char PAWN = '\u2659'; 
	// our choice for "empty square" - medium rectangle ... 
	static private  final char EMPTY = '\u25AD'; 
	
	private static final int ROWS = 8; 
	private static final int COLS = 8;
	
	private static final String HEADING = 
			"-----------------------------------------\n"
			+ "        Unicode Symbols Chessboard\n" 
			+ "-----------------------------------------\n";
	
	// Black piece values are equiv white pieces + 6
	private static final int BLACK_PIECE_DISPLACEMENT = 6;
	
	static private void displayHeading() {
		System.out.println(HEADING.toUpperCase());
	}
	
	private static void displayBoard() {
		for (int row = ROWS; row >= 1; --row){
			System.out.printf("%-5d", row);
			switch (row) {
				case 8:
					for (int col = 0; col < COLS; col++)
						System.out.printf("%-5c", WHITE_PIECES[col] + BLACK_PIECE_DISPLACEMENT);
					break;
				case 7:
					for (int col = 0; col < COLS; col++)
						System.out.printf("%-5c", PAWN + BLACK_PIECE_DISPLACEMENT);
					break;
				case 6:
				case 5:
				case 4:
				case 3:
					for (int col = 0; col < COLS; col++)
						System.out.printf("%-5c", EMPTY);
					break;
				case 2:
					for (int col = 0; col < COLS; col++)
						System.out.printf("%-5c", PAWN);
					break;
				case 1:
					for (int col = 0; col < COLS; col++)
						System.out.printf("%-5c", WHITE_PIECES[col]);
					break;
				default:
			}
			
			System.out.println("");
		}
		
	}

		
	
	// Only Public Method Used Directly By main() 
	static public void display() {
		displayHeading(); // display heading 
		displayBoard(); 
	
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		ChessBoard.display(); 
	}
	
}