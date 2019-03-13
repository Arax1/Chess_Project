package parts;

public class Board {
	
	public Square[][] board;
	
	public Board() {
		
		board = new Square[8][8];
		
		for(int column = 0; column < 8; column++)
			for(int row = 0; row < 8; row++) {
				board[column][row] = new Square(column,row);
				
				if(row < 2 || row > 5) {
					// there should be pieces in this square
					// [0,0] is bottom left, [7,7] is top right
					
					if(row == 1 || row == 6) {
						//we have a pawn!
						board[column][row].putPiece(new Pawn(column,row));
					
					} else {
						//we have some other piece
						switch(column) {
						
						case 0: case 7:
							//rook
							board[column][row].putPiece(new Rook(column, row));
							break;
						
						case 1: case 6:
							//knight
							board[column][row].putPiece(new Knight(column, row));
							break;
							
						case 2: case 5:
							//bishop
							board[column][row].putPiece(new Bishop(column, row));
							break;
						
						case 3:
							//YASSS QUEEN
							board[column][row].putPiece(new Queen(column, row));
							break;
							
						case 4:
							//THE KING
							board[column][row].putPiece(new King(column, row));
							break;
						}	
					}
				}
			}
	}
	
	public void printBoard() {
		
		for(int row = board[0].length; row > 0; row--) {
			
			for(int col = 0; col < board.length; col++)
				System.out.print(board[col][row-1] + " ");
			
			System.out.println(row);
		}
		
		for(int col = 0; col < board.length; col++) {
			if(col != 0)
				System.out.print(" ");
			
			System.out.print(" " + (char)('a'+col));
			
		}
		
		System.out.println("\n");
	}
	
	public Square getTileAt(String s) {
		
		System.out.println(s.charAt(0) + " " + s.charAt(1));
		int row = s.charAt(0) - 'a' + 1;
		int col = Character.getNumericValue(s.charAt(1));
		
		return board[row - 1][col - 1];
		
	}
	
	/*public Piece getPieceAt(String s) {
		
		Square sq = getTileAt(s);
		if(sq.filled)
			return sq.p;
		
		return null;
	}*/
}
