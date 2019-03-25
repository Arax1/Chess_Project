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
	
	public char getColor() {
		return color;
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
		
		System.out.println("Current Row and Collumn: " + row + " " + column);
		System.out.println("Destination Row and Collumn: " + newr + " " + newc);
		
		int direction = (color == 'w') ? 1 : -1;
		int hop = (hasmoved) ? 0 : direction;
		
		if(!b.board[column][row + direction].filled) {
			
			if(newr == row + direction && newc == column) {
				hasmoved = true;
				row = newr;
				column = newc;
				b.en_passant = null;
				return true;
			}
			
			else if(newr == row + direction + hop && newc == column && (!b.board[newc][newr].filled)) {
				hasmoved = true;
				row = newr;
				b.en_passant = this;
				column = newc;
				return true;
			}
		}
		
		if(threatens(newc, newr, b)) {
			
			//regular taking a piece
			if(b.board[newc][newr].filled && b.board[newc][newr].p.getColor() != color) {
				hasmoved = true;
				row = newr;
				column = newc;
				b.en_passant = null;
				return true;
			}
			
			//en passant
			else if(b.board[newc][row].filled) {
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

	@Override
	public ArrayList<Square> getAllMoves(Board b) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
