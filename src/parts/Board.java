package parts;

public class Board {
	
	public Square[][] board;
	
	public Board() {
		
		board = new Square[8][8];
		
		for(int row = 0; row < 8; row++) {
			
			for(int col = 0; col < 8; col++) {
				
				Square s = new Square(row, col);
				board[row][col] = s;
			}
		}
	}
	
	public void printBoard() {
		
		for(int row = 7; row >= 0; row--) {
			
			for(int col = 0; col < 8; col++) {
				
				System.out.print(board[row][col] + " ");
			}
			
			System.out.print(" "+ (row + 1) + "\n");
		}
		System.out.println(" a  b  c  d  e  f  g  h\n");
	}
	
	public Square getTileAt(String s) {
		
		System.out.println(s.charAt(0) + " " + s.charAt(1));
		int row = s.charAt(0) - 'a' + 1;
		int col = Character.getNumericValue(s.charAt(1));
		
		return board[row - 1][col - 1];
		
	}
	
	public Piece getPieceAt(String s) {
		
		Square sq = getTileAt(s);
		if(sq.filled)
			return sq.p;
		
		return null;
	}
}
