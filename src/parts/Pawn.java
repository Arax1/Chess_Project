package parts;

import java.util.ArrayList;

public class Pawn implements Piece {
	private int column, row;
	private char color;
	
	public boolean hasmoved = false;
	
	//given a row, column and color
	public Pawn(int c, int r, char co) {
		row = r;
		column = c;
		color = co;
	}
	
	//assume basic setup, determine color yourself
	public Pawn(int c, int r) {
		row = r;
		column = c;
		
		color = (r == 6) ? 'b' : 'w';
	}
	
	//if you have a square already, use it to judge your position
	public Pawn(Square s, char c) {
		row = s.row;
		column = s.column;
		color = c;
	}
	
	@Override
	public boolean equals(Object obj) {
		
	    if (obj == null) 
	    	return false;
	    
	    if (obj == this) 
	    	return true;
	    
	    if (!(obj instanceof Pawn)) 
	    	return false;
	    
	    Pawn o = (Pawn) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	
	public String toString() {
		return getColor() + "P";
	}

	@Override
	public boolean threatens(int c, int r, Board board) {
		if(c < 0 || c > 7 || r < 0 || r > 7)
			return false;
		
		int direction = (color == 'w') ? 1 : -1;
		
		if(r == row + direction) {
			if(c == column + 1 || c == column - 1) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean moveTo(int newc, int newr, Board b) {
		
		//System.out.println("Current Row and Collumn: " + row + " " + column);
		//System.out.println("Destination Row and Collumn: " + newr + " " + newc);
		
		int direction = (color == 'w') ? 1 : -1;
		int hop = (hasmoved) ? 0 : direction;
		
		if(!b.filled(column,row + direction)) {
			
			if(newr == row + direction && newc == column) {
				hasmoved = true;
				row = newr;
				column = newc;
				b.en_passant = null;
				return true;
			}
			
			else if(newr == row + direction + hop && newc == column && (!b.filled(newc, newr))) {
				hasmoved = true;
				row = newr;
				b.en_passant = this;
				column = newc;
				return true;
			}
		}
		
		if(threatens(newc, newr, b)) {
			
			//regular taking a piece
			if(b.filled(newc, newr) && b.board[newc][newr].p.getColor() != color) {
				hasmoved = true;
				row = newr;
				column = newc;
				b.en_passant = null;
				return true;
			}
			
			//en passant
			else if(b.filled(newc, row)) {
				if(b.board[newc][row].p instanceof Pawn) {
					if(b.board[newc][row].p.equals(b.en_passant)) {
						
						@SuppressWarnings("unused")
						Piece pas = b.board[newc][row].removePiece();
						hasmoved = true;
						row = newr;
						column = newc;
						b.en_passant = null;
						return true;
					}
				}
			}
		}
			
		return false;
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}
	public char getColor() {
		return color;
	}

	@Override
	public ArrayList<Square> getAllMoves(Board board) {
		// TODO Auto-generated method stub
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		int dir = (color == 'w') ? 1 : -1;
		
		
		//check diagonals
		if(board.onBoard(column - 1, row + dir)) {
			
			//check regular take or en passant
			if(board.filled(column - 1, row + dir) || 
					(board.filled(column - 1, row) && board.getTileAt(column - 1, row).p instanceof Pawn)) {
					
				moves.add(new Square(column - 1, row + dir));
			}
		}
		
		if(board.onBoard(column + 1, row + dir)) {
			
			//regular take or en passant
			if(board.getTileAt(column + 1, row + dir).filled || 
					(board.filled(column + 1, row) && board.getTileAt(column + 1, row).p instanceof Pawn)) {
			
				moves.add(new Square(column + 1, row + dir));
			}
		}
		
		//check moving up
		if(board.onBoard(column, row + dir)) {
			if(!board.filled(column, row + dir)) {
				
				moves.add(new Square(column, row + dir));
				
				if(!hasmoved)
					if(board.onBoard(column, row + 2*dir))
						if(!board.filled(column, row + 2*dir))
							moves.add(new Square(column, row + 2*dir));
			}
		}
			
		return moves;
	}

	
}
