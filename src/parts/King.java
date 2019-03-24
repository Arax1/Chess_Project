package parts;

import java.util.*;

public class King implements Piece {

	public int column, row;
	private char color;
	
	public King(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	public boolean equals(Object obj) {
		
	    if (obj == null) 
	    	return false;
	    
	    if (obj == this) 
	    	return true;
	    
	    if (!(obj instanceof King)) 
	    	return false;
	    
	    King o = (King) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		
		if(r >= 0 && r < 8 && c >= 0 && c < 8) {
			
			if(r <= (row + 1) && r >= (row - 1) && c <= (column + 1) && c >= (column - 1)) {
				return true;
			}
			
		}
		
		return false;
		
		
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled) {
			
			if(b.board[c][r].p.getColor() == color)
				return false;
		}
			
			
		else {
			
			List<Piece> list = (this.getColor() == 'w') ? b.black_pieces : b.white_pieces;
			List<Piece> threatens = b.threatens_king(list, c, r);
			
			if(!(threatens.isEmpty())) {
				return false;
			}
			
		}
		
		column = c;
		row = r;
		b.en_passant = null;
		return true;
	}

	public char getColor() {
		return color;
	}
	
	public String toString() {
		return getColor() + "K";
	}
}
