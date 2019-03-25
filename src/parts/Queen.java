package parts;

public class Queen implements Piece {

	private int column, row;
	private char color;
	
	public Queen(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	@Override
	public boolean equals(Object obj) {
		
	    if (obj == null) 
	    	return false;
	    
	    if (obj == this) 
	    	return true;
	    
	    if (!(obj instanceof Queen)) 
	    	return false;
	    
	    Queen o = (Queen) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	// Reused code from Rook class
	private boolean threat_lateral(int c, int r, Board b) {
		
		if(c != column && r != row)
			return false;
		
		if(c == column && c == row)
			return false;
		
		if(c == column) {
			int direction = (r > row) ? 1 : -1;
			for(int x = row + direction; x != r; x += direction)
				if(b.board[c][x].filled)
					return false;
			
			return true;
		}
		
		if(r == row) {
			int direction = (c > column) ? 1 : -1;
			for(int x = column + direction; x != c; x+= direction)
				if(b.board[x][r].filled)
					return false;
			
			return true;
		}
		
		return false;
	}
	
	
	// Reused code from Bishop class
	private boolean threat_diagonal(int c, int r, Board b) {
		
		if(c == column || r == row) {
			return false;
		}
		
		int c_mod = (c > column) ? 1 : -1;
		int r_mod = (r > row) ? 1 : -1;
		
		int index_r = row + r_mod;
		int index_c = column + c_mod;
		
		while((index_r > 0) && (index_r < 7) || (index_c > 0) && (index_c < 7)){
			
			if(index_r == r && index_c == c)
				return true;
			
			else if(index_r == r && index_c != c)
				return false;
			
			else if(index_r != r && index_c == c)
				return false;
			
			else {
				if(b.board[index_c][index_r].filled)
					return false;
				
				index_r += r_mod;
				index_c += c_mod;
			}
		}
		
		return false;
	}
	
	@Override
	// Uses threat_lateral and threat_diagonal
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		return threat_lateral(c, r, b) || threat_diagonal(c, r, b);
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled)
			if(b.board[c][r].p.getColor() == color)
				return false;
		
		column = c;
		row = r;
		b.en_passant = null;
		return true;
	}

	public char getColor() {
		return color;
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
	
	public String toString() {
		return getColor() + "Q";
	}

	@Override
	public Square[] getAllMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}
